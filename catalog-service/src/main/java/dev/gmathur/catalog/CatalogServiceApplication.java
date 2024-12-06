package dev.gmathur.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
// @EnableConfigurationProperties(ApplicationProperties.class)
// or
@ConfigurationPropertiesScan // scans from the root package and finds all classes annotated with @ConfigurationProperties
public class CatalogServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatalogServiceApplication.class, args);
    }
}
