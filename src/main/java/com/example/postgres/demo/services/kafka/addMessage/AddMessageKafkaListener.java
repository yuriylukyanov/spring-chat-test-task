package com.example.postgres.demo.services.kafka.addMessage;

import com.example.postgres.demo.dto.AddMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AddMessageKafkaListener {
    private final AddMessageHandler handler;

    public AddMessageKafkaListener(AddMessageHandler handler) {
        this.handler = handler;
    }

    @KafkaListener(topics = "${kafka.topics.add-message}", groupId = "${spring.kafka.groupId}",
            containerFactory = "addMessageKafkaListenerContainerFactory")
    void listener(AddMessage data) {
        log.info("Received message [{}] in group1", data);
        handler.createMessage(data);
    }
}
