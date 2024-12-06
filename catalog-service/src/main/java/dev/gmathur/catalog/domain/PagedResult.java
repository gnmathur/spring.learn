package dev.gmathur.catalog.domain;

import java.util.List;

// Public because its shared with the web layer
public record PagedResult<T>(
    List<T> data,
    long totalElements,
    int pageNumber,
    int totalPages,
    boolean isFirst,
    boolean isLast,
    boolean hasNext,
    boolean hasPrevious) { }
