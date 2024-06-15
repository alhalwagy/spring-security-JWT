package com.example.demo.service;


import com.example.demo.dao.UserRepo;
import com.example.demo.model.User;
import com.example.demo.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import  org.springframework.security.core.userdetails.UserDetailsService;


@Service
public class CustomUserDetails implements UserDetailsService
    {

      @Autowired
      private UserRepo userRepo;

      @Override
      public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByUsername(username);

        if (user == null) {
          throw new UsernameNotFoundException(username);
        }
        return new UserPrincipal(user);

      }
    }
