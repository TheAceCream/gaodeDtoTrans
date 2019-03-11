package com.shellJSch;

/**
 * Description:
 *
 * @Author: weishenpeng
 * Date: 2017/12/23
 * Time: 上午 11:36
 */
public class LinuxUser {
	/**
	 * 远程主机的ip地址
	 */
	private String host;
	/**
	 * 设置ssh连接的远程端口
	 */
	public int port = 22;
	/**
	 * 远程主机登录用户名
	 */
	private String username;
	/**
	 * 链接类型 1.密码登录， 2.秘钥登录
	 */
	private int connectType;
	/**
	 * 远程主机的登录密码
	 */
	private String password;
	/**
	 * 秘钥文件路径
	 */
	private String identifyKeyPath;

	public LinuxUser(String host, String username, int connectType, String identify) {
		this.host = host;
		this.username = username;
		this.connectType = connectType;
		if (connectType == 1) {
			this.password = identify;
		} else {
			this.identifyKeyPath = identify;
		}
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getConnectType() {
		return connectType;
	}

	public void setConnectType(int connectType) {
		this.connectType = connectType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIdentifyKeyPath() {
		return identifyKeyPath;
	}

	public void setIdentifyKeyPath(String identifyKeyPath) {
		this.identifyKeyPath = identifyKeyPath;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
