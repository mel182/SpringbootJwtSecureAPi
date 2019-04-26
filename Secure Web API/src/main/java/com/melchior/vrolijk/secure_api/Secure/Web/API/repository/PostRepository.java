package com.melchior.vrolijk.secure_api.Secure.Web.API.repository;

import com.melchior.vrolijk.secure_api.Secure.Web.API.database.entity.PostEntity;
import com.melchior.vrolijk.secure_api.Secure.Web.API.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is the custom Jpa Repository for posts
 *
 * @see JpaRepository
 * @author Melchior Vrolijk
 */
@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> { }
