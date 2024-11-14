package com.example.tpnote1.services;

import com.example.tpnote1.dto.Todo;
import com.example.tpnote1.exceptions.ResourceAlreadyExistsException;
import com.example.tpnote1.exceptions.ResourceNotFoundException;
import com.example.tpnote1.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TodoServiceImplTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoServiceImpl todoService;

    private Todo todo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        todo = new Todo();
        todo.setIdTodo(1L);
        todo.setNomTodo("Tâche 1");
        todo.setContenuTodo("blalba");
        todo.setStatut("en cours");
    }

    @Test
    void creerTodo_ShouldCreateTodo() throws ResourceAlreadyExistsException {
        when(todoRepository.existsById(todo.getIdTodo())).thenReturn(false);
        when(todoRepository.save(any(Todo.class))).thenReturn(todo);

        Todo createdTodo = todoService.creerTodo(todo);

        assertNotNull(createdTodo);
        assertEquals("Tâche 1", createdTodo.getNomTodo());
        verify(todoRepository, times(1)).save(todo);
    }

    @Test
    void creerTodo_ShouldThrowExceptionWhenTodoAlreadyExists() {
        when(todoRepository.existsById(todo.getIdTodo())).thenReturn(true);

        assertThrows(ResourceAlreadyExistsException.class, () -> {
            todoService.creerTodo(todo);
        });

        verify(todoRepository, never()).save(any(Todo.class));
    }

    @Test
    void modifierTodo_ShouldUpdateExistingTodo() throws ResourceNotFoundException {
        when(todoRepository.existsById(todo.getIdTodo())).thenReturn(true);
        todo.setNomTodo("Tâche modifiée");

        todoService.modifierTodo(todo);

        verify(todoRepository, times(1)).save(todo);
    }

    @Test
    void modifierTodo_ShouldThrowExceptionWhenTodoNotFound() {
        when(todoRepository.existsById(todo.getIdTodo())).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> {
            todoService.modifierTodo(todo);
        });

        verify(todoRepository, never()).save(any(Todo.class));
    }

    @Test
    void supprimerTodo_ShouldDeleteTodo() throws ResourceNotFoundException {
        when(todoRepository.existsById(todo.getIdTodo())).thenReturn(true);

        todoService.supprimerTodo(todo.getIdTodo());

        verify(todoRepository, times(1)).deleteById(todo.getIdTodo());
    }

    @Test
    void supprimerTodo_ShouldThrowExceptionWhenTodoNotFound() {
        when(todoRepository.existsById(todo.getIdTodo())).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> {
            todoService.supprimerTodo(todo.getIdTodo());
        });

        verify(todoRepository, never()).deleteById(anyLong());
    }

    @Test
    void montreTodo_ShouldReturnTodo() {
        when(todoRepository.findById(todo.getIdTodo())).thenReturn(Optional.of(todo));

        Todo foundTodo = todoService.montreTodo(todo.getIdTodo());

        assertNotNull(foundTodo);
        assertEquals("Tâche 1", foundTodo.getNomTodo());
        verify(todoRepository, times(1)).findById(todo.getIdTodo());
    }

    @Test
    void montreTodo_ShouldThrowExceptionWhenTodoNotFound() {
        when(todoRepository.findById(todo.getIdTodo())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            todoService.montreTodo(todo.getIdTodo());
        });

        verify(todoRepository, times(1)).findById(todo.getIdTodo());
    }
}
