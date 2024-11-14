package com.example.tpnote1.services;

import com.example.tpnote1.dto.TodoList;
import com.example.tpnote1.exceptions.ResourceAlreadyExistsException;
import com.example.tpnote1.exceptions.ResourceNotFoundException;

public interface TodoListService {

    TodoList creerTodoList(TodoList todoList) throws ResourceAlreadyExistsException;

    TodoList modifierTodoList(TodoList todoList) throws ResourceNotFoundException;

    void supprimerTodoList(Long idTodoList) throws ResourceNotFoundException;

    TodoList montreTodoList(Long idTodo);
}
