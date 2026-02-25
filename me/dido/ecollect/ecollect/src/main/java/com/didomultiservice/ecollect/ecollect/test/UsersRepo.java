package com.didomultiservice.ecollect.ecollect.test;

import com.didomultiservice.ecollect.ecollect.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepo extends JpaRepository<Users, Long> {
    Optional<Users> findByLogin(String login);
}

