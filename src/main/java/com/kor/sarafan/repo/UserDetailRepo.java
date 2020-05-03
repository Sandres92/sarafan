package com.kor.sarafan.repo;

import com.kor.sarafan.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepo extends JpaRepository<User, String> {

}
