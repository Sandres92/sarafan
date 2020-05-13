package com.kor.sarafan.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.kor.sarafan.domain.User;
import com.kor.sarafan.domain.UserSubscrioption;
import com.kor.sarafan.domain.Views;
import com.kor.sarafan.service.ProfileServeice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        if (subscriber.equals(channel)) {
            return channel;
        }
        return profileServeice.changeSubscription(channel, subscriber);
    }

    @GetMapping("get-subscribers/{channelId}")
    @JsonView(Views.idName.class)
    public List<UserSubscrioption> subscribers(@PathVariable("channelId") User channel) {
        return profileServeice.getSubscribers(channel);
    }

    @PostMapping("change-status/{subscriberId}")
    @JsonView(Views.idName.class)
    public UserSubscrioption changeSubscriptionStatus(
            @AuthenticationPrincipal User channel,
            @PathVariable("subscriberId") User subscriber) {
        return profileServeice.changeSubscriptionStatus(channel, subscriber);
    }
}
