package com.example.demo.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String EXCHANGE = "BookingExchange";
    public static final String WALLET_QUEUE = "wallet_queue";
    public static final String BOOKING_COMPLETION_QUEUE = "booking_completion_queue";
    public static final String ROLLBACK_QUEUE = "rollback_queue";
    @Bean
    public DirectExchange bookingExchange(){
        return new DirectExchange(EXCHANGE);
    }

    //json queue
    @Bean 
    public Queue walletQueue(){
        return new Queue(WALLET_QUEUE, true);
    }

    @Bean
    public Queue rollbackQueue(){
        return new Queue(ROLLBACK_QUEUE, true);
    }

    @Bean
    public Queue compeletionQueue(){
        return new Queue(BOOKING_COMPLETION_QUEUE, true);
    }

    @Bean
    public Binding walletBinding(Queue walletQueue, DirectExchange bookingExchange) {
        return BindingBuilder.bind(walletQueue).to(bookingExchange).with("routing_key_wallet");
    }
    @Bean
    public Binding completionBinding(Queue compeletionQueue, DirectExchange bookingExchange) {
        return BindingBuilder.bind(compeletionQueue).to(bookingExchange).with("routing_key_completion");
    }
    @Bean
    public Binding rollbackBinding(Queue rollbackQueue, DirectExchange bookingExchange) {
        return BindingBuilder.bind(rollbackQueue).to(bookingExchange).with("routing_key_rollback");
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory)
    {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
    

    
}
