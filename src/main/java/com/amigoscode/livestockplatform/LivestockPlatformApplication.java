package com.amigoscode.livestockplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class LivestockPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(LivestockPlatformApplication.class, args);
	}

}
