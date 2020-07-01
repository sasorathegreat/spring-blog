/**
 * This signup method is calling another method inside the AuthService class, 
 * which is mainly responsible to create the User object and storing it in the database. 
 * @author crystaldlogan
 *
 */
package com.programming.techie.springngblog.dto;

import java.util.HashSet;
import java.util.Set;



public class RegisterRequest {

	private String username;
    private String email;
    private String password;
    private Set<String> roles = new HashSet<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public  Set<String> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<String> roles) {
	    this.roles = roles;
	}
}
