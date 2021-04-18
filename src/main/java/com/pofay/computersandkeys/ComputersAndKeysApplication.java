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
			Computer c1 = new Computer("X507UA", "ASUS", ComputerTypes.LAPTOP, "日本語", Arrays.asList("black", "silver"));
			Computer c2 = new Computer("A407UB", "ACER", ComputerTypes.LAPTOP, "EN", Arrays.asList("red", "blue"));
			Computer c3 = new Computer("C614RB", "IBM", ComputerTypes.LAPTOP, "EN", Arrays.asList("red"));
			Computer c4 = new Computer("14-dv0006TU", "HP", ComputerTypes.LAPTOP, "EN", Arrays.asList("silver"));
			Computer c5 = new Computer("GL704GV", "ASUS", ComputerTypes.LAPTOP, "EN", Arrays.asList("black", "white"));
			repo.saveAll(Arrays.asList(c1, c2, c3, c4, c5));
		};

	}

}
