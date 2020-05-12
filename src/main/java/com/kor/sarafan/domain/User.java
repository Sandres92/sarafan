package com.kor.sarafan.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usr")
@Data
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id", "name"})
public class User implements Serializable {
    @Id
    @JsonView(Views.idName.class)
    private String id;
    @JsonView(Views.idName.class)
    private String name;
    @JsonView(Views.idName.class)
    private String userpic;
    private String email;
    @JsonView(Views.fullProfile.class)
    private String gender;
    @JsonView(Views.fullProfile.class)
    private String locale;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @JsonView(Views.fullProfile.class)
    private LocalDateTime lastVisit;

    @JsonView(Views.fullProfile.class)
    @OneToMany(
            mappedBy = "subscriber",
            orphanRemoval = true)
    private Set<UserSubscrioption> subscriptions = new HashSet<>();

    @JsonView(Views.fullProfile.class)
    @OneToMany(
            mappedBy = "channel",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private Set<UserSubscrioption> subscribers = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserpic() {
        return userpic;
    }

    public void setUserpic(String userpic) {
        this.userpic = userpic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public LocalDateTime getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(LocalDateTime lastVisit) {
        this.lastVisit = lastVisit;
    }

    public Set<UserSubscrioption> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Set<UserSubscrioption> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public Set<UserSubscrioption> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Set<UserSubscrioption> subscribers) {
        this.subscribers = subscribers;
    }
}
