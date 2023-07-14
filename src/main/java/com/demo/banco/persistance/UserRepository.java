package com.demo.banco.persistance;

import com.demo.banco.model.AuthInfo;
import com.demo.banco.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByEmail(String email);

    Optional<User> findByAuthInfo(AuthInfo authInfo);
}
