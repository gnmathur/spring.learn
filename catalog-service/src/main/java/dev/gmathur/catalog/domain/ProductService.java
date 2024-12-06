package dev.gmathur.catalog.domain;

import dev.gmathur.catalog.web.ApplicationProperties;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
// Typically service layer is considered as a transactional boundary. Rollback is done if any exception is thrown in
// the service layer.
@Transactional
public class ProductService { // Its made public because its accessed by the controller layer.
    private final ProductRepository productRepository;
    private final ApplicationProperties applicationProperties;

    // This need not be public as its only going to be used by the Spring framework.
    ProductService(ProductRepository productRepository, ApplicationProperties applicationProperties) {
        this.productRepository = productRepository;
        this.applicationProperties = applicationProperties;
    }

    public PagedResult<Product> getProducts(int pageNo) {
        // Leverage Spring Data JPA to fetch all products; Paging should always be accompanied with sorting
        Sort sort = Sort.by("name").ascending();
        pageNo = pageNo <= 1 ? 0 : pageNo - 1; // Spring Data JPA is 0-based
        Pageable pageable = PageRequest.of(pageNo, applicationProperties.pageSize(), sort);
        Page<Product> productsPages = productRepository.findAll(pageable).map(ProductMapper::toProduct);

        return new PagedResult<>(
                productsPages.getContent(),
                productsPages.getTotalElements(),
                productsPages.getNumber() + 1, // Spring Data JPA is 0-based
                productsPages.getTotalPages(),
                productsPages.isFirst(),
                productsPages.isLast(),
                productsPages.hasNext(),
                productsPages.hasPrevious()
        );
    }
}
