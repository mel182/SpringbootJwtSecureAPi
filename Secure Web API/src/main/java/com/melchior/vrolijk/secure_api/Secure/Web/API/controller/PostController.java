package com.melchior.vrolijk.secure_api.Secure.Web.API.controller;

import com.melchior.vrolijk.secure_api.Secure.Web.API.controller.baseClasses.BaseSecurityControllerVerifier;
import com.melchior.vrolijk.secure_api.Secure.Web.API.database.dbEnum.Category;
import com.melchior.vrolijk.secure_api.Secure.Web.API.database.entity.PostEntity;
import com.melchior.vrolijk.secure_api.Secure.Web.API.model.Post;
import com.melchior.vrolijk.secure_api.Secure.Web.API.model.ResponseMessage;
import com.melchior.vrolijk.secure_api.Secure.Web.API.services.postService.PostService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.melchior.vrolijk.secure_api.Secure.Web.API.security.constant.SecurityConstantValue.AUTHORIZATION_HEADER_KEY;
import static com.melchior.vrolijk.secure_api.Secure.Web.API.security.constant.SecurityConstantValue.AUTHORIZATION_REQUIRED;

@SuppressWarnings("ALL")
@Api(tags = "Posts", description = "Endpoint for manipulating available post items")
@RequestMapping("/post")
@RestController
public class PostController extends BaseSecurityControllerVerifier
{
    //region Local instances
    @Autowired
    PostService postService;
    //endregion

    //region Get all available posts GET Api
    @GetMapping("/all")
    @Validated
    @ApiOperation(value = "Get all available post",
    notes = "An array containing all available post will be provided",
    response = Post[].class, responseContainer = "List")
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
    public ResponseEntity getAllPosts(@ApiParam(value = AUTHORIZATION_REQUIRED, required = true) @RequestHeader(AUTHORIZATION_HEADER_KEY) String authorization)
    {
        return ResponseEntity.ok(postService.allposts());
    }
    //endregion

