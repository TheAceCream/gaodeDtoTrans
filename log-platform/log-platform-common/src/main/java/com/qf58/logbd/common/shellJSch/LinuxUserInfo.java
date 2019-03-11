package com.shellJSch;

/**
 * Description:
 *
 * @Author: weishenpeng
 * Date: 2017/12/8
 * Time: 下午 08:44
 */
import com.jcraft.jsch.UserInfo;

public class LinuxUserInfo implements UserInfo {

	@Override
	public String getPassphrase() {
		// TODO Auto-generated method stub
		System.out.println("LinuxUserInfo.getPassphrase()");
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		System.out.println("LinuxUserInfo.getPassword()");
		return null;
	}

	@Override
	public boolean promptPassphrase(String arg0) {
		// TODO Auto-generated method stub
		System.out.println("LinuxUserInfo.promptPassphrase()");
		System.out.println(arg0);
		return false;
	}

	@Override
	public boolean promptPassword(String arg0) {
		// TODO Auto-generated method stub
		System.out.println("LinuxUserInfo.promptPassword()");
		System.out.println(arg0);
		return false;
	}

	@Override
	public boolean promptYesNo(String arg0) {
		// TODO Auto-generated method stub
		System.out.println("LinuxUserInfo.promptYesNo()");
		System.out.println(arg0);
		if (arg0.contains("The authenticity of host")) {
			return true;
		}
		return true;
	}

	@Override
	public void showMessage(String arg0) {
		// TODO Auto-generated method stub
		System.out.println("LinuxUserInfo.showMessage()");
	}

}
