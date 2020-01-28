package com.implemica.gatlingdemo.jpa;

import com.implemica.gatlingdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
