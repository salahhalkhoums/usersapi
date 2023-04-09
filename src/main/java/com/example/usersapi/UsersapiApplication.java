package com.example.usersapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The main class for the Users API application.
 */
@SpringBootApplication
@EnableSwagger2
public class UsersapiApplication {

	/**
	 * The main entry point for the Users API application.
	 *
	 * @param args The command-line arguments passed to the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(UsersapiApplication.class, args);
	}

}