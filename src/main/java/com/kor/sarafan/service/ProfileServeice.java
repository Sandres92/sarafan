package com.kor.sarafan.service;

import com.kor.sarafan.domain.User;
import com.kor.sarafan.domain.UserSubscrioption;
import com.kor.sarafan.repo.UserDetailRepo;
import com.kor.sarafan.repo.UserSubscriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileServeice {
    private final UserDetailRepo userDetailRepo;
    private final UserSubscriptionRepo userSubscriptionRepo;

    @Autowired
    public ProfileServeice(UserDetailRepo userDetailRepo, UserSubscriptionRepo userSubscriptionRepo) {
        this.userDetailRepo = userDetailRepo;
        this.userSubscriptionRepo = userSubscriptionRepo;
    }

    public User changeSubscription(User channel, User subscriber) {

        List<UserSubscrioption> subscribtions = channel.getSubscribers()
                .stream()
                .filter(subscribtion -> subscribtion.getSubscriber().equals(subscriber))
                .collect(Collectors.toList());

        if (subscribtions.isEmpty()) {
            UserSubscrioption subscription = new UserSubscrioption(channel, subscriber);
            channel.getSubscribers().add(subscription);
        } else {
            channel.getSubscribers().removeAll(subscribtions);
        }

        return userDetailRepo.save(channel);
    }

    public List<UserSubscrioption> getSubscribers(User channel) {
        List<UserSubscrioption> byChannel = userSubscriptionRepo.findByChannel(channel);
        return byChannel;
    }

    public UserSubscrioption changeSubscriptionStatus(User channel, User subscriber) {
        UserSubscrioption subscription = userSubscriptionRepo.findByChannelAndSubscriber(channel, subscriber);
        subscription.setActive(!subscription.isActive());

        return userSubscriptionRepo.save(subscription);
    }
}
