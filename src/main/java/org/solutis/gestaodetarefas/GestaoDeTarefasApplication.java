package org.solutis.gestaodetarefas;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(servers = {@Server(url = "/",  description = "Default Server URl")})
@SpringBootApplication

public class GestaoDeTarefasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoDeTarefasApplication.class, args);
	}

}
