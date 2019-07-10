package com.equality.springbootdemo2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import com.equality.springbootdemo2.interceptor.LanguageInterceptor;

@Configuration  
public class WebConfiguration extends WebMvcConfigurerAdapter {  
   @Autowired  
    private LanguageInterceptor languageInterceptor;  
     
   @Bean  
    public LocaleResolver localeResolver() {  
        CookieLocaleResolver cl = new CookieLocaleResolver();  
        cl.setCookieName("lang");  
        return cl;  
    }  
    @Override  
    public void addInterceptors(InterceptorRegistry registry) {  
        registry.addInterceptor(languageInterceptor);  
    }  
}  
