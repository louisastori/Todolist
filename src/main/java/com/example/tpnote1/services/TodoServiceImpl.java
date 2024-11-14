package com.example.tpnote1.services;

import com.example.tpnote1.dto.Todo;
import com.example.tpnote1.exceptions.ResourceAlreadyExistsException;
import com.example.tpnote1.exceptions.ResourceNotFoundException;

public class TodoServiceImpl implements TodoService {
    @Override
    public Todo creerTodo(Todo todo) throws ResourceAlreadyExistsException {
        return null;
    }

    @Override
    public Todo modifierTodo(Todo todo) throws ResourceNotFoundException {

        return todo;
    }

    @Override
    public void supprimerTodo(Long idTodo) throws ResourceNotFoundException {

    }

    @Override
    public Todo montreTodo(Long idTodo) {
        return null;
    }
}
