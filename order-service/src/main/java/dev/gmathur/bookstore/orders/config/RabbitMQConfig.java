package dev.gmathur.bookstore.orders.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.gmathur.bookstore.orders.ApplicationProperties;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RabbitMQConfig {
    private final ApplicationProperties applicationProperties;

    public RabbitMQConfig(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    DirectExchange exchange() {
        return ExchangeBuilder.directExchange(applicationProperties.orderEventsExchange()).build();
    }

    @Bean
    Queue newOrdersQueue() {
        return QueueBuilder.durable(applicationProperties.newOrdersQueue()).build();
    }

    @Bean
    Binding newOrdersQueueBinding() {
        return BindingBuilder.bind(newOrdersQueue())
                .to(exchange())
                .with(applicationProperties.newOrdersQueue()); // routing key could have been different than the queue name
    }

    @Bean
    Queue deliveredOrdersQueue() {
        return QueueBuilder.durable(applicationProperties.deliveredOrdersQueue()).build();
    }

    @Bean
    Binding deliveredOrdersQueueBinding() {
        return BindingBuilder.bind(deliveredOrdersQueue())
                .to(exchange())
                .with(applicationProperties.deliveredOrdersQueue());
    }

    @Bean
    Queue cancelledOrdersQueue() {
        return QueueBuilder.durable(applicationProperties.cancelledOrdersQueue()).build();
    }

    @Bean
    Binding cancelledOrdersQueueBinding() {
        return BindingBuilder.bind(cancelledOrdersQueue())
                .to(exchange())
                .with(applicationProperties.cancelledOrdersQueue());
    }

    @Bean
    Queue errorOrdersQueue() {
        return QueueBuilder.durable(applicationProperties.errorOrdersQueue()).build();
    }

    @Bean
    Binding errorOrdersQueueBinding() {
        return BindingBuilder.bind(errorOrdersQueue())
                .to(exchange())
                .with(applicationProperties.errorOrdersQueue());
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, ObjectMapper objectMapper) {
        final var rabbitMQTemplate = new RabbitTemplate(connectionFactory);
        rabbitMQTemplate.setMessageConverter(jackson2JsonMessageConverter(objectMapper));
        return rabbitMQTemplate;
    }

}
