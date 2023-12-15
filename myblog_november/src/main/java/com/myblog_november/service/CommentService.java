package com.myblog_november.service;

import com.myblog_november.payload.CommentDto;

public interface CommentService {

    CommentDto saveComment(CommentDto dto, long postId);

    void deleteCommentById(long postId);

    CommentDto updateCommentById(CommentDto commentDto, long commentId);
}
