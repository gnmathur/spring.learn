package dev.gmathur.catalog.web.controllers;

import dev.gmathur.catalog.AbstractIT;
import dev.gmathur.catalog.domain.Product;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

// Runs this script before every test
@Sql("/test-data.sql")
class ProductControllerTest extends AbstractIT {
    @Test
    void shouldReturnProducts() {
        given()
                .when()
                .get("/api/v1/products")
                .then()
                .statusCode(200);
    }

    @Test
    void shouldAlsoReturnProducts() {
        given().contentType(ContentType.JSON)
                .when()
                .get("/api/v1/products")
                .then()
                .statusCode(200)
                .body("data", hasSize(7))
                .body("totalElements", is(28))
                .body("pageNumber", is(1))
                .body("totalPages", is(4))
                .body("isFirst", is(true))
                .body("isLast", is(false))
                .body("hasNext", is(true))
                .body("hasPrevious", is(false));
    }

    @Test
    void shouldReturnProductByCode() {
        Product product = given().contentType(ContentType.JSON)
                .when()
                .get("/api/v1/products/{code}", "P123")
                .then()
                .statusCode(200)
                .assertThat()
                .extract()
                .body()
                .as(Product.class);

        assertThat(product.name()).isEqualTo("The Odyssey");
        assertThat(product.price()).isEqualTo(BigDecimal.valueOf(10.99));
        assertThat(product.code()).isEqualTo("P123");
        assertThat(product.description()).isEqualTo("The Odyssey is an epic poem attributed to Homer, chronicling the adventures of Odysseus as he returns home from the Trojan War.");
    }
}