package br.com.wguerra.todolist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

// @Controller
@RestController
@RequestMapping("primeiraRota")
//http://localhost:8080/ -----
public class MinhaPrimeiraControle {
  
  /**
   * MÉTOOS DE ACESSO DO HTTP
   * GET - Buscar uma informação
   * POST - Adicionar um dado/informação
   * PUT -  Alterar um dado/info
   * DELETE - Remover um dado
   * PATCH - Alterar somente uma parte da info/dado
   * @return
   */

   //Método (Funcionalidade) de uma classe

   @GetMapping("/")
  public String primeiraMensagem(){
    return "Minha primeira mensagem";
  }
}
