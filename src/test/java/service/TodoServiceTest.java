package service;

import fr.eiffel.sio.todolist.model.Todo;
import fr.eiffel.sio.todolist.repository.TodoRepository;
import fr.eiffel.sio.todolist.service.TodoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    public TodoServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateTodo() {
        Todo todo = new Todo("Faire les courses", false);
        when(todoRepository.save(todo)).thenReturn(todo);

        Todo createdTodo = todoService.createTodo(todo);

        assertNotNull(createdTodo);
        assertEquals("Faire les courses", createdTodo.getContenu());
    }

    @Test
    public void testGetTodoById() {
        Todo todo = new Todo("Faire les courses", false);
        when(todoRepository.findById(1L)).thenReturn(Optional.of(todo));

        Optional<Todo> retrievedTodo = todoService.getTodoById(1L);

        assertTrue(retrievedTodo.isPresent());
        assertEquals("Faire les courses", retrievedTodo.get().getContenu());
    }

    @Test
    public void testUpdateTodo() {
        // Objet existant
        Todo existingTodo = new Todo("Faire les courses", false);
        existingTodo.setId(1L);

        // Détails mis à jour
        Todo updatedDetails = new Todo("Faire le ménage", true);
        updatedDetails.setId(1L);

        // Configuration des comportements des mocks
        when(todoRepository.findById(1L)).thenReturn(Optional.of(existingTodo));
        when(todoRepository.save(any(Todo.class))).thenReturn(updatedDetails);

        // Appel du service pour mettre à jour le todo
        Todo updatedTodo = todoService.updateTodo(1L, updatedDetails);

        // Vérifications
        assertEquals("Faire le ménage", updatedTodo.getContenu());
        assertTrue(updatedTodo.isStatut());
    }

    @Test
    public void testDeleteTodoById() {
        todoService.deleteTodoById(1L);
        verify(todoRepository, times(1)).deleteById(1L);
    }
}
