package br.com.wguerra.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.wguerra.todolist.user.IUserRepository;
// import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
// import jakarta.servlet.ServletRequest;
// import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component // Serve para marcar a classe como um componente gerenciado pelo Spring
           // Framework.
public class FilterTaskauth extends OncePerRequestFilter {

  @Autowired //
  private IUserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    var servertPath = request.getServletPath();
    if (servertPath.equals("/task/")) {
      // Pegqar a autenticação (usuario e senha)

      var authorization = request.getHeader("Authorization");
      System.out.println("Authorization");
      System.out.println(authorization);

      // validar se o token é valido
      var authEncoded = authorization.substring("Basic".length()).trim();

      byte[] authDecode = Base64.getDecoder().decode(authEncoded);
      var authDecoded = new String(authDecode);
      int i = authDecoded.indexOf(':');

      var username = authDecoded.substring(0, i);
      var password = authDecoded.substring(i + 1);

      System.out.println(username);
      System.out.println(password);

      // Validar usuário

      var user = this.userRepository.findByUsername(username);

      if (user == null) {
        response.sendError(401, "Usuário não encontrado");
        return;
      } else {
        // validar senha
        var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
        if (passwordVerify.verified) {
          System.out.println("Senha ok");
          filterChain.doFilter(request, response);
        } else {
          response.sendError(401, "Senha inválida");
          return;
        }
        // tudo ok segue viagem

      }

    } else {
      filterChain.doFilter(request, response);

    }
  }

}

// public class FilterTaskauth implements Filter{

// @Override // Indica que o método a seguir substitui um método da interface
// Filter.
// public void doFilter(ServletRequest request, ServletResponse response,
// FilterChain chain)
// throws IOException, ServletException {
// // TODO Auto-generated method stub (Comentário gerado automaticamente para
// indicar que o método deve ser implementado)

// // Executar alguma ação (Aqui você pode incluir o código para processar a
// solicitação)
// System.out.println("Chegou no filtro"); // Esta linha imprime uma mensagem no
// console
// chain.doFilter(request, response); // Chama o próximo filtro na cadeia ou o
// servlet apropriado.
// }

// }
