package org.dark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class MyApp {

	public static void main(String[] args) {
		SpringApplication.run(MyApp.class, args);
	}
}

