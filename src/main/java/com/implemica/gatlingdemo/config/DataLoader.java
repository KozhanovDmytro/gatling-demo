package com.implemica.gatlingdemo.config;

import com.implemica.gatlingdemo.entity.User;
import com.implemica.gatlingdemo.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

   private final UserRepository userRepository;

   private final PasswordEncoder passwordEncoder;

   @Autowired
   public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
      this.userRepository = userRepository;
      this.passwordEncoder = passwordEncoder;
   }


   @Override
   public void run(ApplicationArguments args) throws Exception {
      User user = new User(null, "admin", passwordEncoder.encode("admin"), true);
      userRepository.save(user);
   }
}
