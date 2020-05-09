package com.kor.sarafan.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.kor.sarafan.domain.Message;
import com.kor.sarafan.domain.Views;
import com.kor.sarafan.dto.EventType;
import com.kor.sarafan.dto.ObjectType;
import com.kor.sarafan.repo.MessageRepo;
import com.kor.sarafan.util.WsSender;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiConsumer;

@RestController
@RequestMapping("message")
public class MessageController {
    @Autowired
    private MessageRepo messageRepo;

    private final BiConsumer<EventType, Message> wsSender;

    @Autowired
    public MessageController(WsSender wsSender) {
        this.wsSender = wsSender.getSender(ObjectType.MESSAGE, Views.idName.class);
    }

    @GetMapping
    @JsonView(Views.idName.class)
    public List<Message> list() {
        return messageRepo.findAll();
    }

    @GetMapping("{id}")
    public Message getOne(@PathVariable("id") Message message) {
        return message;
    }

    @PostMapping
    public Message create(@RequestBody Message message) {
        message.setCreationDate(LocalDateTime.now());
        Message updateMessage = messageRepo.save(message);

        wsSender.accept(EventType.CREATE, updateMessage);

        return updateMessage;
    }

    @PutMapping("{id}")
    public Message update(@PathVariable("id") Message messageFromDb, @RequestBody Message message) {

        BeanUtils.copyProperties(message, messageFromDb, "id");

        Message updateMessage = messageRepo.save(messageFromDb);
        wsSender.accept(EventType.UPDATE, updateMessage);

        return updateMessage;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message) {
        System.out.println("test   " + message.getId() + "   " + message.getText());
        messageRepo.delete(message);
        wsSender.accept(EventType.REMOVE, message);
    }
}
