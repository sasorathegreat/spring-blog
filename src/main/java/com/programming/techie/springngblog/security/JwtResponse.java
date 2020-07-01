package com.programming.techie.springngblog.security;
/**
 * JWT Response POJO
 * @author crystaldlogan
 *
 */

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private String id;
	private String username;
	private String email;

	public JwtResponse(String accessToken, String id, String username) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		//this.email = email;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}