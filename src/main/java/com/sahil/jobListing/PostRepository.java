package com.sahil.jobListing;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sahil.jobListing.Model.Post;

public interface PostRepository extends MongoRepository<Post, String>{

}
