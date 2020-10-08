package com.dapenbi.heronline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.dapenbi.heronline.models"})  // scan JPA entities
public class HerOnlineMvcApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(HerOnlineMvcApplication.class, args);
	}

}

