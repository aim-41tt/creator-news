package ru.example.creato_rnews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CreatorNewsApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(CreatorNewsApplication.class, args);
	}

}
