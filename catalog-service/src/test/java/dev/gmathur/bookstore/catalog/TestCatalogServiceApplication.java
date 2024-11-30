package dev.gmathur.bookstore.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

public class TestCatalogServiceApplication {

	@Bean
	@ServiceConnection
	PostgreSQLContainer<?> postgresContainer() {
		return new PostgreSQLContainer<>(DockerImageName.parse("postgres:16-alpine"));
	}


	public static void main(String[] args) {
		SpringApplication.from(CatalogServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}