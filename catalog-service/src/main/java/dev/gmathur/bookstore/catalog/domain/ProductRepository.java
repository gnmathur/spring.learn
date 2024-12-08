package dev.gmathur.bookstore.catalog.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// It does not need to be public. It is only going to be used by the domain layer (service layer).
// Does not need any test because we are only extending JpaRepository, which is a framework interface.
interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    // Spring data JPA will automatically implement this method for us by stripping the findBy prefix and parsing the
    // rest of the method name.
    Optional<ProductEntity> findByCode(String code);
}
