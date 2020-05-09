package com.kor.sarafan.dto;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonView;
import com.kor.sarafan.domain.Views;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@JsonView(Views.id.class)
public class WsEventDto {
    private ObjectType objectType;
    private EventType eventType;

    @JsonRawValue
    private String body;

    public WsEventDto(ObjectType objectType, EventType eventType, String value) {
        this.objectType = objectType;
        this.eventType = eventType;

        this.body = value;
    }
}
