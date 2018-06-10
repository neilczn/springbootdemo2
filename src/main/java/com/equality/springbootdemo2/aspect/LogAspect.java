package com.equality.springbootdemo2.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 日志切面
 * @author E-Quality
 *
 */
@Aspect
@Order(10)
@Component
public class LogAspect {
	
	private Logger logger  = LoggerFactory.getLogger(this.getClass());

	//两个..代表所有子目录，最后括号里的两个..代表所有参数
	@Pointcut("execution(public * com.equality.springbootdemo2.service..*.*(..))")
	public void logAspect() {}
	
	@Before("logAspect()")
	public void doBefore(JoinPoint joinPoint) throws Throwable{
		//System.out.println("logAspect:" + joinPoint);
	}
	
	
	@AfterThrowing(pointcut="logAspect()",throwing = "e")
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
        System.out.println("logAspect 例外通知(异常):"+e);
    }
	

}
