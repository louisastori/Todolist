package fr.eiffel.sio.todolist.repository;

import fr.eiffel.sio.todolist.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
