package com.example.demo.cotroller;

import com.example.demo.model.User;
import com.example.demo.service.JwtService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class UserController {

  @Autowired private UserService userService;

  @Autowired private AuthenticationManager authenticationManager;

  @Autowired private JwtService jwtService;

  @PostMapping("register")
  public User register(@RequestBody User user) {
    return userService.save(user);
  }

  @PostMapping("login")
  public String login(@RequestBody User user) {
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

    if (authentication.isAuthenticated()) {
      return jwtService.generateToken(user.getUsername());

    } else {
      return "fail to login";
    }
  }
}
