package dev.gmathur.catalog.domain;

// Class and method are non-public. They are only going to be used by the domain layer (service layer).
class ProductMapper {
    static Product toProduct(ProductEntity entity) {
        return new Product(
                entity.getCode(),
                entity.getName(),
                entity.getDescription(),
                entity.getImageUrl(),
                entity.getPrice() );
    }
}
