package com.myblog_november.controller;


import com.myblog_november.payload.PostDto;
import com.myblog_november.payload.PostResponse;
import com.myblog_november.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {


    @Autowired
    private PostService postService;


    @PostMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto,
                                              BindingResult result)
    {
        if(result.hasErrors())
        {
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.CREATED);
        }
        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    //http://localhost/8081/api/posts/{postId}
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{postId}")
    public ResponseEntity<String> deletePostById(@PathVariable int postId)
    {
        postService.deletePostById(postId);
        return new ResponseEntity<>("post is deleted with id:" +postId, HttpStatus.OK);
    }

    @GetMapping("{postId}")
    public ResponseEntity<PostDto> getPostByPostId(@PathVariable int postId)
    {

        PostDto postByPostId = postService.getPostByPostId(postId);
        return new ResponseEntity<>(postByPostId, HttpStatus.OK);
    }


    //http://localhost:8081/api/posts?pageNo=0&pageSize=3&sortBy=description
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = "0",required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "3",required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id",required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc",required = false) String sortDir
    )
    {

        PostResponse allResponses = postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
        return allResponses;
    }
}
