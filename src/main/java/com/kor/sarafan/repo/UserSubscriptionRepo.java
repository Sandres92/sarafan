package com.kor.sarafan.repo;

import com.kor.sarafan.domain.User;
import com.kor.sarafan.domain.UserSubscrioption;
import com.kor.sarafan.domain.UserSubscriptionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSubscriptionRepo extends JpaRepository<UserSubscrioption, UserSubscriptionId> {
    List<UserSubscrioption> findBySubscriber(User user);

    List<UserSubscrioption> findByChannel(User channel);

    UserSubscrioption findByChannelAndSubscriber(User channel, User subscriber);
}
