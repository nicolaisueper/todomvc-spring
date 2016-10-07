package de.micromata.springtodo.controller;

import de.micromata.springtodo.entities.Todo;
import de.micromata.springtodo.repository.TodoRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.List;

/**
 * @author Nicolai Süper (n.sueper@micromata.de)
 */
@Controller
public class TodoController {

  private static final Logger LOG = LoggerFactory.getLogger(TodoController.class);

  @Autowired
  private TodoRepo todoRepo;

  @GetMapping("/")
  String home(Model model) {
    model.addAttribute("todo", new Todo());
    if (todoRepo.findAll().size() > 0) {
      List<Todo> todos = todoRepo.findAll();
      Collections.reverse(todos);
      model.addAttribute("todos", todos);
    }
    return "base";
  }

  @PostMapping("/new")
  String newTodo(@ModelAttribute Todo todo) {
    todo = todoRepo.save(todo);
    LOG.info("New todo saved with id {} content {}", todo.getId(), todo.getTodo());
    return "redirect:/";
  }

  @GetMapping("/remove/{id}")
  String removeTodo(@PathVariable("id") String id) {
    Todo todo = this.todoRepo.findAll()
        .stream()
        .filter(o -> o.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> new RuntimeException("Couldn't find todo with id " + id));
    this.todoRepo.delete(todo);
    return "redirect:/";
  }
}
