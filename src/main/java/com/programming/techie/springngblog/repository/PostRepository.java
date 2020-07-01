/**
 * @author crystaldlogan
 *
 */
package com.programming.techie.springngblog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.programming.techie.springngblog.model.Post;


@RepositoryRestResource(collectionResourceRel = "posts", path = "posts")
public interface PostRepository extends MongoRepository<Post, Long> {
	//List<Post> findAllPost(@Param("name") String name);
	//List<Post> findPostByTitle(@Param("title") String title);
}
