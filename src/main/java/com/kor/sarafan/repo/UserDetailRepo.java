package com.kor.sarafan.repo;

import com.kor.sarafan.domain.User;
        import org.springframework.data.jpa.repository.EntityGraph;
        import org.springframework.data.jpa.repository.JpaRepository;

        import java.util.Optional;

public interface UserDetailRepo extends JpaRepository<User, String> {
    @EntityGraph(attributePaths = {"subscriptions", "subscribers"})
    Optional<User> findById(String s);
}
