package fr.eiffel.sio.todolist.controller;

import fr.eiffel.sio.todolist.model.Todolist;
import fr.eiffel.sio.todolist.service.TodolistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todolists")
public class TodolistController {

    private final TodolistService todolistService;

    public TodolistController(TodolistService todolistService) {
        this.todolistService = todolistService;
    }

    @GetMapping
    public List<Todolist> getAllTodolists() {
        return todolistService.getAllTodolists();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todolist> getTodolistById(@PathVariable Long id) {
        return todolistService.getTodolistById(id)
                .map(todolist -> new ResponseEntity<>(todolist, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Todolist createTodolist(@RequestBody Todolist todolist) {
        return todolistService.createTodolist(todolist);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todolist> updateTodolist(@PathVariable Long id, @RequestBody Todolist todolistDetails) {
        return todolistService.getTodolistById(id)
                .map(todolist -> new ResponseEntity<>(todolistService.updateTodolist(id, todolistDetails), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodolistById(@PathVariable Long id) {
        todolistService.deleteTodolistById(id);
        return ResponseEntity.noContent().build();
    }
}
