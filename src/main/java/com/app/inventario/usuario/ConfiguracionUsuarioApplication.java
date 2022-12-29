package com.app.inventario.usuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.reactive.function.client.WebClient;

import com.app.inventario.usuario.infrastructure.config.AxonConfig;

@SpringBootApplication
@Import({ AxonConfig.class })
public class ConfiguracionUsuarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfiguracionUsuarioApplication.class, args);
	}
	
	@Bean
	@LoadBalanced
	public WebClient.Builder loadBalancedWebClientBuilder() {
		return WebClient.builder();
	}

}
