package com.autoservis.projekat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("model")
public class AutoServisWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoServisWebApplication.class, args);
	}

}
