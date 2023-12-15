package com.myblog_november.service;
import com.myblog_november.payload.PostDto;
import com.myblog_november.payload.PostResponse;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface PostService {

    public PostDto createPost(PostDto postDto);

    public void deletePostById(int postId);

    PostDto getPostByPostId(int postId);

    List<PostDto> getAllPosts();

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
}

