package dev.gmathur.catalog.domain;

import org.springframework.data.jpa.repository.JpaRepository;

// It does not need to be public. It is only going to be used by the domain layer (service layer).
interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
