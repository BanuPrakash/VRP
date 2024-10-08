package com.example.democonsumerest;

import com.example.democonsumerest.service.PostService;
import com.example.democonsumerest.service.UserService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AppConfig {

    @Bean
    PostService jsonPostService() {
        RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com");
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(restClient)).build();
        return  factory.createClient(PostService.class);
    }

    @Bean
    UserService jsonUserService() {
        RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com");
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(restClient)).build();
        return  factory.createClient(UserService.class);
    }

    @Bean(name="posts-pool")
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(10);
        executor.setThreadNamePrefix("POST-POOL");
        executor.initialize();
        return executor;
//        Executors.newFixedThreadPool(10);
    }

    @Bean(name="users-pool")
    public Executor asyncExecutorUsers() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(10);
        executor.setThreadNamePrefix("USER-POOL");
        executor.initialize();
        return executor;
//        Executors.newFixedThreadPool(10);
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().build();
    }
}
