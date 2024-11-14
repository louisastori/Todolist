package com.example.tpnote1.services;

import com.example.tpnote1.dto.TodoList;
import com.example.tpnote1.exceptions.ResourceAlreadyExistsException;
import com.example.tpnote1.exceptions.ResourceNotFoundException;

public class TodoListServiceImpl implements TodoListService {


    @Override
    public TodoList creerTodoList(TodoList todoList) throws ResourceAlreadyExistsException {
        return null;
    }

    @Override
    public TodoList modifierTodoList(TodoList todoList) throws ResourceNotFoundException {

        return todoList;
    }

    @Override
    public void supprimerTodoList(Long idTodoList) throws ResourceNotFoundException {

    }

    @Override
    public TodoList montreTodoList(Long idTodo) {
        return null;
    }
}
