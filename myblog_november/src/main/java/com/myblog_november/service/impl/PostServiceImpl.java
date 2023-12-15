package com.myblog_november.service.impl;

import com.myblog_november.entity.Post;
import com.myblog_november.exception.ResourceNotFound;
import com.myblog_november.payload.PostDto;
import com.myblog_november.payload.PostResponse;
import com.myblog_november.repository.PostRepository;
import com.myblog_november.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {


    @Autowired
    private ModelMapper modelMapper;
    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

//another way of creating bean by -constructor based injection


    @Override
    public PostDto createPost(PostDto postDto) {


        //Post post = new Post();
        Post post = modelMapper.map(postDto, Post.class);
        //       post.setTitle(postDto.getTitle());
//        post.setContent(postDto.getContent());
//      post.setDescription(postDto.getDescription());

        Post updatedPost = postRepository.save(post);


       // PostDto dto = new PostDto();

//        dto.setId(post.getId());
//        dto.setTitle(post.getTitle());
//        dto.setContent(post.getContent());
//        dto.setDescription(post.getDescription());

        PostDto dto = modelMapper.map(updatedPost, PostDto.class);

        return dto;
    }

    @Override
    public void deletePostById(int postId) {


        postRepository.findById((long) postId).orElseThrow(
                () -> new ResourceNotFound("post not found with id:" + postId)
        );

        postRepository.deleteById((long)postId);
    }

    @Override
    public PostDto getPostByPostId(int postId) {

        postRepository.findById((long) postId).orElseThrow(
                () -> new ResourceNotFound("post not found with id:" + postId)
        );
        Post post =postRepository.getById((long)postId);

        return mapToDto(post);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostDto> dtos = posts.stream().map(p-> mapToDto(p)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        PageRequest pagable = PageRequest.of(pageNo,pageSize,sort);
        Page<Post> all = postRepository.findAll(pagable);
        List<Post> posts = all.getContent();


        List<PostDto> dtos = posts.stream().map(p -> mapToDto(p)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(dtos);
        postResponse.setPageNo(all.getNumber());
        postResponse.setPageSize(all.getSize());
        postResponse.setLast(all.isLast());
        postResponse.setTotalElements((int)all.getTotalElements());
        postResponse.setTotalPages(all.getTotalPages());


        return postResponse;

    }

    PostDto mapToDto(Post post)
   {
       PostDto dto = new PostDto();

       dto.setId(post.getId());
       dto.setTitle(post.getTitle());
       dto.setContent(post.getContent());
       dto.setDescription(post.getDescription());

       return dto;
   }


}
