package com.example.tpnote1.controleur;

import com.example.tpnote1.dto.TodoList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.tpnote1.services.TodoListService;

import java.net.URI;

@RestController
@RequestMapping("/todoList")
public class TodoListControleur {



    private TodoListService todoListService;

    @PostMapping
    public ResponseEntity createdTodoList(@RequestBody TodoList todoList) {
        TodoList creer_TodoList = todoListService.creerTodoList(todoList);
        return ResponseEntity.created(URI.create("/todoList/"+ creer_TodoList.getIdTodoList())).body(creer_TodoList);
    }

    @PutMapping
    public ResponseEntity updateTodoList(@RequestBody TodoList todoList) {
        todoListService.modifierTodoList(todoList);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/idTodoList")
    public ResponseEntity deleteTodoList(@PathVariable("idTodoList") Long idTodoList){
        todoListService.supprimerTodoList(idTodoList);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/idTodoList")
    public TodoList montrerTodoList(@PathVariable("idTodoList") Long idTodoList) {
        return todoListService.montreTodoList(idTodoList);
    }
}
