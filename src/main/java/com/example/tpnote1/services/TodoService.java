package com.example.tpnote1.services;

import com.example.tpnote1.dto.Todo;
import com.example.tpnote1.exceptions.ResourceAlreadyExistsException;
import com.example.tpnote1.exceptions.ResourceNotFoundException;

public interface TodoService {

    Todo creerTodo(Todo todo) throws ResourceAlreadyExistsException;

    Todo modifierTodo(Todo todo) throws ResourceNotFoundException;

    void supprimerTodo(Long idTodo) throws ResourceNotFoundException;

    Todo montreTodo(Long idTodo);
}
