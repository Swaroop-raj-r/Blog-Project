package com.myblog_november.service.impl;

import com.myblog_november.entity.Comment;
import com.myblog_november.entity.Post;
import com.myblog_november.exception.ResourceNotFound;
import com.myblog_november.payload.CommentDto;
import com.myblog_november.repository.CommentRepository;
import com.myblog_november.repository.PostRepository;
import com.myblog_november.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CommentRepository commentRepo;

    @Autowired
    private PostRepository postRepo;
    @Override
    public CommentDto saveComment(CommentDto dto, long postId) {


        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFound("Post not found with id:" + postId)
        );


        Comment comment = modelMapper.map(dto,Comment.class);
//        Comment comment = new Comment();
//
//        comment.setId(dto.getId());
//        comment.setName(dto.getName());
//        comment.setEmail(dto.getEmail());
//        comment.setBody(dto.getBody());
        comment.setPost(post);
        
        

        Comment SavedComment = commentRepo.save(comment);


//        CommentDto commentDto = new CommentDto();
//
//        commentDto.setId(SavedComment.getId());
//        commentDto.setName(SavedComment.getName());
//        commentDto.setEmail(SavedComment.getEmail());
//        commentDto.setBody(SavedComment.getBody());

        CommentDto commentDto = modelMapper.map(SavedComment, CommentDto.class);

        return commentDto;
    }

    @Override
    public void deleteCommentById(long postId) {
        commentRepo.deleteById(postId);
    }

    @Override
    public CommentDto updateCommentById(CommentDto commentDto, long commentId) {

        Comment comment = commentRepo.findById(commentId).orElseThrow(
                () -> new ResourceNotFound("Post not found with id:" + commentId)
        );

        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        Comment updatedComment = commentRepo.save(comment);

        CommentDto dto = new CommentDto();

        dto.setName(updatedComment.getName());
        dto.setEmail(updatedComment.getEmail());
        dto.setBody(updatedComment.getBody());

        return dto;
    }
}
