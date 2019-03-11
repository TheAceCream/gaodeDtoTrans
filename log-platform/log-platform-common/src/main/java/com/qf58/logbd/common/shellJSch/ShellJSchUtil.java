package com.shellJSch;

import com.jcraft.jsch.*;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Description: 利用JSch包实现远程主机SHELL命令执行
 *
 * @Author: weishenpeng
 * Date: 2017/12/8
 * Time: 下午 08:43
 */

public class ShellJSchUtil {
	/**
	 * 保存输出内容的容器
	 */
	private ArrayList<String> stdout;
	/**
	 * 使环境变量生效
	 */
	public static final String SOURCE_ETC_PROFILE = "source /etc/profile;";
	/**
	 * 用户信息
	 */
	private LinuxUser linuxUser;

	public ShellJSchUtil(LinuxUser linuxUser) {
		this.linuxUser = linuxUser;
		stdout = new ArrayList<>();
	}

	/**
	 * 获取session
	 *
	 * @return
	 * @throws JSchException
	 */
	public Session getSession() throws JSchException {
		//创建session并且打开连接，因为创建session之后要主动打开连接
		JSch jsch = new JSch();
		LinuxUserInfo userInfo = new LinuxUserInfo();
		if (LinuxConnectTypeEnum.ID_RSA.getCode() == linuxUser.getConnectType()) {
			jsch.addIdentity(linuxUser.getIdentifyKeyPath());
		}
		Session session = jsch.getSession(linuxUser.getUsername(), linuxUser.getHost(), linuxUser.getPort());
		if (LinuxConnectTypeEnum.PASSWORD.getCode() == linuxUser.getConnectType()) {
			session.setPassword(linuxUser.getPassword());
		}
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig("userauth.gssapi-with-mic", "no");
		session.setConfig(config);
		session.setUserInfo(userInfo);
		//设置链接超时时间
		session.connect(300000);
		return session;
	}

	/**
	 * 执行shell命令
	 *
	 * @param command
	 * @return
	 */
	public int execute(final String command) throws JSchException, IOException {
		if (StringUtils.isEmpty(command)) {
			return 0;
		}
		String execCommand = SOURCE_ETC_PROFILE + command.toString();
		System.out.println("The remote command is :" + execCommand);
		int returnCode = 0;

		//执行命令时，默认让环境变量生效一次
		Session session = null;
		ChannelExec channelExec = null;
		BufferedReader bfReader = null;
		try {
			session = getSession();

			//打开通道，设置通道类型，和执行的命令
			Channel channel = session.openChannel("exec");
			channelExec = (ChannelExec) channel;
			channelExec.setCommand(execCommand);

			channelExec.setInputStream(null);
			bfReader = new BufferedReader(new InputStreamReader
					(channelExec.getInputStream()));
			channelExec.connect();

			//接收远程服务器执行命令的结果
			formatStdout(bfReader);
			// 得到returnCode
			if (channelExec.isClosed()) {
				returnCode = channelExec.getExitStatus();
			}
		} catch (JSchException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (bfReader != null) {
					bfReader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 关闭通道
			try {
				if (channelExec != null) {
					channelExec.disconnect();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//关闭session
			try {
				if (session != null) {
					session.disconnect();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}


		}
		return returnCode;
	}

	/**
	 * 记录输出
	 *
	 * @param bfReader
	 * @throws IOException
	 */
	private void formatStdout(BufferedReader bfReader) throws IOException {
		String line;
		while ((line = bfReader.readLine()) != null) {
			stdout.add(line);
		}
	}

	/**
	 * 打印输出
	 *
	 * @return
	 */
	public String printStdout() {
		StringBuffer sout = new StringBuffer();
		for (String str : stdout) {
			System.out.println(str);
			sout.append(str);
		}
		return sout.toString();
	}

	/**
	 * get stdout
	 *
	 * @returnj
	 */
	public ArrayList<String> getStandardOutput() {
		return stdout;
	}

	public static void main(final String[] args) throws IOException, JSchException {
		LinuxUser linuxUser = new LinuxUser("172.16.6.93", "root", 2, "D:\\id_rsa_93");
		//LinuxUser linuxUser = new LinuxUser("172.16.6.93", "sqoop", 1, "wsp123456");
		ShellJSchUtil shellJSchUtil = new ShellJSchUtil(linuxUser);
		System.out.println("-------------------------------");
		shellJSchUtil.execute("ls /opt/web;");
		for (String str : shellJSchUtil.getStandardOutput()) {
			System.out.println(str);
		}
	}
}
