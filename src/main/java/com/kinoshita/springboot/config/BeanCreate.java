package com.kinoshita.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kinoshita.springboot.utility.PaginationDialect;

@Configuration
public class BeanCreate {

	@Bean
	public PaginationDialect paginationDialect() {
		return new PaginationDialect();
	}
}
