package com.visionet.core.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import com.amazonaws.services.s3.internal.MD5DigestCalculatingInputStream;

/**
 * 随便写个算法做验证吧...
 * 
 * @author zph
 *
 */
public class TokenUtils {

	private static final String PRIVATE_KEY="K6LB4G6QHY4X4FPGB6U";
	/**
	 * 获取token
	 * @param context
	 * @return
	 */
	public static String getToken(String context) {
	
		char[] ascArr = context.toCharArray();
		// 排一下序
		Arrays.sort(ascArr);
		StringBuilder sb = new StringBuilder();
		//倒序添加
		for (int i = ascArr.length - 1; i >= 0; i--) {
			sb.append(ascArr[i]);
		}
		//最后加上key
		sb.append(PRIVATE_KEY);
		String str = sb.toString();
		return MD5(str);
	}
	/**
	 * 身份验证
	 * @param str
	 * @return
	 */
	public static boolean authentication(String str,String token)
	{
		return getToken(str).equals(token);
	}

	/**
	 * MD5加密
	 * 
	 * @param value
	 * @return
	 */
	public static String MD5(String value) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			// 计算md5函数
			md.update(value.getBytes());
			return new BigInteger(1, md.digest()).toString(16);

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

