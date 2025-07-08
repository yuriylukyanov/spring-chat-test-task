package com.example.postgres.demo.configuration;

import com.example.postgres.demo.dto.AddMessage;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumer {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.groupId}")
    private String groupId;

    @Bean
    public ConsumerFactory<String, AddMessage> addMessageConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerProps(), new StringDeserializer(), new JsonDeserializer<AddMessage>(AddMessage.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, AddMessage> addMessageKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, AddMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(addMessageConsumerFactory());
        return factory;
    }

    Map<String, Object> consumerProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

        return props;
    }
}
