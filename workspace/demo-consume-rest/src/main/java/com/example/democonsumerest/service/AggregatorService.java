package com.example.democonsumerest.service;

import com.example.democonsumerest.dto.Post;
import com.example.democonsumerest.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class AggregatorService {
    private final PostService postService;
    private final UserService userService;

    @Async("users-pool")
    public CompletableFuture<List<User>> getUsers() {
        System.out.println(Thread.currentThread() + " getting users");
        // simulate delay
        return  CompletableFuture.completedFuture(userService.getUsers());
    }

    @Async("posts-pool")
    public CompletableFuture<List<Post>> getPosts() {
        System.out.println(Thread.currentThread() + " getting posts");
        // simulate delay
        return  CompletableFuture.completedFuture(postService.getPosts());
    }

}
