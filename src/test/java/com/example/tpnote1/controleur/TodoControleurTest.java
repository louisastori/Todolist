package com.example.tpnote1.controleur;

import com.example.tpnote1.dto.Todo;
import com.example.tpnote1.exceptions.ResourceNotFoundException;
import com.example.tpnote1.services.TodoService;
import org.assertj.core.api.AbstractBigDecimalAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TodoControleur.class)
class TodoControleurTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

    private Todo todo;

    @BeforeEach
    void setUp() {
        todo = new Todo();
        todo.setIdTodo(1L);
        todo.setNomTodo("Tâche Exemple");
    }

    @Test
    void creerTodo_ShouldReturnCreatedStatus() throws Exception {
        when(todoService.creerTodo(any(Todo.class))).thenReturn(todo);

        mockMvc.perform(post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Tâche Exemple\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Tâche Exemple"));
    }

    @Test
    void obtenirTodoParId_ShouldReturnTodo() throws Exception {
        when(todoService.montreTodo(1L)).thenReturn(todo);

        mockMvc.perform(get("/todos/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idTodo").value(1))
                .andExpect(jsonPath("$.name").value("Tâche Exemple"));
    }

    @Test
    void obtenirTodoParId_ShouldReturnNotFound() throws Exception {
        when(todoService.montreTodo(1L)).thenThrow(new ResourceNotFoundException("Todo non trouvé"));

        mockMvc.perform(get("/todos/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Todo non trouvé"));
    }


/*
    @Test
    void modifierTodo_ShouldReturnOkStatus() throws Exception {
        when(todoService.modifierTodo(any(Todo.class))).thenReturn(todo);

        mockMvc.perform(put("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idTodo\":1,\"name\":\"Tâche Modifiée\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Tâche Exemple"));
    }*/

    @Test
    void testUpdateUser() {
        Todo todo = new Todo();
        todo.setNomTodo("vroum vroum");
        todo.setContenuTodo("blabla");

        Todo savedTodo = todoService.modifierTodo(todo);

        savedTodo.setNomTodo("new vroum");
        Todo updatedTodo = todoService.modifierTodo(savedTodo);

        assertThat(updatedTodo.getNomTodo()).isEqualTo("new vroum");
    }

    private <SELF extends AbstractBigDecimalAssert<SELF>> AbstractBigDecimalAssert<SELF> assertThat(String nomTodo) {
        return null;
    }

    @Test
    void supprimerTodo_ShouldReturnNoContent() throws Exception {
        doNothing().when(todoService).supprimerTodo(1L);

        mockMvc.perform(delete("/todos/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void supprimerTodo_ShouldReturnNotFound() throws Exception {
        doThrow(new ResourceNotFoundException("Todo non trouvé")).when(todoService).supprimerTodo(1L);

        mockMvc.perform(delete("/todos/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Todo non trouvé"));
    }
}
