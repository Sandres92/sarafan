package com.kor.sarafan.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
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
}
