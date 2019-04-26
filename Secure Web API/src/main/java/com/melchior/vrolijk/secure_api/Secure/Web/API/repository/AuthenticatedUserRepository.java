package com.melchior.vrolijk.secure_api.Secure.Web.API.repository;

import com.melchior.vrolijk.secure_api.Secure.Web.API.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This is the custom Jpa Repository for authenticated users
 *
 * @see JpaRepository
 * @author Melchior Vrolijk
 */
@Repository
public interface AuthenticatedUserRepository extends JpaRepository<UserEntity,Long>
{
    //region find user by e-mail
    /**
     * Find user by e-mail since the e-mail address is also the username
     * @param email The user e-mail address
     * @return {@link UserDetails} optional
     */
    Optional<UserDetails> findByEmail(String email);
    //endregion
}
