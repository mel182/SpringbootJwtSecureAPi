package com.melchior.vrolijk.secure_api.Secure.Web.API.controller;

import com.melchior.vrolijk.secure_api.Secure.Web.API.database.dbEnum.Category;
import com.melchior.vrolijk.secure_api.Secure.Web.API.model.Post;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/post")
@RestController
public class PostController {

    @GetMapping("/all")
    public Post getAllPost()
    {
        Post post = new Post();
        post.setTitle("Test title");
        post.setDescription("This is a test description");
        post.setCategory(Category.EDUCATIONAL);
        post.setLastUpdate(System.currentTimeMillis());
        post.setPublishedDate(System.currentTimeMillis());

        return post;
    }
}
