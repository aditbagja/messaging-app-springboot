package com.app.messaging.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.app.messaging.model.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaMessageConsumer {
    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeData(String message) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            Message messageData = mapper.readValue(message, Message.class);
            log.info("\nMessage Sender: {}, \nMessage Receiver: {}, \nMessage Content: {}", messageData.getSender(),
                    messageData.getReceiver(), messageData.getMessageContent());

            // Kita bisa olah data tersebut misalkan menyimpannya ke Database, dll.
        } catch (JsonProcessingException e) {
            log.error("Error converting String to JSON: ", e.getLocalizedMessage());
        }
    }
}
