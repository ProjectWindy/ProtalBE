package com.example.PortalBE.repository;


import com.example.PortalBE.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface usersRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
