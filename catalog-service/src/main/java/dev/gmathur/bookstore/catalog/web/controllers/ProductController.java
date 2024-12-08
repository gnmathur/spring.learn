package dev.gmathur.bookstore.catalog.web.controllers;

import dev.gmathur.bookstore.catalog.domain.PagedResult;
import dev.gmathur.bookstore.catalog.domain.Product;
import dev.gmathur.bookstore.catalog.domain.ProductNotFoundException;
import dev.gmathur.bookstore.catalog.domain.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{code}")
    ResponseEntity<Product> getProductByCode(@PathVariable String code) {
        System.out.println("code = " + code);
        return productService.getProductByCode(code)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> ProductNotFoundException.forCode(code)); // Exception handled in a global exception handler
    }
}
