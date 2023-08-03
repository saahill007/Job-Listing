package com.sahil.jobListing;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.sahil.jobListing.Model.Post;

@Repository
public class ImplementSearch implements SearchRepository{

	@Autowired
	MongoClient client; 
	
	@Autowired
	MongoConverter converter;
	
	@Override
	public List<Post> searchFilter(String text) {
		
			List<Post> posts = new ArrayList<Post>();
			MongoDatabase database = client.getDatabase("sahil");
			MongoCollection<Document> collection = database.getCollection("JobPost");
			
			AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search", 
			    new Document("text", 
			    new Document("query", text)
			                .append("path", Arrays.asList("techs", "exp", "id", "desc", "profile")))), 
			    new Document("$sort", 
			    new Document("exp", 1L)), 
			    new Document("$limit", 5L)));
			
			result.forEach(doc->posts.add(converter.read(Post.class,doc)));
			
		
		return posts;
	}

}
