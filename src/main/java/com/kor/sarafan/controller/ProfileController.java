package com.kor.sarafan.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.kor.sarafan.domain.User;
import com.kor.sarafan.domain.Views;
import com.kor.sarafan.service.ProfileServeice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("profile")
public class ProfileController {
    private final ProfileServeice profileServeice;

    @Autowired
    public ProfileController(ProfileServeice profileServeice) {
        this.profileServeice = profileServeice;
    }

    @GetMapping("{id}")
    @JsonView(Views.fullProfile.class)
    public User get(@PathVariable("id") User user) {
        return user;
    }

    @PostMapping("change-subscription/{channelId}")
    @JsonView(Views.fullProfile.class)
    public User changeSubscription(
            @AuthenticationPrincipal User subscriber,
            @PathVariable("channelId") User channel) {
        if(subscriber.equals(channel)){
            return profileServeice.changeSubscription(channel, subscriber);
        }

        return channel;
    }
}
