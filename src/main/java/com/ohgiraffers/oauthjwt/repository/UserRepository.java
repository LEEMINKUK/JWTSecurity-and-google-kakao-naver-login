package com.ohgiraffers.oauthjwt.repository;

import com.ohgiraffers.oauthjwt.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);
}