package com.inuflo.springboot.app.item;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
	
	@Bean("clienteRest")
	@LoadBalanced //Utilizar Ribbon
	public RestTemplate registrarRestTemplate() {
		return new RestTemplate();
	}
}
