package com.visionet.ivr.exception;

public class ExceptionUtil {

	public static String GetExceptionStackTraceMsg(Exception e)
	{
		StringBuilder msg=new StringBuilder();
		StackTraceElement []  ele= e.getStackTrace();
		for(int i=ele.length-1;i>=0;i--)
		{
			msg.append("at ");
			msg.append(ele[i].getClassName());
			msg.append(ele[i].getMethodName());
			msg.append("(");
			msg.append(ele[i].getFileName());
			msg.append(":");
			msg.append(ele[i].getLineNumber());
			msg.append(")");
			msg.append("\r\n");
			//msg.append("at "+ele[i].getClassName()+ele[i].getMethodName()+"("+ele[i].getFileName()+":"+ele[i].getLineNumber()+")\r\n");
		}
		return msg.toString();
	}
}
