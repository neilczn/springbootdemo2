package com.equality.springbootdemo2.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.equality.springbootdemo2.shiro.UserRealm;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

/**
 * Shiro配置类
 * @author E-Quality
 *
 */
@Configuration
public class ShiroConfig {
	
	/**
	 * suject:用户主体(把操作交给SecurityManager)
	 * SecurityManager:安全管理器(关联Realm)
	 * Realm:Shiro连接数据的桥梁
	 */
	
	/**
	 * 1.创建ShiroFilterFactoryBean 
	 */
	@Bean(name="shiroFilterFactoryBean")
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		//设置安全管理器
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		
		//添加shiro内置过滤器
		/**
		 * shiro内置过滤器,可以实现权限相关的拦截
		 * 	常用的过滤器：
		 * 		anon:无需认证（登录）就可以访问
		 * 		authc:必须认证才可以访问
		 * 		user:如果使用remember me的功能可以直接访问
		 * 		perms:该资源必须得到资源权限才可以访问
		 * 		role:该资源必须得到角色权限才可以访问
		 */
		Map<String,String> filterMap = new LinkedHashMap<String,String>();//LinkedHashMap 保持顺序
				
		filterMap.put("/testThymeleaf", "anon");
		//放行login。html页面
		filterMap.put("/login", "anon");
		
		//允许访问静态资源
		filterMap.put("/static/**", "anon");
		
		//授权过滤器  注意：当前授权被拦截后，shiro会自动跳转到未授权页面
		filterMap.put("/user/add", "perms[user:add]");//需要在UserRealm doGetAuthorizationInfo 方法中添加資源的授权字符串，否则跳转到未授权页面
		filterMap.put("/user/update", "perms[user:update]");
						
		filterMap.put("/**", "authc");
		//filterMap.put("/user/*", "authc");
		
		
		
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
		
		//修改跳转的登录页面
		shiroFilterFactoryBean.setLoginUrl("/toLogin");
		
		//设置未授权提示页面
		shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");
		
		return shiroFilterFactoryBean;
	}
	
	/**
	 * 2.创建DefaultWebSecurityManager
	 */
	@Bean(name="securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		//关联3.Realm
		securityManager.setRealm(userRealm);
		
		//关联6.RedisCacheManager
		securityManager.setCacheManager(cacheManager());
		
		//关联8.
		securityManager.setSessionManager(sessionManager());
		
		return securityManager;
	}
	
	/**
	 * 3.创建Realm
	 */
	@Bean(name="userRealm")
	public UserRealm getRealm(){
		return new UserRealm();
	}
	
	/**
	 * 4.配置ShiroDialect,用于Shiro标签与thymeleaf标签配合使用
	 */
	@Bean
	public ShiroDialect getShiroDialect(){
		return new ShiroDialect();
	}
	
	/**
	 * 5
	 * @return
	 */
	public RedisManager redisManager() {
	    RedisManager redisManager = new RedisManager();
	    redisManager.setExpire(60);//配置过期时间 单位秒
	    return redisManager;
	}
	
	/**
	 * 6
	 * @return
	 */
	public RedisCacheManager cacheManager() {
	    RedisCacheManager redisCacheManager = new RedisCacheManager();
	    redisCacheManager.setRedisManager(redisManager());	    
	    return redisCacheManager;
	}
	
	/**
	 * 7.RedisSessionDAO shiro sessionDao层的实现 通过redis
	 */
	public RedisSessionDAO redisSessionDAO() {
		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
		redisSessionDAO.setRedisManager(redisManager());
		return redisSessionDAO;
	}
	/**
	 * 8.shiro session的管理
	 */
	public DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionDAO(redisSessionDAO());
		return sessionManager;
	}
	
	
}
