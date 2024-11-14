package fr.eiffel.sio.todolist.service;

import fr.eiffel.sio.todolist.model.Todolist;
import fr.eiffel.sio.todolist.repository.TodolistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodolistService {

    private final TodolistRepository todolistRepository;

    public TodolistService(TodolistRepository todolistRepository) {
        this.todolistRepository = todolistRepository;
    }

    public List<Todolist> getAllTodolists() {
        return todolistRepository.findAll();
    }

    public Optional<Todolist> getTodolistById(Long id) {
        return todolistRepository.findById(id);
    }

    public Todolist createTodolist(Todolist todolist) {
        return todolistRepository.save(todolist);
    }

    public Todolist updateTodolist(Long id, Todolist todolistDetails) {
        Todolist todolist = todolistRepository.findById(id).orElseThrow();
        todolist.setNom(todolistDetails.getNom());
        todolist.setTodos(todolistDetails.getTodos());
        return todolistRepository.save(todolist);
    }

    public void deleteTodolistById(Long id) {
        todolistRepository.deleteById(id);
    }
}
