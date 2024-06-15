package com.example.demo.service;

import com.example.demo.dao.UserRepo;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepo userRepo;

  private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

  public User save(User user) {
    user.setPassword(encoder.encode(user.getPassword()));
    return userRepo.save(user);
  }
}
