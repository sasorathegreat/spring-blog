/**
 * @author crystaldlogan
 *
 */
package com.programming.techie.springngblog.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.programming.techie.springngblog.model.ERoles;
import com.programming.techie.springngblog.model.Role;



public interface RoleRepository extends MongoRepository<Role, String> {
	  Optional<Role> findByName(ERoles name);
}
