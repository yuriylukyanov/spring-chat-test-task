package com.example.postgres.demo.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {
    @Value("${kafka.topics.add-message}")
    private String addMessageTopicName;
    @Bean
    public NewTopic addMessageTopic() {
        return TopicBuilder.name(addMessageTopicName).build();
    }
}
