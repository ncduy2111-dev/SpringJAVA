package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByEmail(String email);

    User findByid(long id);

    void deleteByid(long id);

    boolean existsByEmail(String email);

    User findByEmail(String email);
}
