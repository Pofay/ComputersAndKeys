package com.pofay.computersandkeys;

import java.util.Arrays;

import com.pofay.computersandkeys.entities.Computer;
import com.pofay.computersandkeys.entities.ComputerTypes;
import com.pofay.computersandkeys.repositories.ComputersRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.pofay.computersandkeys")
@EnableWebMvc
public class ComputersAndKeysApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComputersAndKeysApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ComputersRepository repo) {

		return (args) -> {
			repo.deleteAll();
			Computer c = new Computer("X507UA", "ASUS", ComputerTypes.LAPTOP, "日本語", Arrays.asList("black", "silver"));
			repo.save(c);
		};

	}

}
