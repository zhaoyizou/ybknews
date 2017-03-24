package com.visionet.ivr.socket;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.visionet.ivr.protocol.MsgBase;
public class MsgQueue {

	private static LinkedBlockingQueue<MsgBase> QUEUE=new LinkedBlockingQueue<MsgBase>();
	private static ArrayList<MsgBase> list=new ArrayList<>();
	private static Logger log=LoggerFactory.getLogger(MsgQueue.class);
	/**
	 * 添加一个元素到队首
	 * @param msg
	 */
	public static void offer(MsgBase msg)
	{
		try {
			QUEUE.offer(msg,10,TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}
	/**
	 * 从队尾弹出一个元素
	 * @return
	 */
	public static MsgBase take()
	{
		try {
			MsgBase msg=QUEUE.take();
			list.add(msg);
			return msg;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return null;
	}
	public static void SaveQueue()
	{
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(new FileOutputStream("D:\\temp\\obj"));
			out.writeObject(QUEUE);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	@SuppressWarnings("unchecked")
	public static void resetQueue()
	{
		ObjectInputStream input;
		 try {
			input=new ObjectInputStream(new FileInputStream("D:\\temp\\obj"));
			QUEUE=(LinkedBlockingQueue<MsgBase>)input.readObject();
			input.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static  <T> T[]   toArray(T[] t) 
	{
		return  QUEUE.toArray(t );
	}
	
	
}
