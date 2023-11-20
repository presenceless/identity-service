package cd.presenceless.identityservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitmqConfig {
    @Value("${rabbitmq.enrollment.exchange}")
    private String exchangeKey;

    @Value("${rabbitmq.enrollment.queue.identity-service}")
    private String identityServiceQueue;

    @Value("${rabbitmq.enrollment.routing-key.identity-service}")
    private String identityServiceRoutingKey;

    @Bean
    public Queue identityQueue() {
        return new Queue(identityServiceQueue);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchangeKey);
    }

    @Bean
    public Binding identityBinding(@Qualifier("identityQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(identityServiceRoutingKey);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
