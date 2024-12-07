package dev.gmathur.catalog.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.utility.TestcontainersConfiguration;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

// Slice tst for JPA tests. No need to load the entire application context.
// Default uses in-memory database, if
@DataJpaTest(
        properties = {
                "spring.tests.database.replace=none", // turns off the default behavior of replacing the datasource with an embedded database
                // note the tc in the URL, which tells Spring to use Testcontainers to start a PostgreSQL database
                "spring.datasource.url=jdbc:tc:postgresql:16-alpine:///catalog" // use testcontainers to start a PostgreSQL database
        }
)
// We could choose to use the TestcontainersConfiguration class to rely on external PG. That way it would not be necessary to use the @DataJpaTest annotation.
// This is not the best idea though because TestcontainersConfiguration class might also include other configurations,
// for example, starting RMQ or Kafka containers, which we don't need to test the ProductRepository.
// @Import(TestcontainersConfiguration.class)
@Sql("/test-data.sql")
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldGetAllProducts() {
        List<ProductEntity> products = productRepository.findAll();
        assertThat(products).hasSize(28);
    }

    @Test
    void shouldGetProductByCode() {
        ProductEntity product = productRepository.findByCode("P123").orElseThrow();
        assertThat(product.getName()).isEqualTo("The Odyssey");
        assertThat(product.getPrice()).isEqualTo(BigDecimal.valueOf(10.99));
        assertThat(product.getCode()).isEqualTo("P123");
        assertThat(product.getDescription()).isEqualTo("The Odyssey is an epic poem attributed to Homer, chronicling the adventures of Odysseus as he returns home from the Trojan War.");

    }

    @Test
    void shouldReturnEmptyWhenProductCodeDoesNotExist() {
        assertThat(productRepository.findByCode("invalid_product_code")).isEmpty();
    }
}