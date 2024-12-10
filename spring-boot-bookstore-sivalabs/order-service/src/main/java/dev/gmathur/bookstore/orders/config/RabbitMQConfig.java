package dev.gmathur.bookstore.orders.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.gmathur.bookstore.orders.ApplicationProperties;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

@Configuration
class RabbitMQConfig {
    private final ApplicationProperties applicationProperties;

    public RabbitMQConfig(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    /**
     * If RabbitAdmin is being initialized after the context has started and RabbitMQ connectivity has not been
     * established at the time of initialization, exchange and binding declarations may not happen.
     * To resolve this, explicitly call rabbitAdmin.initialize() in a custom ApplicationListener:
     */
    @Bean
    public ApplicationListener<ContextRefreshedEvent> rabbitAdminInitializer(RabbitAdmin rabbitAdmin) {
        return event -> {
            System.out.println("Initializing RabbitAdmin");
            rabbitAdmin.initialize();
        };
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        System.out.println("Creating RabbitAdmin");
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true); // Ensure it starts automatically
        return rabbitAdmin;
    }

    @Bean
    DirectExchange exchange() {
        System.out.println("Creating exchange: " + applicationProperties.orderEventsExchange());
        return ExchangeBuilder.directExchange(applicationProperties.orderEventsExchange()).build();
    }

    @Bean
    Queue newOrdersQueue() {
        return QueueBuilder.durable(applicationProperties.newOrdersQueue()).build();
    }

    @Bean
    Binding newOrdersQueueBinding() {
        System.out.println("Binding newOrdersQueue to exchange");
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
