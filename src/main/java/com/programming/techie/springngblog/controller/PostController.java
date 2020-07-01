/**
 * 
 */
package com.programming.techie.springngblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.programming.techie.springngblog.dto.PostDto;
import com.programming.techie.springngblog.service.PostService;

/**
 * @author crystaldlogan
 *
 */
@RestController
@RequestMapping("api/posts/")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	
	@PostMapping
	public ResponseEntity createPost(@RequestBody PostDto postDto) {
		postService.createPost(postDto);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<PostDto>> showAllPosts() {
		return new ResponseEntity<>(postService.showAllPosts(), HttpStatus.OK);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<PostDto> getSinglePost(@PathVariable @RequestBody String id) {
		return new ResponseEntity<PostDto>(postService.getSinglePost(id), HttpStatus.OK);
	}
	
	@PutMapping("/post/{id}")
	public ResponseEntity updatePost(@RequestBody PostDto postDto, @PathVariable String id) {
		postService.updatePost(postDto, id);
		return new ResponseEntity(HttpStatus.OK);
	}


}
