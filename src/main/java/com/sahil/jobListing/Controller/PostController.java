package com.sahil.jobListing.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahil.jobListing.PostRepository;
import com.sahil.jobListing.SearchRepository;
import com.sahil.jobListing.Model.Post;
import java.util.Arrays;
import org.bson.Document;
import jakarta.servlet.http.HttpServletResponse;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@CrossOrigin
public class PostController {
	
	@Autowired
	PostRepository repo;
	@Autowired
	SearchRepository srepo;
	
	@GetMapping("/allPosts")
	public List<Post> getAllPost(){
		return repo.findAll();
	}
	
	
	@GetMapping("/posts/{text}")
	public List<Post> getAllPost(@PathVariable String text){
		return srepo.searchFilter(text);
	}
	
	@PostMapping("/post")
	public Post addPost(@RequestBody Post post) {
		return repo.save(post);
		
	}
	
}
