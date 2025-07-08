package com.example.postgres.demo.configuration;

import com.example.postgres.demo.dto.AddMessage;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ProducerFactory<String, AddMessage> addMessageProducerFactory() {
        return new DefaultKafkaProducerFactory<>(configProps(), new StringSerializer(), new JsonSerializer<>());
    }

    private Map<String, Object> configProps() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers);
        return configProps;
    }

    @Bean
    public KafkaTemplate<String, AddMessage> messageKafkaTemplate() {
        return new KafkaTemplate<>(addMessageProducerFactory());
    }
}

