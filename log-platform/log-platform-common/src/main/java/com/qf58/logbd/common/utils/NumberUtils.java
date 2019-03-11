package com.qf58.bd.common.utils;


import org.apache.commons.lang3.StringUtils;

/**
 * Description:
 * User: weishenpeng
 * Date: 2017/6/29
 * Time: 下午 07:29
 */
public class NumberUtils {

	/**
	 * Integer 转 Byte
	 *
	 * @param integer
	 * @return
	 */
	public static Byte integerToByte(Integer integer) {
		if (integer != null && integer >= -128 && integer <= 127) {
			return Byte.parseByte(integer.toString());
		}
		return null;
	}

	/**
	 * Byte 转 Integer
	 *
	 * @param bt
	 * @return
	 */
	public static Integer byteToInteger(Byte bt) {
		if (bt != null) {
			return bt.intValue();
		}
		return null;
	}

	/**
	 * String 转 Byte
	 *
	 * @param obj
	 * @return
	 */
	public static Byte stringToByte(Object obj) {
		if (obj != null && !StringUtils.isEmpty(obj.toString())) {
			return Byte.parseByte(obj.toString());
		}
		return null;
	}

	/**
	 * String 转 Integer
	 *
	 * @param obj
	 * @return
	 */
	public static Integer stringToInteger(Object obj) {
		if (obj != null && !StringUtils.isEmpty(obj.toString())) {
			return Integer.parseInt(obj.toString());
		}
		return null;
	}

	/**
	 * String 转 Double
	 * @param obj
	 * @return
	 */
	public static Double stringToDouble(Object obj) {
		if (obj != null && !StringUtils.isEmpty(obj.toString())) {
			return Double.parseDouble(obj.toString());
		}
		return null;
	}

	/**
	 * String 转 Long
	 *
	 * @param obj
	 * @return
	 */
	public static Long stringToLong(Object obj) {
		if (obj != null && !StringUtils.isEmpty(obj.toString())) {
			return Long.parseLong(obj.toString());
		}
		return null;
	}

	/**
	 * 验证Long不为空，并且大于0
	 *
	 * @param num
	 * @return
	 */
	public static boolean validateLongIsNotNull(Long num) {
		if (num == null || num == 0) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(NumberUtils.integerToByte(null));
	}

}
