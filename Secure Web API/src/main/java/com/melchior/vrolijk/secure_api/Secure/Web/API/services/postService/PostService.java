package com.melchior.vrolijk.secure_api.Secure.Web.API.services.postService;

import com.melchior.vrolijk.secure_api.Secure.Web.API.model.Post;

import java.util.List;

public interface PostService
{
    List<Post> allposts();
    Post getPost(Long id);
    void addPost(Post post);
    void removePost(Long id);
    void updatePost(Post post);
}
