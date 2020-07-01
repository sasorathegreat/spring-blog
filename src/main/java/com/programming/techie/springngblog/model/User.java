/**
 * User Model/Domain
 * @author crystaldlogan
 *
 */
package com.programming.techie.springngblog.model;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "T_USER")
public class User {

	@Id
    private String id;
    
	@NotBlank
	@Size(max = 20)
    private String userName;
    
	@NotBlank
	@Size(max = 120)
    private String password;
    
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    

    @DBRef
    private Set<Role> roles = new HashSet<>();
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public  Set<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<Role> roles) {
	    this.roles = roles;
	}
    
    
}
