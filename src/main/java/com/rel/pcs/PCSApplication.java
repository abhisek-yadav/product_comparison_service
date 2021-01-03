package com.rel.pcs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PCSApplication {

	public static void main(String[] args) {
		SpringApplication.run(PCSApplication.class, args);
	}

}
