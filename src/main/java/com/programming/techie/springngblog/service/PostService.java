/**
 * 
 */
package com.programming.techie.springngblog.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programming.techie.springngblog.dto.PostDto;
import com.programming.techie.springngblog.model.Post;
import com.programming.techie.springngblog.repository.PostRepository;
import com.programming.techie.springngblog.service.Exception.PostNotFoundException;

import java.util.stream.Collectors;

/**
 * @author crystaldlogan
 *
 */
@Service
public class PostService {
	@Autowired
	AuthService authService;
	@Autowired
	PostRepository postRepository;
	
	@Transactional
	public void createPost(PostDto dto) {
		Post post = mapFromDtoToPost(dto);
		postRepository.save(post);
	}

	@Transactional
	public List<PostDto> showAllPosts() {
		List<Post> posts = postRepository.findAll();
		return posts.stream().map(this::mapFromPostToDto).collect(Collectors.toList());
	}
	
	@Transactional
	public PostDto getSinglePost(String id) {
		Post post = postRepository.findById(id).orElseThrow(()->new PostNotFoundException("For Id " + id));
		return mapFromPostToDto(post);
	}
	
	private PostDto mapFromPostToDto(Post post) {
		PostDto postDto = new PostDto();
		postDto.setId(post.getId());
		postDto.setTitle(post.getTitle());
		postDto.setContent(post.getContent());
		postDto.setUsername(post.getUsername());
		
		return postDto;
	}
	
	private Post mapFromDtoToPost(PostDto postDto) {
		Post post = new Post();
		//post.setId(postDto.getId());
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setUsername(postDto.getUsername());
		UserDetailsImpl user = authService.getCurrentUser().orElseThrow(()->new IllegalArgumentException("No User Login"));
		post.setUsername(user.getUsername());
		post.setCreatedOn(Instant.now());
		post.setUpdatedOn(Instant.now());
		return post;
	}

	@Transactional
	public void updatePost(PostDto postDto, String id) {
		Post post = postRepository.findById(id).orElseThrow(()->new PostNotFoundException("For Id " + id));
		post.setContent(postDto.getContent());
		post.setTitle(postDto.getTitle());
		post.setUpdatedOn(Instant.now());
		postRepository.save(post);
		
	}
	

}
