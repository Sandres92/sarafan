package com.kor.sarafan.service;

import com.kor.sarafan.domain.Comment;
import com.kor.sarafan.domain.Message;
import com.kor.sarafan.domain.User;
import com.kor.sarafan.domain.Views;
import com.kor.sarafan.dto.EventType;
import com.kor.sarafan.dto.ObjectType;
import com.kor.sarafan.repo.CommentRepo;
import com.kor.sarafan.repo.MessageRepo;
import com.kor.sarafan.util.WsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.BiConsumer;

@Service
public class CommentService {
    private final CommentRepo commentRepo;

    private final BiConsumer<EventType, Comment> wsSender;

    @Autowired
    public CommentService(CommentRepo commentRepo, WsSender wsSender) {
        this.commentRepo = commentRepo;
        this.wsSender = wsSender.getSender(ObjectType.COMMENT, Views.fullComment.class);
    }

    public Comment create(Comment comment, User user) {
        comment.setAuthor(user);
        Comment commentFromDb = commentRepo.save(comment);

        wsSender.accept(EventType.CREATE, commentFromDb);

        return comment;
    }
}
