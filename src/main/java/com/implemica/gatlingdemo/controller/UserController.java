package com.implemica.gatlingdemo.controller;

import com.implemica.gatlingdemo.entity.User;
import com.implemica.gatlingdemo.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@Controller
public class UserController {

   @Autowired
   private UserRepository userRepository;

   @GetMapping("/users")
   public ResponseEntity<List<User>> getUsers() {
      return ResponseEntity.ok(userRepository.findAll());
   }

   @GetMapping("/users/{user-id}")
   public ResponseEntity<User> getUser(@PathVariable("user-id") Long userId) {
      Optional<User> userOptional = userRepository.findById(userId);

      if(userOptional.isPresent()) {
         return ResponseEntity.ok(userOptional.get());
      } else {
         return ResponseEntity.notFound().build();
      }

   }

   @PostMapping("/users")
   public ResponseEntity<User> createUser(@RequestBody User user) throws Exception {
      User save = userRepository.save(user);

      return ResponseEntity.created(new URI("/users/" + save.getId())).build();
   }


}
