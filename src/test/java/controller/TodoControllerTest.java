package controller;

import fr.eiffel.sio.todolist.controller.TodoController;
import fr.eiffel.sio.todolist.controller.TodolistController;
import fr.eiffel.sio.todolist.exceptions.ExceptionHandlingAdvice;
import fr.eiffel.sio.todolist.model.Todo;
import fr.eiffel.sio.todolist.service.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest
@ContextConfiguration(classes = TodoController.class)
@Import(ExceptionHandlingAdvice.class)
public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

    @Test
    public void testGetTodoById() throws Exception {
        Todo todo = new Todo("Faire les courses", false);
        when(todoService.getTodoById(1L)).thenReturn(Optional.of(todo));

        mockMvc.perform(MockMvcRequestBuilders.get("/todos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contenu").value("Faire les courses"));
    }

    @Test
    public void testCreateTodo() throws Exception {
        Todo todo = new Todo("vroum vroum", false);
        when(todoService.createTodo(Mockito.any(Todo.class))).thenReturn(todo);

        mockMvc.perform(MockMvcRequestBuilders.post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"contenu\": \"vroum vroum\", \"statut\": false}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contenu").value("vroum vroum"));
    }

    @Test
    public void testDeleteTodoById() throws Exception {
        // Configuration du mock pour simuler la présence de l'élément
        Long todoId = 1L;
        Todo todo = new Todo();
        todo.setId(todoId);

        when(todoService.getTodoById(todoId)).thenReturn(Optional.of(todo));
        doNothing().when(todoService).deleteTodoById(todoId);

        // Exécution de la requête DELETE et vérification de la réponse
        mockMvc.perform(delete("/todos/{id}", todoId))
                .andExpect(status().isNoContent());

        // Vérification que le service a bien appelé la méthode de suppression
        verify(todoService, times(1)).deleteTodoById(todoId);
    }
}
