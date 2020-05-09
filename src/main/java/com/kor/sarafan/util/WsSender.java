package com.kor.sarafan.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.kor.sarafan.dto.EventType;
import com.kor.sarafan.dto.ObjectType;
import com.kor.sarafan.dto.WsEventDto;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

@Component
public class WsSender {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ObjectMapper objectMapper;

    public WsSender(SimpMessagingTemplate simpMessagingTemplate, ObjectMapper objectMapper) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.objectMapper = objectMapper;
    }

    public <T> BiConsumer<EventType, T> getSender(ObjectType objectType, Class view) {
        ObjectWriter objectWriter = objectMapper
                .setConfig(objectMapper.getSerializationConfig())
                .writerWithView(view);

        return (EventType eventType, T payload) -> {
            String value = null;
            try {
                value = objectWriter.writeValueAsString(payload);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            simpMessagingTemplate.convertAndSend("/topic/activity", new WsEventDto(objectType, eventType, value));
        };
    }
}
