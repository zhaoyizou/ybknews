package com.visionet.core.util;

import org.apache.commons.lang3.StringUtils;

public class ValidateUtils {
	/**
	 * 
	 * 
	 * Verify whether the data is double type
	 * 
	 * @param i
	 * @return
	 */
	public static boolean checkIsDouble(String i) {
		boolean bCheckResult = false;
		if (StringUtils.isBlank(i.trim())) {
			return bCheckResult;
		}
		try {
			Double dCheckValue = Double.parseDouble(i.trim());
			if (dCheckValue instanceof Double == false) {
				bCheckResult = false;
			} else
				bCheckResult = true;
		} catch (NumberFormatException e) {
			bCheckResult = false;
		}
		return bCheckResult;

	}

	/**
	 * 
	 * 
	 * Verify whether the data is int type
	 * 
	 * @param i
	 * @return
	 */
	public static boolean checkIsInt(String i) {
		boolean bCheckResult = false;
		if (StringUtils.isBlank(i.trim())) {
			return bCheckResult;
		}
		try {
			Integer iCheckValue = Integer.parseInt(i.trim());
			if (iCheckValue instanceof Integer == false) {
				bCheckResult = false;
		} else
			bCheckResult = true;
		} catch (NumberFormatException e) {
			bCheckResult = false;
		}
		return bCheckResult;

	}

	public static void main(String[] args) {
		System.err.println(checkIsDouble(" 0quite  "));
		System.err.println(checkIsInt(" 0away  "));
	}
}
