package com.github.GeekerDream.chapter2.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

/**
 * 自定义Realm
 * 
 * @author TongWei.Chen
 * @date 2016年12月26日11:54:16
 */
public class MyRealm2 implements Realm {
	
	/*
	 * 返回一个唯一的Realm名字
	 */
	public String getName() {
		return "myRealm2";
	}
	
	/*
	 * 判断此Realm是否支持此token
	 */
	public boolean supports(AuthenticationToken token) {
		//仅支持UsernamePassword类型的token
		return token instanceof UsernamePasswordToken;
	}

	/*
	 * 根据token获取认证信息
	 */
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		String username = (String)token.getPrincipal();
		String password = new String((char[])token.getCredentials());
		if(!"wei".equals(username)) {
            throw new UnknownAccountException(); //如果用户名错误
        }
        if(!"123".equals(password)) {
            throw new IncorrectCredentialsException(); //如果密码错误
        }
        //如果身份验证成功，则返回一个AuthenticationInfo实现
		return new SimpleAuthenticationInfo(username, password, getName());
	}
}
