package com.inuflo.springboot.app.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.inuflo.springboot.app.commons.models.entity"})
public class SpringbootServicioProductoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioProductoApplication.class, args);
	}

}
