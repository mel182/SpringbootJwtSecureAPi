package com.melchior.vrolijk.secure_api.Secure.Web.API.services.postService;

import com.melchior.vrolijk.secure_api.Secure.Web.API.database.dbEnum.Category;
import com.melchior.vrolijk.secure_api.Secure.Web.API.database.entity.PostEntity;
import com.melchior.vrolijk.secure_api.Secure.Web.API.model.Post;
import com.melchior.vrolijk.secure_api.Secure.Web.API.repository.PostRepository;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImplementation implements PostService
{
    @Autowired
    private PostRepository postRepository;

    public void setPostRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> allposts() {
//        List<Post> posts = postRepository.findAll();
//        return posts;
        return null;
    }

    @Override
    public Post getPost(Long id) {
//        Optional<Post> post = postRepository.findById(id);
//        return post.get();

        Post post = new Post();
        post.setCategory(Category.SPORT);
        post.setDescription("This is a test post");
        post.setTitle("Test");
        post.setPublishedDate(System.currentTimeMillis());
        post.setLastUpdate(System.currentTimeMillis());
        return post;
    }

    @Override
    public void addPost(Post post) {
//        postRepository.save(post);

        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(post.getTitle());
        postEntity.setLastUpdate(post.getLastUpdate());
        postEntity.setDescription(post.getDescription());
        postEntity.setCategory(post.getCategory());
        postEntity.setCreator(123456);


    }

    @Override
    public void removePost(Long id) {
//        postRepository.deleteById(id);
    }

    @Override
    public void updatePost(Post post) {
//        postRepository.save(post);
    }
}
