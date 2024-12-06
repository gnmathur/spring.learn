package dev.gmathur.catalog.domain;

import java.math.BigDecimal;

// Public because its shared with the web layer
public record Product(
        String code,
        String name,
        String description,
        String imageUrl,
        BigDecimal price) { }
