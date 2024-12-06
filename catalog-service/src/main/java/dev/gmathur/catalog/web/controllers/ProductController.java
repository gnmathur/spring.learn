package dev.gmathur.catalog.web.controllers;

import dev.gmathur.catalog.domain.PagedResult;
import dev.gmathur.catalog.domain.Product;
import dev.gmathur.catalog.domain.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Class is not public. Its only going to be used by the Spring framework. Its the same case with all the methods in
// this class. Note that we don't return ProductEntity objects from this class. We return Product objects. This is
// because the web layer should not know about the database layer. It should only know about the domain layer and also
// we don't want to expose our database schema to the outside world.
@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    PagedResult<Product> getProducts(@RequestParam(name = "page", defaultValue = "1") int page) {
        return productService.getProducts(page);
    }
}
