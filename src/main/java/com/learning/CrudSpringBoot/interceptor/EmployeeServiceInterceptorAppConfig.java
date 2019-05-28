package com.learning.CrudSpringBoot.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class EmployeeServiceInterceptorAppConfig implements WebMvcConfigurer {

	@Autowired
	EmployeeServiceInterceptor employeeServiceInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(employeeServiceInterceptor);
	}
}
