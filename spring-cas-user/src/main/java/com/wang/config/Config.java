package com.wang.config;

import java.util.HashMap;
import java.util.Map;

import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
	
	@Bean("casFilterMap")
	public FilterRegistrationBean casFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(casFilter());
		Map<String, String>initParameters=new HashMap<String, String>();
		initParameters.put("casServerLoginUrl", "http://127.0.0.1:8080/cas/login");
		initParameters.put("serverName", "http://127.0.0.1:8001");
		registration.setInitParameters(initParameters);
		registration.addUrlPatterns("/*");
		registration.setOrder(1);
		return registration;
	}
	
	/**
	 *
	 * 该过滤器负责用户的认证工作，必须启用它
	 * @return
	 */
	@Bean
	public AuthenticationFilter casFilter() {
		AuthenticationFilter authenticationFilter=new AuthenticationFilter();
		return authenticationFilter; 
	}
	

	/**
	 * 该过滤器负责对Ticket的校验工作，必须启用它 
	 * @return
	 */
	@Bean
	public Cas20ProxyReceivingTicketValidationFilter casValidateFilter() {
		Cas20ProxyReceivingTicketValidationFilter casValidateFilter=new Cas20ProxyReceivingTicketValidationFilter();
		return  casValidateFilter;
	}
	
	
	@Bean
	public FilterRegistrationBean cas20ProxyReceivingTicketValidationFilter() { 
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(casValidateFilter());
		Map<String, String>initParameters=new HashMap<String, String>();
		initParameters.put("casServerUrlPrefix", "http://127.0.0.1:8080/cas");
		initParameters.put("serverName", "http://127.0.0.1:8001");
		registration.setInitParameters(initParameters);
		registration.addUrlPatterns("/*");
		registration.setOrder(2);
		return registration;
	}
	

	/**
	 * 与下面的监听器配合，实现单点退出
	 * @return
	 */
	@Bean
	public FilterRegistrationBean singleoutFilter() {
		FilterRegistrationBean signOutFilter=new FilterRegistrationBean();
		signOutFilter.setFilter(new SingleSignOutFilter());
		signOutFilter.addUrlPatterns("/*");
		signOutFilter.setOrder(3);
		return signOutFilter;
	}
	
	 @Bean
	    public ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> singleSignOutHttpSessionListener() {
	        ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> listener = new ServletListenerRegistrationBean<SingleSignOutHttpSessionListener>();
	        listener.setEnabled(true);
	        listener.setListener(new SingleSignOutHttpSessionListener());
	        listener.setOrder(4);
	        return listener;
	    }
	
}
