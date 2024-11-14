package service;

import fr.eiffel.sio.todolist.model.Todolist;
import fr.eiffel.sio.todolist.repository.TodolistRepository;
import fr.eiffel.sio.todolist.service.TodolistService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TodolistServiceTest {

    @Mock
    private TodolistRepository todolistRepository;

    @InjectMocks
    private TodolistService todolistService;

    public TodolistServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateTodolist() {
        Todolist todolist = new Todolist("Perso", null);
        when(todolistRepository.save(todolist)).thenReturn(todolist);

        Todolist createdTodolist = todolistService.createTodolist(todolist);

        assertNotNull(createdTodolist);
        assertEquals("Perso", createdTodolist.getNom());
    }

    @Test
    public void testGetTodolistById() {
        Todolist todolist = new Todolist("Pro", null);
        when(todolistRepository.findById(1L)).thenReturn(Optional.of(todolist));

        Optional<Todolist> retrievedTodolist = todolistService.getTodolistById(1L);

        assertTrue(retrievedTodolist.isPresent());
        assertEquals("Pro", retrievedTodolist.get().getNom());
    }
}
