/**
 * Post Model/Domain
 * @author crystaldlogan
 *
 */
package com.programming.techie.springngblog.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

public class Post {
	@Id
	private String id;
	
	@NotBlank
    private String title;
    
    @NotEmpty
    private String content;
   
    private Instant createdOn;
    
    private Instant updatedOn;
   
    @NotBlank
    private String username;
    
    public Post() {
        id = new ObjectId().toString();
    }
    
    public String getId() {
    	return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the createdOn
	 */
	public Instant getCreatedOn() {
		return createdOn;
	}

	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(Instant createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * @return the updatedOn
	 */
	public Instant getUpdatedOn() {
		return updatedOn;
	}

	/**
	 * @param updatedOn the updatedOn to set
	 */
	public void setUpdatedOn(Instant updatedOn) {
		this.updatedOn = updatedOn;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
    
    
}