    //region Get a specific post instance GET API
    @ApiOperation(value = "Get a specific post",
            notes = "Get a specific post based on the ID provided",
            response = Post.class, responseContainer = "Post that corresponds to the ID provided")
    @ApiResponses(value = {
            @ApiResponse(code=200, message = "Post that corresponds to the ID provided. In case no post instance is found that corresponds with the ID provided it will return an empty response",
                    response = Post.class,
                    responseHeaders = {}),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized since you are not authorized user"),
            @ApiResponse(code = 403, message = "Forbidden since you are not authorized to perform such action"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/{id}")
    @Validated
    /**
     * Get a specific post based on the post ID GET API
     * @param id This is the post ID
     * @return The corresponding post based on the ID provided
     */
    public ResponseEntity getPost(@ApiParam(value = AUTHORIZATION_REQUIRED, required = true) @RequestHeader(AUTHORIZATION_HEADER_KEY) String authorization, @ApiParam(value = "The post ID", required = true) @PathVariable long id)
    {
        Post correspondingPost = postService.getPost(id);
        if (correspondingPost != null)
        {
            return ResponseEntity.ok(correspondingPost);
        }

        return new ResponseEntity<>(new ResponseMessage("Error retrieving post item or it could not be found"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    //endregion

    //region Add a new post POST API
    @ApiOperation(value = "Create a new post ",
            response = Post.class, responseContainer = "The post data that has been added to the database")
    @ApiResponses(value = {
            @ApiResponse(code=200, message = "Post has been successfully created",
                    response = Post.class, responseHeaders = {
            }),
            @ApiResponse(code = 400, message = "Invalid or missing minimum required data"),
            @ApiResponse(code = 401, message = "Unauthorized since you are not authorized user"),
            @ApiResponse(code = 403, message = "Forbidden since you are not authorized to use this api"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @Validated
    /**
     * This is the add new post POST API
     * @param newPost The post object
     * @return Data of the post being added to the database
     */
    @PostMapping("")
    public ResponseEntity AddNewPost(@ApiParam(value = AUTHORIZATION_REQUIRED, required = true) @RequestHeader(AUTHORIZATION_HEADER_KEY) String authorization,@ApiParam(value = "New post data", required = true) @RequestBody Post newPost)
    {
        if (postService.getPostCategory(newPost.getCategory()) != Category.NONE)
        {
            newPost.setCreator(Long.valueOf(extractUserID(authorization)));
            return ResponseEntity.ok(postService.addPost(newPost));
        }

        return new ResponseEntity<>(new ResponseMessage("Invalid post item data"), HttpStatus.BAD_REQUEST);
    }
    //endregion

    //region Update a target post PUT API
    @ApiOperation(value = "Update a post",
            notes = "For updating a specific post instance, you must be the target post item original creator",
            response = Post.class, responseContainer = "Post that has been updated including the updated data")
    @ApiResponses(value = {
            @ApiResponse(code=200, message = "The corresponding post instance that has been updated including the updated values.",
                    response = Post.class, responseHeaders = {}),
            @ApiResponse(code = 400, message = "Invalid or missing minimum required data"),
            @ApiResponse(code = 401, message = "Unauthorized since you are not original creator"),
            @ApiResponse(code = 403, message = "Forbidden since you are not original creator"),
            @ApiResponse(code = 500, message = "Error updating post item or it could not be found")
    })
    @Validated
    /**
     * Update a target post instance based on the post ID provided PUT API
     * @param post The post instance that need to be updated
     * @param id The target post ID
     * @return The post instance including the updated values
     */
    @PutMapping("/update/{id}")
    public ResponseEntity updatePost(@ApiParam(value = AUTHORIZATION_REQUIRED, required = true) @RequestHeader(AUTHORIZATION_HEADER_KEY) String authorization, @ApiParam(value = "The updated post instance", required = true) @RequestBody Post post, @ApiParam(value = "The post ID", required = true) @PathVariable long id)
    {
        if (postService.getPostCategory(post.getCategory()) != Category.NONE)
        {
            post.setId(id);
            Post updatedPost = postService.updatePost(post);
            if (updatedPost != null)
            {
                return ResponseEntity.ok(updatedPost);
            }else{
                return new ResponseEntity<>(new ResponseMessage("Error updating post item or it could not be found"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(new ResponseMessage("Invalid or missing minimum required data"), HttpStatus.BAD_REQUEST);
    }
    //endregion

    //region Remove a post DELETE API
    @ApiOperation(value = "Remove a post",
            notes = "For removing a specific post can only be done by the root user, admins and original post creator",
            response = Post.class, responseContainer = "Details of the post that has been successfully removed")
    @ApiResponses(value = {
            @ApiResponse(code=200, message = "The corresponding post instance that has been removed.",
                    response = Post.class, responseHeaders = {}),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized since you are not a root user, admin or the original user"),
            @ApiResponse(code = 403, message = "Forbidden since you are not a root user, admin or the original user"),
            @ApiResponse(code = 500, message = "Error removing post or it could not be found")
    })
    @Validated
    /**
     * Remove a post based on the Post ID provided
     * @param id The corresponding post ID
     * @return The {@link PostEntity} instance containing the post details that has been deleted
     */
    @DeleteMapping("/{id}")
    public ResponseEntity removePost(@ApiParam(value = AUTHORIZATION_REQUIRED, required = true) @RequestHeader(AUTHORIZATION_HEADER_KEY) String authorization, @ApiParam(value = "The corresponding post ID", required = true) @PathVariable long id)
    {
        Post removedPost = postService.removePost(id);
        if (removedPost != null)
        {
            return ResponseEntity.ok(removedPost);
        }
        return new ResponseEntity<>(new ResponseMessage("Error removing post or it could not be found"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    //endregion
}
