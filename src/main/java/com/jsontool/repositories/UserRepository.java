package com.jsontool.repositories;

import com.jsontool.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select * from users u where u.email = :email", nativeQuery = true)
    Optional<User> findUserByEmail(String email);
}
