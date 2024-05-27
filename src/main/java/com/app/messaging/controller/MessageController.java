package com.app.messaging.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import com.app.messaging.model.Message;
import com.app.messaging.service.KafkaMessageProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private KafkaMessageProducer kafkaMessageProducer;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/send")
    public Message sendMessage(@Payload Message message) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            String json = mapper.writeValueAsString(message);
            kafkaMessageProducer.sendMessage(json);
            simpMessagingTemplate.convertAndSendToUser(message.getReceiver(), "/user",
                    message);

        } catch (JsonProcessingException e) {
            log.error("Error mapping JSON to String: ", e.getLocalizedMessage());
        }

        return message;
    }
}
