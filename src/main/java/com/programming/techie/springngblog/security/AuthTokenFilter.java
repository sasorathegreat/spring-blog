/**
 * 
 */
package com.programming.techie.springngblog.security;

/**
 * @author crystaldlogan
 *
 */
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.programming.techie.springngblog.service.UserDetailsServiceImpl;


public class AuthTokenFilter extends OncePerRequestFilter {
  @Autowired
  private JwtProvider jwtprovider;

  @Autowired
  private UserDetailsServiceImpl userDetailsService;

  private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);
  
  private final String HEADER = "Authorization";
  private final String PREFIX = "Bearer ";

  /**
   * Logic that loads the user from the database and attempts to authenticate the user 
   * and set the Security Context to Authenticate
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      String jwt = parseJwt(request);
      if (jwt != null && jwtprovider.validateJwtToken(jwt)) {
        String username = jwtprovider.getUserNameFromJwtToken(jwt);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
            userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    } catch (Exception e) {
      logger.error("Cannot set user authentication: {}", e);
    }

    filterChain.doFilter(request, response);
  }

  /**
   *  Grabs the Authorization Key from the Request Header
   *  Checks to see if it has text and the "Bearer" is prefixed to the value
   *  then extract the value
   * @param request
   * @return
   */
  private String parseJwt(HttpServletRequest request) {
    
    String headerAuth = request.getHeader("Authorization");
    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith(PREFIX)) {
        return headerAuth.substring(7, headerAuth.length());
      }

    return null;
  }
}