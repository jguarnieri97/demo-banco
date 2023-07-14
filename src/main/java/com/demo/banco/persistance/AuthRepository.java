package com.demo.banco.persistance;

import com.demo.banco.model.AuthInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<AuthInfo, Long> {

    Optional<AuthInfo> findByToken(String token);

}
