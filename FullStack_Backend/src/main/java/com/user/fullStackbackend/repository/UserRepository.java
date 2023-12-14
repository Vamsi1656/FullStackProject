package com.user.fullStackbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.fullStackbackend.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
