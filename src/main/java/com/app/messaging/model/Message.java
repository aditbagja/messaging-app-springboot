package com.app.messaging.model;

import java.sql.Timestamp;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID messageId;

    private String sender;
    private String receiver;
    private String messageContent;
    private Timestamp createdAt;
}
