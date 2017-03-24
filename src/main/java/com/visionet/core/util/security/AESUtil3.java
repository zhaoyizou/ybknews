package com.visionet.core.util.security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import com.visionet.core.util.ResourceUtil;

public class AESUtil3 {

	static final String algorithmStr = "AES/ECB/PKCS5Padding";
	// static final String algorithmStr = "AES/ECB/NoPadding";

	private static final Object TAG = "AES";

	static private KeyGenerator keyGen;

	static private Cipher cipher;

	static boolean isInited = false;

	private static void init() {
		try {
			keyGen = KeyGenerator.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		keyGen.init(128); // 128位的AES加密
		try {
			cipher = Cipher.getInstance(algorithmStr);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
		isInited = true;
	}

	private static byte[] genKey() {
		if (!isInited) {
			init();
		}
		return keyGen.generateKey().getEncoded();
	}

	private static byte[] encrypt(byte[] content, byte[] keyBytes) {
		byte[] encryptedText = null;
		if (!isInited) {
			init();
		}

		Key key = new SecretKeySpec(keyBytes, "AES");
		try {
			cipher.init(Cipher.ENCRYPT_MODE, key);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		try {
			encryptedText = cipher.doFinal(content);
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return encryptedText;
	}

	private static byte[] encrypt(String content, String password) {
		try {
			byte[] keyStr = getKey(password);
			SecretKeySpec key = new SecretKeySpec(keyStr, "AES");
			Cipher cipher = Cipher.getInstance(algorithmStr);// algorithmStr
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// ?
			byte[] result = cipher.doFinal(byteContent);
			return result; //
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static byte[] decrypt(byte[] content, String password) {
		try {
			byte[] keyStr = getKey(password);
			SecretKeySpec key = new SecretKeySpec(keyStr, "AES");
			Cipher cipher = Cipher.getInstance(algorithmStr);// algorithmStr
			cipher.init(Cipher.DECRYPT_MODE, key);// ?
			byte[] result = cipher.doFinal(content);
			return result; //
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static byte[] getKey(String password) {
		byte[] rByte = null;
		if (password != null) {
			rByte = password.getBytes();
		} else {
			rByte = new byte[24];
		}
		return rByte;
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	// 注意: 这里的password(秘钥必须是16位的)
	private static final String keyBytes = ResourceUtil.getValueBykey("base", "aes.cipher");

	/**
	 * 加密
	 */
	public static String encode(String content) {
		// 加密之后的字节数组,转成16进制的字符串形式输出
		return parseByte2HexStr(encrypt(content, keyBytes));
	}

	/**
	 * 解密
	 */
	public static String decode(String content) {
		// 解密之前,先将输入的字符串按照16进制转成二进制的字节数组,作为待解密的内容输入
		byte[] b = decrypt(parseHexStr2Byte(content), keyBytes);
		return new String(b);
	}

	private static final String ccc = ResourceUtil.getValueBykey("base", "aes.code");
	// 测试用例
	public static void test1() {
		// String content = "hello abcdefggsdfasdfasdf";
		// String pStr = encode(content );
		// System.out.println("加密前："+content);
		// System.out.println("加密后:" + pStr);
		//
		// String postStr = decode(pStr);
		// System.out.println("解密后："+ postStr );

//		String c = keyCode;
		// byte[] encrypt = encrypt(c, keyBytes);
		// String parseByte2HexStr = parseByte2HexStr(encrypt);

		// String encode = encode(c);
		// System.err.println("b4 encode :" + encode);
		// String decode = decode(encode);
		// System.err.println("at encode :" + decode);
		// System.err.println("--------------------------");
		// String encode2 = encode(encode(c));
		// System.err.println("2b4 encode-------------- :" + encode2);
		
		String cc = "45459E1AD2048E98AF5DF7CA3665CF93AC78D9C656777905A06988C8E8DD9825";
		System.err.println("带解密内容" + cc);
		String decode2 = decode(cc);
		System.err.println("解密后 :" + decode2);

		// System.err.println("带价密内容" + ccc);
		// String encode = encode(ccc);
		// System.err.println("加密后 :" + encode);
		// String decode = decode(encode);
		// System.err.println("解密后 :" + decode);
		

	}

	public static void main(String[] args) {
		test1();
	}
}