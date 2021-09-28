package com.security.testcode.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}