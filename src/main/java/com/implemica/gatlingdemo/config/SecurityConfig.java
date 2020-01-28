package com.implemica.gatlingdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

/**
 * @author User
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   @Autowired
   private DataSource dataSource;


   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()
              .antMatchers("/hello").permitAll()
              .anyRequest()
              .authenticated()
              .and()
              .formLogin()
              .successHandler((httpServletRequest, httpServletResponse, authentication) -> {
                  httpServletResponse.setStatus(HttpStatus.OK.value());
              })
              .and()
              .exceptionHandling()
              .defaultAuthenticationEntryPointFor(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED), new AntPathRequestMatcher("/users"));

      http.csrf().disable();

      http.headers()
              .frameOptions()
              .sameOrigin();
   }

   @Autowired
   public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
      auth.jdbcAuthentication()
              .dataSource(dataSource)
              .usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username=?")
              .authoritiesByUsernameQuery("SELECT username, 'ROLE_USER' FROM users WHERE username=?");
   }

   @Bean
   public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }


}
