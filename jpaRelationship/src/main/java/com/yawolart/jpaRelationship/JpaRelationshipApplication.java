package com.yawolart.jpaRelationship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication//(exclude = DataSourceAutoConfiguration.class)
public class JpaRelationshipApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaRelationshipApplication.class, args);
		System.out.println("Amarjit: my server started");
	}

}
