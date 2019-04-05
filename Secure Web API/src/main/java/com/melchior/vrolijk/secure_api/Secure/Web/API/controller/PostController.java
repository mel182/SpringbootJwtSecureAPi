package com.melchior.vrolijk.secure_api.Secure.Web.API.controller;

import com.melchior.vrolijk.secure_api.Secure.Web.API.database.dbEnum.Category;
import com.melchior.vrolijk.secure_api.Secure.Web.API.model.Post;
import io.swagger.annotations.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "Posts", description = "Endpoint for manipulating available post")
@RequestMapping("/post")
@RestController
public class PostController {

    //region Get all available posts GET Api
    @GetMapping("/all")
    @Validated
    @ApiOperation(value = "Get all available post",
    notes = "An array containing all available post will be provided",
    response = Post.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code=200, message = "List of all available posts", response = Post[].class, responseHeaders = {
            }),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized since you are not authorized user"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    /**
     * Get all available posts GET API
     * @return List of all available posts
     */
    public List<Post> getAllPosts()
    {
        List<Post> posts = new ArrayList<>();

        Post post = new Post();
        post.setTitle("Test title");
        post.setDescription("This is a test description");
        post.setCategory(Category.EDUCATIONAL);
        post.setLastUpdate(System.currentTimeMillis());
        post.setPublishedDate(System.currentTimeMillis());
        posts.add(post);

        return posts;
    }
    //endregion

    //region Get a specific post instance GET API
    @ApiOperation(value = "Get a specific post",
            notes = "Get a specific post based on the ID provided",
            response = Post.class, responseContainer = "Post that corresponds to the ID provided")
    @ApiResponses(value = {
            @ApiResponse(code=200, message = "Post that corresponds to the ID provided. In case no post instance is found that corresponds with the ID provided it will return an empty response", response = Post[].class, responseHeaders = {
            }),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized since you are not authorized user"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/{id}")
    @Validated
    /**
     * Get a specific post based on the post ID GET API
     * @param id This is the post ID
     * @return The corresponding post based on the ID provided
     */
    public Post getPost( @ApiParam(value = "The post ID", required = true) @PathVariable long id)
    {
        Post post = new Post();
        post.setTitle("Test title");
        post.setDescription("This is a test description");
        post.setCategory(Category.EDUCATIONAL);
        post.setLastUpdate(System.currentTimeMillis());
        post.setPublishedDate(System.currentTimeMillis());
        return post;
    }
    //endregion

    @PostMapping("")
    public Post AddNewPost(@RequestBody Post newPost)
    {
        Post post = new Post();
        post.setTitle("Test title");
        post.setDescription("This is a test description");
        post.setCategory(Category.EDUCATIONAL);
        post.setLastUpdate(System.currentTimeMillis());
        post.setPublishedDate(System.currentTimeMillis());

        return post;
    }

    @PutMapping("/update/{id}")
    public Post updatePost(@RequestBody Post post, @PathVariable long id)
    {
        Post post2 = new Post();
        post.setTitle("Test title");
        post.setDescription("This is a test description");
        post.setCategory(Category.EDUCATIONAL);
        post.setLastUpdate(System.currentTimeMillis());
        post.setPublishedDate(System.currentTimeMillis());

        return post2;
    }

    @DeleteMapping("/{id}")
    public Post removePost(@PathVariable long id)
    {
        Post postR = new Post();
        postR.setTitle("Test title");
        postR.setDescription("This is a test description");
        postR.setCategory(Category.EDUCATIONAL);
        postR.setLastUpdate(System.currentTimeMillis());
        postR.setPublishedDate(System.currentTimeMillis());
        return postR;
    }
}
