package br.com.wguerra.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Modificador
 * public
 * private
 * protected
 */

@RestController
@RequestMapping("/users")
public class UserController {
  /**
   * String
   * Integer
   * Double
   * Float
   * char (A C)
   * Date (data)
   * void
   */
  @Autowired
  private IUserRepository userRepository;

  @PostMapping("/")
  public ResponseEntity create(@RequestBody UserModel UserModel) {
    var user = this.userRepository.findByUsername(UserModel.getUsername());
    if (user != null) {
      // System.out.println("Usuário já existe")
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuário já existe");
    }

    var userCreated = this.userRepository.save(UserModel);
    return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
  }
}
