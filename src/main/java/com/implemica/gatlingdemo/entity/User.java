package com.implemica.gatlingdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @NotBlank
   @Column(name = "username")
   private String username;

   @NotBlank
   @Column(name = "password")
   private String password;

   @NotNull
   @Column(name = "enabled")
   private Boolean enabled;

}
