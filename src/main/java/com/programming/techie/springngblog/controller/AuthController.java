/**
 * @author crystaldlogan
 *
 */
package com.programming.techie.springngblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.programming.techie.springngblog.dto.LoginRequest;
import com.programming.techie.springngblog.dto.RegisterRequest;
import com.programming.techie.springngblog.security.JwtResponse;
import com.programming.techie.springngblog.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

	@Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);
      //TODO Add Logging
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?>  login (@RequestBody LoginRequest loginRequest) {
    	String jwtToken = authService.login(loginRequest);
    	//TODO Add Logging
    	return ResponseEntity.ok(new JwtResponse(
    			jwtToken, 
    			loginRequest.getPassword(), 
    			loginRequest.getUsername()));
    	
    }
    
    @PostMapping("/logout")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity logoutUser() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(null);
		//TODO Add Logging
		return ResponseEntity.ok(HttpStatus.OK);
		//return ResponseEntity.ok(new MessageResponse("logout successful"));
	}

}
