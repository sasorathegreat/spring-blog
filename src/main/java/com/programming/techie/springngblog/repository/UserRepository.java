/**
 * @author crystaldlogan
 *
 */
 
package com.programming.techie.springngblog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.programming.techie.springngblog.model.User;
import java.util.Optional;


public interface UserRepository extends MongoRepository<User, String> {
	Optional<User> findByUserName(String username);
}
