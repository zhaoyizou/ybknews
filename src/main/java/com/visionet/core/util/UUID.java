package com.visionet.core.util;

/**
 * @author admin
 *
 */
public class UUID {
	private UUID() {
	}
	
	/**
	 * 生成UUID
	 * @return
	 */
	public static String UUIDString() {
		return java.util.UUID.randomUUID().toString().replace("-", "");
	}

	private static String digits(long val, int digits) {
		long hi = 1L << (digits * 4);
		return NumberUtils.toString(hi | (val & (hi - 1)), NumberUtils.MAX_RADIX).substring(1);
	}

	/**
	 * 以62进制（字母加数字）生成19位UUID，最短的UUID
	 * 
	 * @return
	 */
	public static String uuid() {
		java.util.UUID uuid = java.util.UUID.randomUUID();
		StringBuilder sb = new StringBuilder();
		sb.append(digits(uuid.getMostSignificantBits() >> 32, 8));
		sb.append(digits(uuid.getMostSignificantBits() >> 16, 4));
		sb.append(digits(uuid.getMostSignificantBits(), 4));
		sb.append(digits(uuid.getLeastSignificantBits() >> 48, 4));
		sb.append(digits(uuid.getLeastSignificantBits(), 12));
		return sb.toString().toLowerCase();
	}

	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {

			System.err.println(uuid());
		}
	}

}
