package com.example.forum.posts;

import com.example.forum.users.User;
import com.example.forum.users.UserRepository;
import org.apache.commons.collections4.IterableUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.AdditionalAnswers.returnsFirstArg;


@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {
    @InjectMocks
    PostService postService;

    @Mock
    Post post;

    @Mock
    UserRepository userRepository;

    @Mock
    PostRepository postRepository;

    @Test
    public void getPostByIdTest() {
        User user = new User(1, "username", "password", "email@email.com", Instant.now());
        Post post = new Post(1, "This is a post", user, "This is the post's text.");

        when(postRepository.findById(Long.valueOf(1))).thenReturn(Optional.of(post));

        Optional<Post> result = postService.getPostById(Long.valueOf(1));

        assertThat(result.get().getId()).isEqualTo(1);
        assertThat(result.get().getTitle()).isEqualTo("This is a post");
        assertThat(result.get().getUser()).isEqualTo(user);
    }

    @Test
    public void getAllPosts() {
        User user = new User(1, "username", "password", "email@email.com", Instant.now());
        List<Post> posts = new ArrayList<>();
        Post postOne = new Post(1, "This is a post", user, "This is the post's text.");
        Post postTwo = new Post(2, "This is another post", user, "Post text.");
        Post postThree = new Post(3, "And this is another post", user, "Post text for other post.");
        posts.add(postOne);
        posts.add(postTwo);
        posts.add(postThree);


        when(postRepository.findAll()).thenReturn(posts);

        Iterable<Post> result = postService.getAllPosts();

        assertThat(IterableUtils.size(result)).isEqualTo(3);

        // Converting Iterable to list to check that it contains certain strings
        List<Post> testList = new ArrayList<>();
        result.forEach(testList::add);

        assertThat(testList.contains(postOne));
        assertThat(testList.contains(postTwo));
        assertThat(testList.contains(postThree));
    }

    @Test
    public void createPostTest() {
        User user = new User(1, "username", "password", "email@email.com", Instant.now());
        PostRequest postRequest = new PostRequest(Long.valueOf(1), "this is title", Long.valueOf(user.getId()), "This is the post's text");

        when(userRepository.findById(postRequest.getUserId())).thenReturn(Optional.of(user));
        // Have to mock postRepository.save()
        when(postRepository.save(any(Post.class))).then(returnsFirstArg());

        Post result = postService.createPost(postRequest);

        assertThat(result.getUser()).isEqualTo(user);
        assertThat(result.getTitle()).isEqualTo("this is title");
    }

    @Test
    public void updatePostTest() {
        User user = new User(1, "username", "password", "email@email.com", Instant.now());
        Post post = new Post(1, "This is a post", user, "Some text for the post.");
        PostRequest postRequest = new PostRequest(Long.valueOf(1),
                "this is the new title",
                Long.valueOf(user.getId()),
                "Post text.");

        when(postRepository.findById(postRequest.getId())).thenReturn(Optional.of(post));
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(postRepository.save(any(Post.class))).then(returnsFirstArg());
        Post result = postService.updatePost(Long.valueOf(1), postRequest);

        // Will only update title for now
        assertThat(result.getTitle()).isEqualTo("this is the new title");
    }

    @Test
    public void deletePostTest() {
        User user = new User(1, "username", "password", "email@email.com", Instant.now());
        Post post = new Post(Long.valueOf(1), "this is the title", user, "Text for the post.");

        when(postRepository.findById(post.getId())).thenReturn(Optional.of(post));
        Post result = postService.deletePost(post.getId());
        assertThat(result.getUser()).isEqualTo(user);
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getTitle()).isEqualTo("this is the title");
    }
}
