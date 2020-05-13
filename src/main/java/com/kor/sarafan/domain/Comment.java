package com.kor.sarafan.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table
@Data
@EqualsAndHashCode(of = "id")
public class Comment {
    @Id
    @GeneratedValue
    @JsonView(Views.idName.class)
    private Long id;

    @JsonView(Views.idName.class)
    private String text;

    @ManyToOne
    @JoinColumn(name = "mesasge_id")
    @JsonView(Views.fullComment.class)
    //@JsonBackReference //УБЕРАЕТ ЦИКЛИЧЕСКИЕ ССЫЛКИ
    private Message message;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    @JsonView(Views.idName.class)
    private User author;
}
