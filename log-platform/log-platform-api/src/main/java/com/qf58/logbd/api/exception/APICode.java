package com.qf58.bd.api.exception;

/**
 * Description:
 *
 * @Author: weishenpeng  Date: 2017/12/12 Time: 下午 03:19
 */
public enum APICode {

	/**
	 * Success api code.
	 */
	SUCCESS(Integer.valueOf(0), "success"),
	/**
	 * Runtime exception api code.
	 */
	RUNTIME_EXCEPTION(1, "系统异常！"),
	/**
	 * Login invalid api code.
	 */
	LOGIN_INVALID(2, "请先登录！"),
	/**
	 * Permission invalid api code.
	 */
	PERMISSION_INVALID(3, "没有权限执行该操作！"),
	/**
	 * Rpc bd platform exception api code.
	 */
	RPC_BD_PLATFORM_EXCEPTION(4,"RPC服务调用异常！"),
	/**
	 * The Rpc sso exception.
	 */
	RPC_SSO_EXCEPTION(5,"SSO RPC服务调用异常！"),
	/**
	 * Param invalid api code.
	 */
	PARAM_INVALID(100101, "参数异常！"),
	/**
	 * Param lack invalid api code.
	 */
	PARAM_LACK_INVALID(100102, "缺少必选参数！"),
	/**
	 * Param contains chinese code api code.
	 */
	PARAM_CONTAINS_CHINESE_CODE(100302, "字符串不能包含中文字符！"),

	;

	private int code;
	private String msg;

	APICode(int code, String msg) {
		this.code = code;
		this.msg = msg;
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
	 * Gets msg.
	 *
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * Gets by code.
	 *
	 * @param code the code
	 * @return the by code
	 */
	public static APICode getByCode(Integer code) {
		if (code == null) {
			return null;
		}
		for (APICode apiCode : APICode.values()) {
			if (apiCode.getCode() == code) {
				return apiCode;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return String.format("[code:%s, message:%s]", new Object[]{Integer.valueOf(this.code), this.msg});
	}
}
