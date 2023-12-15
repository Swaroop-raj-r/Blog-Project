package com.myblog_november.controller;

import com.myblog_november.payload.CommentDto;
import com.myblog_november.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    //  http://localhost:8081/api/comments/{postId}
    @PostMapping("{postId}")
    public ResponseEntity<CommentDto> saveComment(@RequestBody CommentDto commentDto, @PathVariable long postId)
    {

        CommentDto dtos = commentService.saveComment(commentDto, postId);

        return new ResponseEntity<>(dtos, HttpStatus.OK);

    }

    @DeleteMapping("{postId}")
    public ResponseEntity<String> deleteComment(@PathVariable long postId)
    {

           commentService.deleteCommentById(postId);

        return new ResponseEntity<>("comment is deleted with the Id"+postId, HttpStatus.OK);

    }

    @PutMapping("{commentId}")
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto, @PathVariable long commentId)
    {

        CommentDto dtos = commentService.updateCommentById(commentDto,commentId);

        return new ResponseEntity<>(dtos, HttpStatus.OK);

    }




}
