package com.sahil.jobListing;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sahil.jobListing.Model.Post;


public interface SearchRepository {
	List<Post> searchFilter(String text);
}
