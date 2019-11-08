package org.simon.pascal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@SpringBootApplication(exclude = ThymeleafAutoConfiguration.class)
@ComponentScan(basePackages = {"org.ff4j.aop","org.simon.pascal"})
public class Ff4jAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ff4jAppApplication.class, args);
	}

}
