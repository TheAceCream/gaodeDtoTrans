package com.qf58.bd.web.pub;

import java.io.Serializable;

/**
 * Description: echarts 统计数据格式
 *
 * @Author: weishenpeng
 * Date: 2018/1/3
 * Time: 下午 03:56
 */
public class BaseEChartsDataEntity implements Serializable {

	private static final long serialVersionUID = 1379809765406618240L;
	private String name;
	private Long value;

	public BaseEChartsDataEntity() {
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public BaseEChartsDataEntity(String name, Long value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "BaseEChartsDataEntity{" +
				"name='" + name + '\'' +
				", value=" + value +
				'}';
	}
}
