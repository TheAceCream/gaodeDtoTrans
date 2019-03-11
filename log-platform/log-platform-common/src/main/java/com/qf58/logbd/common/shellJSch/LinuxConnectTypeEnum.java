package com.shellJSch;

/**
 * Description: linux连接类型
 *
 * @Author: weishenpeng
 * Date: 2017/12/23
 * Time: 上午 11:57
 */
public enum LinuxConnectTypeEnum {
	PASSWORD(1, "密码连接"),
	ID_RSA(2, "秘钥连接");

	private int code;
	private String desc;

	LinuxConnectTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public static LinuxConnectTypeEnum getByCode(Integer code) {
		if (code != null) {
			for (LinuxConnectTypeEnum linuxConnectTypeEnum : LinuxConnectTypeEnum.values()) {
				if (linuxConnectTypeEnum.getCode() == code) {
					return linuxConnectTypeEnum;
				}
			}
		}
		return null;
	}
}
