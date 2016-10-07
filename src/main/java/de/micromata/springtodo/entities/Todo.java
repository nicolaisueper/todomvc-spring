package de.micromata.springtodo.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Nicolai Süper (n.sueper@micromata.de)
 */
@Document
public class Todo {

  @Id
  private String id;
  private String todo;

  public Todo() {
  }

  public String getId() {
    return id;
  }

  public String getTodo() {
    return todo;
  }

  public void setTodo(String todo) {
    this.todo = todo;
  }
}
