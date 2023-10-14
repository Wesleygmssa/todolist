package br.com.wguerra.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

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
  @Autowired // genrenciar o ciclo de vida
  private IUserRepository userRepository;

  @PostMapping("/")
  public ResponseEntity create(@RequestBody UserModel userModel) {
    var user = this.userRepository.findByUsername(userModel.getUsername());
    if (user != null) {
      // System.out.println("Usuário já existe")
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
    }

    var passwordHasred = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());
    userModel.setPassword(passwordHasred);
    var userCreated = this.userRepository.save(userModel);
    return ResponseEntity.status(200).body(userCreated);
  }
}
