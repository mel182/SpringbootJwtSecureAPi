package com.melchior.vrolijk.secure_api.Secure.Web.API.services.postService;

import com.melchior.vrolijk.secure_api.Secure.Web.API.converter.Converter;
import com.melchior.vrolijk.secure_api.Secure.Web.API.database.dbEnum.Category;
import com.melchior.vrolijk.secure_api.Secure.Web.API.database.entity.PostEntity;
import com.melchior.vrolijk.secure_api.Secure.Web.API.model.Post;
import com.melchior.vrolijk.secure_api.Secure.Web.API.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
@Service
public class PostService implements PostServiceTask
{
    @Autowired
    private PostRepository postRepository;

    public void setPostRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> allposts()
    {
        return Converter.convertToPostList(postRepository.findAll());
    }

    @Override
    public Post getPost(Long id) {

        Optional<PostEntity> correspondingPostEntity = postRepository.findById(id);
        if (correspondingPostEntity.isPresent())
        {
            return Converter.convertToPost(correspondingPostEntity.get());
        }
        return null;
    }

    @Override
    public Post addPost(Post post)
    {
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(post.getTitle());
        postEntity.setDescription(post.getDescription());
        postEntity.setCategory(getPostCategory(post.getCategory()));
        postEntity.setCreator(post.getCreator());
        postEntity.setpublishedDate(System.currentTimeMillis());
        postEntity.setLastUpdate(0);

        return Converter.convertToPost(postRepository.save(postEntity));
    }

    @Override
    public Post removePost(Long id)
    {
        Optional<PostEntity> postEntity = postRepository.findById(id);

        if (postEntity.isPresent())
        {
            postRepository.deleteById(id);
            return Converter.convertToPost(postEntity.get());
        }

        return null;
    }

    @Override
    public Post updatePost(Post post)
    {
        Optional<PostEntity> correspondingPostEntity = postRepository.findById(post.getId());

        if (correspondingPostEntity.isPresent())
        {
            PostEntity currentPost = correspondingPostEntity.get();
            PostEntity updatedPostEntity = new PostEntity();
            updatedPostEntity.setId(currentPost.getId());
            updatedPostEntity.setTitle(post.getTitle().trim().equals("")? currentPost.getTitle() : post.getTitle());
            updatedPostEntity.setDescription(post.getDescription().trim().equals("") ? currentPost.getDescription() : post.getDescription());
            updatedPostEntity.setCategory(getPostCategory(post.getCategory()) == Category.NONE ? currentPost.getCategory() : getPostCategory(post.getCategory()));
            updatedPostEntity.setCreator(currentPost.getCreator());
            updatedPostEntity.setLastUpdate(System.currentTimeMillis());

            return Converter.convertToPost(postRepository.save(updatedPostEntity));
        }

        return null;
    }

    public List<Post> getAllUserPosts(long id)
    {
        List<PostEntity> postEntities = postRepository.findAll();

        List<PostEntity> correspondingUserPostEntities = postEntities.stream()
                .filter(post -> post.getCreator() == id)
                .collect(Collectors.toList());

        return Converter.convertToPostList(correspondingUserPostEntities);
    }

    //region Get user role of the raw JWT
    /**
     * Get user role based based on the JWT token provided
     * @param token The raw JWT token
     * @return The user role extracted from the JWT token
     */
    public Category getPostCategory(String rawCategory)
    {
        return getCategories().stream().filter(availableCategory -> availableCategory.toString().equals(rawCategory))
                .findFirst().orElse(Category.NONE);
    }
    //endregion

    //region Get post category options
    /**
     * Get all valid available user roles
     * @return The list of valid available roles
     */
    private List<Category> getCategories()
    {
        return new ArrayList<>(EnumSet.allOf(Category.class));
    }
    //endregion
}
