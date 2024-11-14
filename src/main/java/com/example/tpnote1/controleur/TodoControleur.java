package com.example.tpnote1.controleur;

import com.example.tpnote1.dto.Todo;
import com.example.tpnote1.services.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/todo")
public class TodoControleur {

    private TodoService todoService;

    @PostMapping
    public ResponseEntity createdTodo(@RequestBody Todo todo) {
        Todo creer_Todo = todoService.creerTodo(todo);
        return ResponseEntity.created(URI.create("/todo/"+ creer_Todo.getIdTodo())).body(creer_Todo);
    }

    @PutMapping
    public ResponseEntity updatedTodo(@RequestBody Todo todo) {
        todoService.modifierTodo(todo);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/idTodo")
    public ResponseEntity deleteTodo(@PathVariable("idTodo") Long idTodo) {
        todoService.supprimerTodo(idTodo);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/idTodo")
    public Todo montrerTodo(@PathVariable("idTodo") Long idTodo) {
        return todoService.montreTodo(idTodo);
    }
}
