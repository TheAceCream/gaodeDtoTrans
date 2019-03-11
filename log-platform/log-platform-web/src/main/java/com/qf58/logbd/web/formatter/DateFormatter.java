package com.qf58.bd.web.formatter;

import org.springframework.format.Formatter;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Description: 时间格式数据绑定
 * User: weishenpeng
 * Date: 2017/6/22
 * Time: 下午 09:33
 */
public class DateFormatter implements Formatter<Date> {

	@Override
	public String print(Date object, Locale locale) {
		return null;
	}

	@Override
	public Date parse(String text, Locale locale) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		if (StringUtils.isEmpty(text)) {
			return null;
		}
		try {
			long timeLong = Long.parseLong(text);
			date = new Date(timeLong);
		} catch (Exception e) {
			date = null;
		}
		return date;
	}

}