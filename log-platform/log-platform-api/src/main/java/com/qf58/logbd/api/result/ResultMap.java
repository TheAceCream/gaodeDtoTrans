package com.qf58.bd.api.result;

import com.qf58.bd.api.exception.APICode;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Description: API接口返回包装类
 * User: weishenpeng
 * Date: 2017/6/19
 * Time: 下午 05:10
 */
public class ResultMap implements Serializable {
	private static final long serialVersionUID = -8673886735675934115L;

	private Integer code;
	private String msg;
	private Object data;

	/**
	 * Gets code.
	 *
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * Sets code.
	 *
	 * @param code the code
	 */
	public void setCode(Integer code) {
		this.code = code;
	}

	/**
	 * Gets msg.
	 *
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * Sets msg.
	 *
	 * @param msg the msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * Gets data.
	 *
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * Sets data.
	 *
	 * @param data the data
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * Sets APICode.
	 *
	 * @param apiCode the data
	 */
	public void setAPICode(APICode apiCode) {
		this.code = apiCode.getCode();
		this.msg = apiCode.getMsg();
	}

	/**
	 * Instantiates a new Result map.
	 */
	public ResultMap() {
		super();
	}

	/**
	 * Instantiates a new Result map.
	 *
	 * @param code the code
	 * @param msg  the msg
	 * @param data the data
	 */
	public ResultMap(Integer code, String msg, Object data) {

		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	/**
	 * Instantiates a new Result map.
	 *
	 * @param code the code
	 * @param msg  the msg
	 */
	public ResultMap(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
		this.data = new HashMap<>();
	}

	/**
	 * Instantiates a new Result map.
	 *
	 * @param apiCode the api code
	 */
	public ResultMap(APICode apiCode) {
		this.code = apiCode.getCode();
		this.msg = apiCode.getMsg();
		this.data = new HashMap<>();
	}
}
