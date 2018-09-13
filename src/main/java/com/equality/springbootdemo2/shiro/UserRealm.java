package com.equality.springbootdemo2.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.equality.springbootdemo2.entity.two.UserInfo;
import com.equality.springbootdemo2.service.UserInfoService;

/**
 * 自定义 Realm 
 * @author E-Quality
 *
 */
public class UserRealm extends AuthorizingRealm {
	
	/**
	 * 用户信息
	 */
	@Autowired
	UserInfoService userInfoService;

	/**
	 * 执行认证逻辑
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		System.out.println("执行认证逻辑");
		
		//假设数据库中用户名和密码
		String name = "admin";
		String password = "admin";
		
		//编写shiro判断逻辑，判断用户名和密码
		
		/*//1.验证用户名
		UsernamePasswordToken token = (UsernamePasswordToken)arg0;
		if (!token.getUsername().equals(name)) {
			//用户名不存在
			return null;//shiro底层会抛出UnknownAccountException
		}*/
		
		//2.验证用户名
		UsernamePasswordToken token = (UsernamePasswordToken)arg0;
		UserInfo userInfo = userInfoService.findByName(token.getUsername());
		
		if(userInfo == null){
			//用户名不存在
			return null;//shiro底层会抛出UnknownAccountException
		}		
				
		//return new SimpleAuthenticationInfo("", password, "");//自动验证密码是否一致
		
		return new SimpleAuthenticationInfo(userInfo, userInfo.getPassword(), "");//自动验证密码是否一致  将userInfo作为principal，以便于授权时获取使用
	}
	
	/**
	 * 执行授权逻辑
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		System.out.println("执行授权逻辑");
		//方式1.
		//return null;
		
		//方式2.
		//给资源授权
		SimpleAuthorizationInfo simpleAuthorizationInfo = null;
		//添加資源的授权字符串
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		simpleAuthorizationInfo  = (SimpleAuthorizationInfo)session.getAttribute("permissions");
		if (simpleAuthorizationInfo == null) {
			simpleAuthorizationInfo = new SimpleAuthorizationInfo();
			UserInfo userInfo = (UserInfo)subject.getPrincipal();
			if ("admin".equals(userInfo.getName())) {
				simpleAuthorizationInfo.addStringPermission("user:add");
				simpleAuthorizationInfo.addStringPermission("user:update");
			}
			session.setAttribute("permissions", simpleAuthorizationInfo);
		}
				
		//方式3.到数据库查询当前用户对应的授权字符串
		//Subject subject = SecurityUtils.getSubject();
		//UserInfo userInfo = (UserInfo)subject.getPrincipal();
		//此时根据 userInfo查找对应的授权字符串
		
		return simpleAuthorizationInfo;
	}

}
