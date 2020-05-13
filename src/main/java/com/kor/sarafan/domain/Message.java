package com.kor.sarafan.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Data
/*@JsonIdentityInfo( // уберает циклическую сеарелизацию
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)*/
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.id.class)
    private Long id;

    @JsonView(Views.idName.class)
    private String text;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @JsonView(Views.fullMessage.class)
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonView(Views.fullMessage.class)
    private User author;

    @OneToMany(mappedBy = "message", orphanRemoval = true)
    @JsonView(Views.fullMessage.class)
    //@JsonManagedReference //УБЕРАЕТ ЦИКЛИЧЕСКИЕ ССЫЛКИ
    private List<Comment> comments;

    @JsonView(Views.fullMessage.class)
    private String link;
    @JsonView(Views.fullMessage.class)
    private String linkTitle;
    @JsonView(Views.fullMessage.class)
    private String linkDescription;
    @JsonView(Views.fullMessage.class)
    private String linkCover;
}
