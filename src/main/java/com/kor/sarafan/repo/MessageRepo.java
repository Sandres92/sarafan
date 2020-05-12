package com.kor.sarafan.repo;

import com.kor.sarafan.domain.Message;
import com.kor.sarafan.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Long> {
//    @EntityGraph(attributePaths = {"comments"})
//    Page<Message> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"comments"})
    Page<Message> findByAuthorIn(List<User> users, Pageable pageable);
}
