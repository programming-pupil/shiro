package com.github.GeekerDream.chapter2;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.mgt.SecurityManager;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 简单登录认证退出流程，类似于helloworld
 * 
 * @author TongWei.Chen
 * @date 2016年12月22日10:50:13
 *
 */
public class LoginLogoutTest {
	private static final Logger log = LoggerFactory.getLogger(LoginLogoutTest.class);
	
	@Test
	public void testHelloWorld() {
		//1、获取SecurityManager工厂，此处适用Ini配置文件初始化SecurityManager
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		//2、得到SecurityManager实例
		SecurityManager securityManager = factory.getInstance();
		//3、绑定给SecurityUtils
		SecurityUtils.setSecurityManager(securityManager);
		//4、得到Subject主体
		Subject subject = SecurityUtils.getSubject();
		//5、创建用户名/密码身份验证token（即用户身份/凭证）
		UsernamePasswordToken token = new UsernamePasswordToken("chen", "123");
		try {
			//6、登陆，即身份验证
			subject.login(token);
		} catch(UnknownAccountException e) {
			//账号不存在的异常
			log.info("账号不存在！", e);
		} catch(IncorrectCredentialsException e1) {
			//账号存在，但密码不匹配
			log.info("账号存在，但密码不匹配！", e1);
		} catch (AuthenticationException e2) {
			//所有认证时异常的父类
			log.info("身份验证失败了!", e2);
		}
		//判断用户是否已经登陆
		System.out.println("-----1------登陆了吗？" + subject.isAuthenticated());
		//7、登出
		subject.logout();
		System.out.println("-----2------登陆了吗？" + subject.isAuthenticated());
	}
}
