package com.app.loginAndRegister.repository;

import com.app.loginAndRegister.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUsername(String username);

    User findByIdAndDateOffIsNull(UUID id);

    User findByUsernameAndDateOffIsNull(String username);

    Set<User> findAllByDateOffIsNull();

}
