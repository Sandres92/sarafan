package com.kor.sarafan.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;


@Entity
@Data
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class UserSubscrioption {
    @EmbeddedId
    private UserSubscriptionId id;

    @MapsId("channelId")
    @ManyToOne
    @JsonView(Views.idName.class)
    @JsonIdentityReference
    @JsonIdentityInfo(
            property = "id",
            generator = ObjectIdGenerators.PropertyGenerator.class
    )
    private User channel;

    @MapsId("subscriberId")
    @ManyToOne
    @JsonView(Views.idName.class)
    @JsonIdentityReference
    @JsonIdentityInfo(
            property = "id",
            generator = ObjectIdGenerators.PropertyGenerator.class
    )
    private User subscriber;

    @JsonView(Views.idName.class)
    private boolean active;

    public UserSubscrioption(User channel, User subscriber) {
        this.channel = channel;
        this.subscriber = subscriber;

        this.id = new UserSubscriptionId(channel.getId(), subscriber.getId());
    }

    public UserSubscriptionId getId() {
        return id;
    }

    public void setId(UserSubscriptionId id) {
        this.id = id;
    }

    public User getChannel() {
        return channel;
    }

    public void setChannel(User channel) {
        this.channel = channel;
    }

    public User getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(User subscriber) {
        this.subscriber = subscriber;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
