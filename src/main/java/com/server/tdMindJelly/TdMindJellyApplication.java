package com.server.tdMindJelly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.server.tdMindJelly")
public class TdMindJellyApplication {

	public static void main(String[] args) {
		SpringApplication.run(TdMindJellyApplication.class, args);
	}

}
