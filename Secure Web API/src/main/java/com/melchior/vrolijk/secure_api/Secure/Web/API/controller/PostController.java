package com.melchior.vrolijk.secure_api.Secure.Web.API.controller;

import com.melchior.vrolijk.secure_api.Secure.Web.API.database.dbEnum.Category;
import com.melchior.vrolijk.secure_api.Secure.Web.API.database.entity.PostEntity;
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
    response = PostEntity.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code=200, message = "List of all available posts", response = PostEntity[].class, responseHeaders = {
            }),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized since you are not authorized user"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    /**
     * Get all available posts GET API
     * @return List of all available posts
     */
    public List<PostEntity> getAllPosts()
    {
        List<PostEntity> posts = new ArrayList<>();

        PostEntity post = new PostEntity();
        post.setTitle("Test title");
        post.setDescription("This is a test description");
        post.setCategory(Category.EDUCATIONAL);
        post.setpublishedDate(System.currentTimeMillis());
        post.setLastUpdate(System.currentTimeMillis());
        posts.add(post);

        return posts;
    }
    //endregion

    //region Get a specific post instance GET API
    @ApiOperation(value = "Get a specific post",
            notes = "Get a specific post based on the ID provided",
            response = PostEntity.class, responseContainer = "Post that corresponds to the ID provided")
    @ApiResponses(value = {
            @ApiResponse(code=200, message = "Post that corresponds to the ID provided. In case no post instance is found that corresponds with the ID provided it will return an empty response",
                    response = PostEntity.class,
                    responseHeaders = {}),
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
    public PostEntity getPost( @ApiParam(value = "The post ID", required = true) @PathVariable long id)
    {
        PostEntity post = new PostEntity();
        post.setTitle("Test title");
        post.setDescription("This is a test description");
        post.setCategory(Category.EDUCATIONAL);
        post.setLastUpdate(System.currentTimeMillis());
        post.setpublishedDate(System.currentTimeMillis());
        return post;
    }
    //endregion

    //region Add a new post POST API
    @ApiOperation(value = "Add a new post ",
            response = Post.class, responseContainer = "The post data that has been added to the database")
    @ApiResponses(value = {
            @ApiResponse(code=200, message = "Post details that has been added to the database.",
                    response = PostEntity.class, responseHeaders = {
            }),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized since you are not authorized user"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @Validated
    /**
     * This is the add new post POST API
     * @param newPost The post object
     * @return Data of the post being added to the database
     */
    @PostMapping("")
    public PostEntity AddNewPost(@ApiParam(value = "The new post that need to be added", required = true) @RequestBody Post newPost)
    {
        PostEntity post = new PostEntity();
        post.setTitle("Test title");
        post.setDescription("This is a test description");
        post.setCategory(Category.EDUCATIONAL);
        post.setLastUpdate(System.currentTimeMillis());
        post.setpublishedDate(System.currentTimeMillis());

        return post;
    }
    //endregion

    //region Update a target post PUT API
    @ApiOperation(value = "Update a post instance",
            notes = "For updating a specific post instance, the corresponding post ID must be provided",
            response = PostEntity.class, responseContainer = "Post that has been updated including the updated values")
    @ApiResponses(value = {
            @ApiResponse(code=200, message = "The corresponding post instance that has been updated including the updated values.",
                    response = PostEntity.class, responseHeaders = {}),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized since you are not authorized user"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @Validated
    /**
     * Update a target post instance based on the post ID provided PUT API
     * @param post The post instance that need to be updated
     * @param id The target post ID
     * @return The post instance including the updated values
     */
    @PutMapping("/update/{id}")
    public PostEntity updatePost(@ApiParam(value = "The updated post instance", required = true) @RequestBody Post post, @ApiParam(value = "The post ID", required = true) @PathVariable long id)
    {
        PostEntity post2 = new PostEntity();
        post2.setTitle("Test title");
        post2.setDescription("This is a test description");
        post2.setCategory(Category.EDUCATIONAL);
        post2.setLastUpdate(System.currentTimeMillis());
        post2.setpublishedDate(System.currentTimeMillis());
        return post2;
    }
    //endregion

    //region Remove a post DELETE API
    @ApiOperation(value = "Remove a post instance",
            notes = "For updating a specific post instance, the corresponding post ID must be provided",
            response = PostEntity.class, responseContainer = "Details of the post that has been successfully removed")
    @ApiResponses(value = {
            @ApiResponse(code=200, message = "The corresponding post instance that has been removed.",
                    response = PostEntity.class, responseHeaders = {}),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized since you are not authorized user"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @Validated
    /**
     * Remove a post based on the Post ID provided
     * @param id The corresponding post ID
     * @return The {@link PostEntity} instance containing the post details that has been deleted
     */
    @DeleteMapping("/{id}")
    public PostEntity removePost(@ApiParam(value = "The corresponding post ID", required = true) @PathVariable long id)
    {
        PostEntity postR = new PostEntity();
        postR.setTitle("Test title");
        postR.setDescription("This is a test description");
        postR.setCategory(Category.EDUCATIONAL);
        postR.setLastUpdate(System.currentTimeMillis());
        postR.setpublishedDate(System.currentTimeMillis());
        return postR;
    }
    //endregion
}
