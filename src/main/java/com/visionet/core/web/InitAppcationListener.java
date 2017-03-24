package com.visionet.core.web;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.visionet.core.util.ResourceUtil;
import com.visionet.ivr.socket.IVRClient;
import com.visionet.ivr.socket.OldDispatchServiceSocketClient;

/**
 * @author liusy
 * 容器初始化监听
 */
public class InitAppcationListener implements ApplicationListener<ContextRefreshedEvent>{
	private static Logger Log = LoggerFactory.getLogger(InitAppcationListener.class);

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
	 */
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		if (contextRefreshedEvent.getApplicationContext().getParent() == null) {
			Log.debug("======================容器加载完成开始初始化数据=============================");
			Log.debug("==========================数据初始化完成===================================");
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					String portStr= ResourceUtil.getValueBykey("base", "ivr.socket.port");
					String dhost= ResourceUtil.getValueBykey("base", "dispatch.socket.host");
					String dportStr= ResourceUtil.getValueBykey("base", "dispatch.socket.port");
					int dport=Integer.parseInt(dportStr);
					ServerSocket ss = null;
					try {
					OldDispatchServiceSocketClient dispatch=new OldDispatchServiceSocketClient(dhost, dport);
					Log.info("已经连接上调度系统...host:{},port:{}",dhost,dport);
					int port=Integer.parseInt(portStr);
						ss = new ServerSocket(port);
						Log.info("开始监听本机端口:"+port+",等待IVR客户端上线...");
						while (true) {
							Socket client = ss.accept();
							new IVRClient(client);
							
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						// TODO: handle finally clause
						try {
							ss.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}).start();
			
		}
	}
}
