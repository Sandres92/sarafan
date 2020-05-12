package com.kor.sarafan.service;

import com.fasterxml.jackson.annotation.JsonView;
import com.kor.sarafan.domain.User;
import com.kor.sarafan.domain.Views;
import com.kor.sarafan.repo.UserDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@Service
public class ProfileServeice {
    private final UserDetailRepo userDetailRepo;

    @Autowired
    public ProfileServeice(UserDetailRepo userDetailRepo) {
        this.userDetailRepo = userDetailRepo;
    }

    public User changeSubscription(User channel, User subscriber) {
        Set<User> subscribers = channel.getSubscribers();

        if (subscribers.contains(subscriber)) {
            subscribers.remove(subscriber);
        } else {
            subscribers.add(subscriber);
        }

        return userDetailRepo.save(channel);
    }
}
