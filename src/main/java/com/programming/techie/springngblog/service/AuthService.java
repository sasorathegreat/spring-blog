/**
 * @author crystaldlogan
 *
 */
package com.programming.techie.springngblog.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.programming.techie.springngblog.dto.LoginRequest;
import com.programming.techie.springngblog.dto.RegisterRequest;
import com.programming.techie.springngblog.model.ERoles;
import com.programming.techie.springngblog.model.Role;
import com.programming.techie.springngblog.model.User;
import com.programming.techie.springngblog.repository.RoleRepository;
import com.programming.techie.springngblog.repository.UserRepository;
import com.programming.techie.springngblog.security.JwtProvider;

@Service
public class AuthService {

	@Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    //@Autowired
    private AuthenticationManager authenticationManager = new SampleAuthenticationManager();
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
	RoleRepository roleRepository;

    public void signup(RegisterRequest registerRequest) {
    	/*if (userRepository.existsByUsername(registerRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(registerRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}*/
		
        User user = new User();
        user.setUserName(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(encodePassword(registerRequest.getPassword()));

        Set<String> strRoles = registerRequest.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles.isEmpty() || strRoles == null) {
			Role userRole = roleRepository.findByName(ERoles.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERoles.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERoles.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERoles.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);
    }

    /**
     * encodePassword will take the input string password and encode the 
     * password to a hash variable which will store in the MongoDB
     * @param password
     * @return
     */
    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    /**
     * The authenticaition service login method will handle the LoginRequest username and password
     * from client and used the authenticationManager bean to perform the authentication
     * @param loginRequest
     * @return 
     */
	public String login(LoginRequest loginRequest) {
		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), 
						loginRequest.getPassword()));
				
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		String jwt = jwtProvider.generateJwtToken(authenticate);
		
		return jwt;
		
	}

	public Optional<UserDetailsImpl> getCurrentUser() {
		 UserDetailsImpl princpal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return Optional.of(princpal);
	}
}
