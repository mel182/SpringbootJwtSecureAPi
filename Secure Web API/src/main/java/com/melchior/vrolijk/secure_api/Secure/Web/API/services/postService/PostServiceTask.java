package com.melchior.vrolijk.secure_api.Secure.Web.API.services.postService;

import com.melchior.vrolijk.secure_api.Secure.Web.API.model.Post;
import java.util.List;

/**
 * This is the post service task interface.
 *
 * @author Melchior Vrolijk
 */
@SuppressWarnings("ALL")
public interface PostServiceTask
{
    //region Get all post
    /**
     * Get all post
     * @return The list of all posts
     */
    List<Post> allposts();
    //endregion

    //region Get post
    /**
     * Get post based on post ID provided
     * @param id The post ID
     * @return The target post
     */
    Post getPost(Long id);
    //endregion

    //region Add new post
    /**
     * Add a new post
     * @param post The {@link Post} instance
     * @return {@link Post} object containing data of the object stored
     */
    Post addPost(Post post);
    //endregion

    //region Remove post
    /**
     * Remove post
     * @param id The ID of the post that has to be removed
     * @return The {@link Post} instance containing data of object that has been removed
     */
    Post removePost(Long id);
    //endregion

    //region Update post
    /**
     * Update post
     * @param post The target {@link Post} instance containing the new data
     * @return {@link Post} containing the updated data.
     */
    Post updatePost(Post post);
    //endregion
}
