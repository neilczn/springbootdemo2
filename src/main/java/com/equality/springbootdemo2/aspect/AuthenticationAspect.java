package com.equality.springbootdemo2.aspect;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 身份验证切面
 * @author E-Quality
 *
 */
@Aspect
@Order(5)
@Component
public class AuthenticationAspect {
	
	private Logger logger  = LoggerFactory.getLogger(this.getClass());
	
	@Pointcut("execution(public * com.equality.springbootdemo2.service..*.*(..))")
	public void authenticationAspect() {}
	
	/**
     * 前置通知
     */
	@Before("execution(public * com.equality.springbootdemo2.service..*.*(..))")
	public void doBefore(JoinPoint joinPoint) throws Throwable{
		System.out.println("authenticationAspect 前置通知:" + joinPoint);
	}
	
	/**
     * 后置通知
     * returnVal,切点方法执行后的返回值
     */
    @AfterReturning(value="execution(public * com.equality.springbootdemo2.service..*.*(..))",returning = "returnVal")
    public void AfterReturning(Object returnVal){
        System.out.println("authenticationAspect 后置通知...."+returnVal);
    }
	
	/**
     * 最终通知 无论什么情况下都会执行的方法 
     */
	@After("authenticationAspect()")
	public void doAfter(JoinPoint joinPoint) throws Throwable{
		System.out.println("authenticationAspect 最终通知:" + joinPoint);
	}
	
	@Around("execution(public * com.equality.springbootdemo2.service..*.*(..))")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕通知前....");
        
        Object[] args = joinPoint.getArgs();
        
                
        /*for (Object arg : args){
	      	//获取request请求
	        if(arg instanceof HttpServletRequest){
	            HttpServletRequest request = (HttpServletRequest) arg;
	            System.out.println(request.getSession().getId());	            
	        }
		}*/
        
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();        
        String i = request.getSession().getId();
        Object test =request.getAttribute("test");
        /*int i = 0;
        if(i == 0){
        	//Object result = new JsonResult(ResultCode.EXCEPTION, "测试抛出异常");
        	throw new RuntimeException("测试抛出异常:权限验证未通过");
        }    */    
        Object obj= (Object) joinPoint.proceed();
        System.out.println("环绕通知后....");
        return obj;
    }
	 
	/**
	 * 异常通知  注意LogAspect已经有了,所以此部分可以不要
	 * @param jp
	 * @param e
	 *//*
	@AfterThrowing(pointcut="authenticationAspect()",throwing = "e")
    public void doAfterThrowing(JoinPoint jp,Exception e){
		Object[] args = jp.getArgs();
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonlist = "";
		try {
			jsonlist = mapper.writeValueAsString(args);
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}
		
		logger.error("signature:"+ jp.getSignature() + ",args:" + jsonlist + ",exception:" +  e.getMessage());
        System.out.println(" authenticationAspect 例外通知(异常):"+e);
    }*/

}
