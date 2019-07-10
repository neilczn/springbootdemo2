package com.equality.springbootdemo2.interceptor;

import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Component  
public class LanguageInterceptor extends LocaleChangeInterceptor {  
    @Autowired  
    private LocaleResolver localeResolver;  
    @SuppressWarnings("rawtypes")
	@Override  
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  
            throws ServletException {  
        //获取   @pathvariable  的参数  /{lang}  
        Map map = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);  
        String lang = MapUtils.getString(map, "lang", "");  
       /* Locale l = new Locale(lang);  
        localeResolver.setLocale(request, response, l);*/
        if("zh".equals(lang)){
            localeResolver.setLocale(request, response, new Locale("zh", "CN"));
        }else if("en".equals(lang)){
            localeResolver.setLocale(request, response, new Locale("en", "US"));
        }
		return true;  
    }  
}  
