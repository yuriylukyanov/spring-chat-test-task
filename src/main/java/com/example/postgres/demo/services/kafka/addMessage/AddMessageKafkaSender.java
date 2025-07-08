package com.example.postgres.demo.services.kafka.addMessage;

import com.example.postgres.demo.dto.AddMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AddMessageKafkaSender {
    private final KafkaTemplate<String, AddMessage> kafkaTemplate;

    @Value("${kafka.topics.add-message}")
    private String addMessageTopicName;

    public AddMessageKafkaSender(KafkaTemplate<String, AddMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(AddMessage message) {
        log.info("Sending : {}", message);
        log.info("--------------------------------");

        kafkaTemplate.send(addMessageTopicName, message);
    }
}

