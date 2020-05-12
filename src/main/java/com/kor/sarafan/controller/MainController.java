package com.kor.sarafan.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.kor.sarafan.domain.User;
import com.kor.sarafan.domain.Views;
import com.kor.sarafan.dto.MessagePageDto;
import com.kor.sarafan.repo.UserDetailRepo;
import com.kor.sarafan.service.MessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class MainController {
    private final MessageService messageService;
    private final UserDetailRepo userDetailRepo;
    private final ObjectWriter messageWriter;
    private final ObjectWriter profileWriter;

    public MainController(MessageService messageService, ObjectMapper objectMapper, UserDetailRepo userDetailRepo) {
        this.messageService = messageService;
        this.userDetailRepo = userDetailRepo;

        ObjectMapper objectMapperTemp = objectMapper
                .setConfig(objectMapper.getSerializationConfig());
        this.messageWriter = objectMapperTemp
                .writerWithView(Views.fullMessage.class);
        this.profileWriter = objectMapperTemp
                .writerWithView(Views.fullProfile.class);
    }

    @Value("${spring.profile.active}")
    private String profile;

    @GetMapping
    public String main(Model model, @AuthenticationPrincipal User user) throws JsonProcessingException {
        HashMap<Object, Object> data = new HashMap<>();

        if (user != null) {
            User userFromDb = userDetailRepo.findById(user.getId()).get();

            model.addAttribute("profile", profileWriter.writeValueAsString(userFromDb));

            Sort sort = Sort.by(Sort.Direction.DESC, "id");
            PageRequest pageRequest = PageRequest.of(0, MessageController.MESSAGES_PER_PAGE, sort);
            MessagePageDto messagePageDto = messageService.findAll(pageRequest);

            String messages = messageWriter.writeValueAsString(messagePageDto.getMessages());

            model.addAttribute("messages", messages);
            data.put("currentPage", messagePageDto.getCurrentPage());
            data.put("totalPage", messagePageDto.getTotalPage());
        } else {
            model.addAttribute("messages", "[]");
            model.addAttribute("profile", "null");
        }

        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(profile));

        return "index";
    }
}
