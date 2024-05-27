package com.app.messaging.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @Builder.Default
    private UUID messageId = UUID.randomUUID();

    private String sender;
    private String receiver;
    private String messageContent;

    @Builder.Default
    private Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());
}
