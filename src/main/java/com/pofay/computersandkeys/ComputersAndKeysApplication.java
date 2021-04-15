package com.pofay.computersandkeys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableAutoConfiguration
@EnableWebMvc
public class ComputersAndKeysApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComputersAndKeysApplication.class, args);
	}

}
