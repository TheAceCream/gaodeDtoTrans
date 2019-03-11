package com.qf58.bd.common.utils;


import com.qf58.bd.common.utils.json.JsonUtil;

import java.io.*;

/**
 * Description:
 *
 * @Author: weishenpeng
 * Date: 2018/1/12
 * Time: 下午 06:26
 */
public class ObjectToByteUtil {

	/**
	 * 对象转数组
	 *
	 * @param obj
	 * @return
	 */
	public static byte[] toByteArray(Object obj) {
		byte[] bytes = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			oos.flush();
			bytes = bos.toByteArray();
			oos.close();
			bos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return bytes;
	}

	/**
	 * 数组转对象
	 *
	 * @param bytes
	 * @return
	 */
	public static Object toObject(byte[] bytes) {
		Object obj = null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bis);
			obj = ois.readObject();
			ois.close();
			bis.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return obj;
	}

	/**
	 * 数组转对象
	 *
	 * @param bytes
	 * @return
	 */
	public static Object toObject(Byte[] bytes) {
		Object obj = null;
		try {
			if (bytes != null) {
				return toObject(toNormalByteArray(bytes));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * byte[] 转为 包装类byte[]
	 *
	 * @param bytes
	 * @return
	 */
	public static Byte[] toPackageByteArray(byte[] bytes) {
		Byte[] newBytes = null;
		if (bytes != null) {
			newBytes = new Byte[bytes.length];
			for (int i = 0; i < bytes.length; i++) {
				newBytes[i] = bytes[i];
			}
		}
		return newBytes;
	}

	/**
	 * 包装类Byte[] 转为 byte[]
	 *
	 * @param bytes
	 * @return
	 */
	public static byte[] toNormalByteArray(Byte[] bytes) {
		byte[] newBytes = null;
		if (bytes != null) {
			newBytes = new byte[bytes.length];
			for (int i = 0; i < bytes.length; i++) {
				newBytes[i] = bytes[i];
			}
		}
		return newBytes;
	}

	public static void main(String[] args) {
		Exception e = new Exception("hehehehe");
		Byte[] pbts = toPackageByteArray(toByteArray(e));

		Exception e2 = (Exception) toObject(pbts);
		System.out.println(JsonUtil.toJSONString(e2));
	}
}
