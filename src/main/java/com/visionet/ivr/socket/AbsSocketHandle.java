package com.visionet.ivr.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.visionet.core.util.UUID;
import com.visionet.ivr.exception.ExceptionUtil;
import com.visionet.ivr.exception.MsgException;
import com.visionet.ivr.exception.MyException;
import com.visionet.ivr.protocol.MsgBase;
import com.visionet.ivr.utils.ConverterUtil;

/**
 * 
 * @author zph
 *
 */
public abstract class AbsSocketHandle extends Thread {

	protected Logger log = LoggerFactory.getLogger(this.getClass());
	private static HashMap<String, AbsSocketHandle> clients = new HashMap<String, AbsSocketHandle>();
	
	private Socket client;
	private String hostName;
	private int prot;
	boolean isClosed = false;
	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public int getProt() {
		return prot;
	}

	public void setProt(int prot) {
		this.prot = prot;
	}

	private OutputStream output;
	private InputStream input;

	/**
	 * 
	 * @return 返回一个客户端
	 */
	public static AbsSocketHandle getClient(String key) {
		return AbsSocketHandle.clients.get(key);
	}

	/**
	 * 
	 * @param key
	 * @param val
	 */
	public static void putClient(String key, AbsSocketHandle val) {
		clients.put(key, val);
	}

	public AbsSocketHandle(Socket clt) throws IOException {
		init(clt);
	}

	public AbsSocketHandle(String host, int port) throws IOException {
		Socket clt = new Socket(host, port);
		
		init(clt);
	}

	protected void init(Socket clt) throws IOException {
		InetSocketAddress address = (InetSocketAddress) clt.getRemoteSocketAddress();
		hostName = address.getHostName();
		prot = address.getPort();
		log.info("remote address:" + hostName + ",port:" + prot + " ,hashCode:" + this.hashCode());
		client = clt;
		output = client.getOutputStream();
		input = client.getInputStream();
		clients.put(this.getClass().getName(), this);// 重连之后,前对象会被替换,但不保证该线程会被清除
		// 本线程自动开启监听
		if (this.getState() == Thread.State.NEW) {
			this.setName(this.getClass().getName());
			this.start();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		listenerRemoteMsg();
		log.info("线程退出...");

	}

	public void listenerRemoteMsg() {
		byte[] head = new byte[2];
		byte[] body = null;
		short blen = 0;
		String redStr = "";
		
		// 假设不会有任何超出协议外的报文出现,如果出现则有问题.由于不知道这个包到底多长...所以报文头应该做加密验证.
		try {

			while (!isClosed && client.isConnected() && !client.isClosed()) {
				try {
					// 读取头
					head = readMsg(input, head.length);
					if (head == null) {
						isClosed = true;
						break;
					}
					blen = ConverterUtil.bytesToShort2(head, 0);
					// 读body
					body = readMsg(input, blen);
					if (body == null) {
						isClosed = true;
						break;
					}
					MsgBase msg = ConverterUtil.getMsg(head, body);
					msg.setUuid(UUID.uuid());
					MsgHandle(msg);
				} catch (MyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MsgException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
			log.error("发生连接异常:\r\n", e.getMessage());

		} finally {
			log.info("连接断开..");
			// 是否需要重连由子类实现,默认重连
			// 如果重连成功本线程继续监听
			if (RestConnection()) {
				log.info("断开重连成功...");
				listenerRemoteMsg();
			} else {
				log.error("断开重连失败...");
			}
		}
	}

	/**
	 * 读取指定长度的字节
	 * 
	 * @param in
	 * @param size
	 * @return
	 * @throws IOException
	 */
	private byte[] readMsg(InputStream in, int size) throws IOException {
		byte[] bt = new byte[size];
		int len = 0;
		int idx = 0;
		log.info("readMsg=>{}:{}", this.hostName, this.prot);

		while (true) {
			idx = in.read(bt, len, size - len);
			// 连接被断开
			if (idx == -1) {
				bt = null;
				return bt;
			}
			len += idx;
			if (len == size || idx < 0) {
				break;
			}
		}
		return bt;
	}

	/**
	 * 发送消息,线程安全的
	 * 
	 * @param msg
	 * @throws IOException
	 */
	public synchronized void sendMsg(String msg) throws IOException {
		if (client.isConnected()) {
			byte[] bte = msg.getBytes(Charset.forName("utf-8"));
			output.write(bte, 0, bte.length);
			output.flush();
		}
	}

	/**
	 * 发送消息,线程安全的
	 * 
	 * @param msg
	 * @throws IOException
	 */
	public synchronized void sendMsg(MsgBase msg) throws IOException {
		if (client.isConnected() && !client.isClosed()) {
			byte[] bte = ConverterUtil.MsgtoBytes(msg);
			output.write(bte, 0, bte.length);
			output.flush();
		}
	}

	/**
	 * 发送消息,线程安全的
	 * 
	 * @param msg
	 * @throws IOException
	 */
	public synchronized void sendMsg(byte[] bts, int off, int length) throws IOException {
		if (client.isConnected()) {
			output.write(bts, off, length);
		}
	}

	/**
	 * 关闭连接并释放资源
	 */
	public void CloseSocket() {

		try {
			output.close();
			input.close();
			client.close();
		} catch (IOException e1) {
			log.error(e1.getMessage());
		}
	}

	/**
	 * 重连.
	 */
	public boolean RestConnection() {
		// TODO Auto-generated method stub
		// 关闭当前的所有资源
		CloseSocket();
		// 最多重试三次
		for (int i = 0; i < 3; i++) {
			try {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Socket client = new Socket(this.hostName, this.prot);
				init(client);
				return true;
			} catch (IOException e) {
				// TODO: handle exception
				log.error("重连发生异常,第{}次重连", i + 1);
			}
		}

		return false;

	}

	/**
	 * 消息处理
	 * 
	 * @param msg
	 */
	public abstract void MsgHandle(MsgBase msg) throws MsgException, MyException;

}
