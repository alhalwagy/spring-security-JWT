package com.example.demo.config;

import com.example.demo.service.CustomUserDetails;
import com.example.demo.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
public class JwtFilter  extends OncePerRequestFilter {


  @Autowired
  private JwtService jwtService;

  @Autowired
  private ApplicationContext applicationContext;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
  String header  = response.getHeader("Authorization");

  String token = null;
  String username = null;

  if(header != null && header.startsWith("Bearer ")) {
    token = header.substring(7);
    username = jwtService.extractUserName(token);
  }

  if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

    UserDetails userDetails = applicationContext.getBean(CustomUserDetails.class).loadUserByUsername(username);
    if(jwtService.validateToken(token,userDetails)){
      UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request,response);
    }

  }



  }
}
