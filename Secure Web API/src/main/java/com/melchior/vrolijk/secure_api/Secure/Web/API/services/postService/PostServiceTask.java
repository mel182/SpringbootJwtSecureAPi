package com.melchior.vrolijk.secure_api.Secure.Web.API.services.postService;

import com.melchior.vrolijk.secure_api.Secure.Web.API.model.Post;

import java.util.List;

@SuppressWarnings("ALL")
public interface PostServiceTask
{
    List<Post> allposts();
    Post getPost(Long id);
    Post addPost(Post post);
    Post removePost(Long id);
    Post updatePost(Post post);
}
