package dev.gmathur.catalog;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;

// SpringBootTest annotation is used to tell Spring Boot to look for a main configuration class (one with
// @SpringBootApplication for instance) and use that to start a Spring application context. Its for an integration test.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // no conflict in pipeline
@Import(TestcontainersConfiguration.class)
public abstract class AbstractIT {
    @LocalServerPort // bind random port to localServerPort
    protected int localServerPort;

    @BeforeEach
    void setUp() {
        System.setProperty("server.port", String.valueOf(localServerPort));
        RestAssured.port = localServerPort;
    }

    // We can now use RestAssured to make HTTP requests to the application running on the random port.

}
