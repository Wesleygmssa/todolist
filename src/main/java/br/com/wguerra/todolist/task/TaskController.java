package br.com.wguerra.todolist.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController {

  @Autowired // gerenciar a estancia do repositorio
  private ITaskRepository taskRepository;
  @PostMapping("/")
  public TaskModel create(@RequestBody TaskModel taskModel){
    System.out.println("Chegou no controller");
  var task =  this.taskRepository.save(taskModel);
  return task;
  }
}
