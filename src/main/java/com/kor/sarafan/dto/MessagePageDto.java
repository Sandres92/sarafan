package com.kor.sarafan.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.kor.sarafan.domain.Message;
import com.kor.sarafan.domain.Views;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@JsonView(Views.fullMessage.class)
@Setter
@Getter
public class MessagePageDto {
    private List<Message> messages;
    private int currentPage;
    private int totalPage;
}
