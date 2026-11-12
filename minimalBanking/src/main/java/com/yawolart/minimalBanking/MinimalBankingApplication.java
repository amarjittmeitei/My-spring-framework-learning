package com.yawolart.minimalBanking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication//(exclude = DataSourceAutoConfiguration.class)
public class MinimalBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinimalBankingApplication.class, args);
		System.out.println("Amarjit: My minimal banking server started");
	}

}
