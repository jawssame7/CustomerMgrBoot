package com.kinoshita.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kinoshita.springboot.utility.PaginationDialect;
import com.kinoshita.springboot.utility.PaginationUtility;

@Configuration
public class BeanCreate {

	@Bean
	public PaginationDialect paginationDialect() {
		return new PaginationDialect();
	}
	
	@Bean
	public PaginationUtility paginationUtility() {
		return new PaginationUtility();
	}
}
