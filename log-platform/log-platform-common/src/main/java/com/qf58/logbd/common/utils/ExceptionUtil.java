package com.qf58.bd.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Description:
 *
 * @Author: weishenpeng
 * Date: 2018/1/12
 * Time: 下午 08:56
 */
public class ExceptionUtil {
	/**
	 * 将异常信息输出位字符串
	 *
	 * @param t 异常类
	 * @return Exception的字符串详细信息
	 */
	public static String getTrace(Throwable t) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		t.printStackTrace(writer);
		StringBuffer buffer = stringWriter.getBuffer();
		return buffer.toString();
	}

}
