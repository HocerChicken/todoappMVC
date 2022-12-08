package com.demo.todoappMVC.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demo.todoappMVC.model.TodoValidator;

@Configuration
public class TodoConfig {

	@Bean
	public TodoValidator validator() {
		return new TodoValidator();
	}
}
