package com.qf58.bd.api.exception;

import java.io.Serializable;

/**
 * Description:
 *
 * @Author: weishenpeng  Date: 2017/12/12 Time: 下午 03:19
 */
public class APIException extends RuntimeException implements Serializable {

	/**
	 * The Code.
	 */
	public int code;
	/**
	 * The Msg.
	 */
	public String msg;

	/**
	 * Instantiates a new Api exception.
	 *
	 * @param msg the msg
	 */
	public APIException(String msg) {
		super(msg);
	}

	/**
	 * Instantiates a new Api exception.
	 *
	 * @param code the code
	 * @param msg  the msg
	 */
	public APIException(int code, String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}

	/**
	 * Instantiates a new Api exception.
	 *
	 * @param apiCode the api code
	 */
	public APIException(APICode apiCode) {
		this(apiCode.getCode(), apiCode.getMsg());
	}

	/**
	 * Gets code.
	 *
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Sets code.
	 *
	 * @param code the code
	 */
	public void setCode(int code) {
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
	 * Sets api code.
	 *
	 * @param apiCode the api code
	 */
	public void setAPICode(APICode apiCode) {
		this.code = apiCode.getCode();
		this.msg = apiCode.getMsg();
	}
}
