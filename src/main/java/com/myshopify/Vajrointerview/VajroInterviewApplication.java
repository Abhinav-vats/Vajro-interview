package com.myshopify.Vajrointerview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class VajroInterviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(VajroInterviewApplication.class, args);
	}

}
