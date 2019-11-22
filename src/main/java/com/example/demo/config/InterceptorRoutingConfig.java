package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.demo.interceptor.IDValidationInterceptor;

@Configuration
public class InterceptorRoutingConfig extends WebMvcConfigurerAdapter{
	
	@Autowired
	IDValidationInterceptor interceptor;
	
	@Override
	   public void addInterceptors(InterceptorRegistry registry) {
	      // LogInterceptor apply to all URLs.
	      registry.addInterceptor(interceptor);
	   }
	
}
