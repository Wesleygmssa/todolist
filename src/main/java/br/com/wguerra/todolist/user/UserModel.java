package br.com.wguerra.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
// import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tb_users")
public class UserModel {

  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;

  // @Column(name = "usuario")
  @Column(unique = true)
  private String username;
  private String name;
  private String password;

  @CreationTimestamp
  private LocalDateTime createdAT;

  // getters setters
  // public void setUserName(String username) {
  // this.username = username;
  // }

  // public String getUserName() {
  // return this.username;
  // }

  // public void setName(String name) {
  // this.name = name;
  // }

  // public String getName() {
  // return this.name;
  // }

  // public void setPassword(String password) {
  // this.password = password;
  // }

  // public String getPassword() {
  // return this.password;
  // }

}
