package de.micromata.springtodo.repository;

import de.micromata.springtodo.entities.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Nicolai Süper (n.sueper@micromata.de)
 */
@Repository
public interface TodoRepo extends MongoRepository<Todo, String> {
  List<Todo> findByTodo(String todo);
}
