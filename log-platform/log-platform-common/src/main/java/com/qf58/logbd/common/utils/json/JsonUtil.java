package com.qf58.bd.common.utils.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.beanutils.BeanMap;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @Author: weishenpeng
 * Date: 2017/12/15
 * Time: 下午 05:08
 */
public class JsonUtil {
	private static final SerializeConfig config;

	static {
		config = new SerializeConfig();
		// 使用和json-lib兼容的日期输出格式
		config.put(java.util.Date.class, new JSONLibDataFormatSerializer());
		// 使用和json-lib兼容的日期输出格式
		config.put(java.sql.Date.class, new JSONLibDataFormatSerializer());
	}

	private static final SerializerFeature[] features = {
			// 输出空置字段
			SerializerFeature.WriteMapNullValue,
			// list字段如果为null，输出为[]，而不是null
			SerializerFeature.WriteNullListAsEmpty,
			// 数值字段如果为null，输出为0，而不是null
			SerializerFeature.WriteNullNumberAsZero,
			// Boolean字段如果为null，输出为false，而不是null
			SerializerFeature.WriteNullBooleanAsFalse,
			// 字符类型字段如果为null，输出为""，而不是null
			SerializerFeature.WriteNullStringAsEmpty
	};


	/**
	 * object 转为 json string
	 *
	 * @param object
	 * @return
	 */
	public static String toJSONString(Object object) {
		return JSON.toJSONString(object, config, features);
	}

	/**
	 * object 转为 json string , 预定义空串，空对象
	 *
	 * @param object
	 * @return
	 */
	public static String toJSONNoFeatures(Object object) {
		return JSON.toJSONString(object, config);
	}


	/**
	 * json 字符串 转 Object
	 *
	 * @param text
	 * @return
	 */
	public static Object toBean(String text) {
		return JSON.parse(text);
	}

	/**
	 * json 字符串 转 指定Object
	 *
	 * @param text
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> T toBean(String text, Class<T> clazz) {
		return JSON.parseObject(text, clazz);
	}

	/**
	 * 转换为数组
	 *
	 * @param text
	 * @return
	 */
	public static Object[] toArray(String text) {
		return toArray(text, null);
	}

	/**
	 * 转换为数组
	 *
	 * @param text
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> Object[] toArray(String text, Class<T> clazz) {
		return JSON.parseArray(text, clazz).toArray();
	}

	/**
	 * 转换为List
	 *
	 * @param text
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> toList(String text, Class<T> clazz) {
		return JSON.parseArray(text, clazz);
	}

	/**
	 * 将javabean转化为序列化的json字符串
	 *
	 * @param keyvalue
	 * @return
	 */
	public static Object beanToJson(KeyValue keyvalue) {
		String textJson = JSON.toJSONString(keyvalue);
		Object objectJson = JSON.parse(textJson);
		return objectJson;
	}

	/**
	 * 将string转化为序列化的json字符串
	 *
	 * @param text
	 * @return
	 */
	public static Object textToJson(String text) {
		Object objectJson = JSON.parse(text);
		return objectJson;
	}

	/**
	 * json字符串转化为map
	 *
	 * @param s
	 * @return
	 */
	public static Map stringToCollect(String s) {
		Map m = JSONObject.parseObject(s);
		return m;
	}

	/**
	 * 将map转化为string
	 *
	 * @param m
	 * @return
	 */
	public static String collectToString(Map m) {
		String s = JSONObject.toJSONString(m);
		return s;
	}

	/**
	 * 数组转为Map list
	 *
	 * @param objs
	 * @param <T>
	 * @return
	 */
	public static <T> List<Map> arrayToMapList(T[] objs) {
		List<Map> mapList = new ArrayList<>();
		if (objs != null) {
			for (Object obj : objs) {
				mapList.add(new BeanMap(obj));
			}
		}
		return mapList;
	}

}
