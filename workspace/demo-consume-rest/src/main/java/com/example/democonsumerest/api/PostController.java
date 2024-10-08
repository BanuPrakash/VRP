package com.example.democonsumerest.api;

import com.example.democonsumerest.dto.Post;
import com.example.democonsumerest.dto.User;
import com.example.democonsumerest.service.AggregatorService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/posts")
@RequiredArgsConstructor
public class PostController {
    private final AggregatorService service;

    record PostsDTO(String title, String user) {}

    @GetMapping()
    public List<PostsDTO> getPosts() {
        CompletableFuture<List<Post>> posts = service.getPosts();  // non - blocking
        CompletableFuture<List<User>> users = service.getUsers();  // non - blocking

        // barrier
        List<Post> postList = posts.join(); // caller thread has to wait
        List<User> userList = users.join();

        return postList.stream().map(post -> {
            String username = userList.stream()
                    .filter(user -> user.id() == post.userId()).findFirst().get().name();
            return new PostsDTO(post.title(), username);
        }).collect(Collectors.toList());
    }
}
