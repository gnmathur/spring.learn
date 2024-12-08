package dev.gmathur.bookstore.orders.web.exception;

import dev.gmathur.bookstore.orders.domain.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.Instant;

// Takes care of cross-cutting concerns like exception handling
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final URI NOT_FOUND_TYPE = URI.create("https://example.org/not-found");
    private static final URI ISE_FOUND_TYPE = URI.create("https://example.org/server-error");
    private static final String SERVICE_NAME = "order-service";

    // Generic exception handler
    @ExceptionHandler(Exception.class)
    ProblemDetail handleUnhandledException(Exception e) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        pd.setTitle("Internal server error");
        pd.setType(ISE_FOUND_TYPE);
        pd.setProperty("service", SERVICE_NAME);
        pd.setProperty("error_category", "Generic");
        pd.setProperty("timestamp", Instant.now());
        return pd;
    }

    // Product not found exception handler
    @ExceptionHandler(OrderNotFoundException.class)
    ProblemDetail handleProductNotFoundException(Exception e) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        pd.setTitle("Product Not Found");
        pd.setType(NOT_FOUND_TYPE);
        pd.setProperty("service", SERVICE_NAME);
        pd.setProperty("error_category", "Generic");
        pd.setProperty("timestamp", Instant.now());
        return pd;
    }

}
