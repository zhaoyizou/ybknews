package com.visionet.core.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BeanUtils {

	/**
	 * 复制对象属性
	 * 
	 * @param source
	 * @param target
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public static void CopyProperty(Object source, Object target) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {
		Class tclazz = target.getClass();
		Class sclazz = source.getClass();
		Method[] tmethod=tclazz.getMethods();
	    
	    for (int i = 0; i < tmethod.length; i++) {
	    	Method tf= tmethod[i];
	    	if(tf.getName().startsWith("set"))
	    	{
		    	Method sf=sclazz.getMethod("get"+tf.getName().substring(3), null);
		    	if(sf!=null)
		    	{
		    	 tf.invoke(target, sf.invoke(source, null));
		    	}
	    	}
		}
		
	}
}
