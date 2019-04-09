package com.melchior.vrolijk.secure_api.Secure.Web.API.repository;

import com.melchior.vrolijk.secure_api.Secure.Web.API.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticatedUserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByEmail(String email);
}