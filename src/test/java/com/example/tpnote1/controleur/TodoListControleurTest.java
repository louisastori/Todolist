package com.example.tpnote1.controleur;

import com.example.tpnote1.dto.Todo;
import com.example.tpnote1.dto.TodoList;
import com.example.tpnote1.exceptions.ResourceNotFoundException;
import com.example.tpnote1.services.TodoListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TodoListControleur.class)
class TodoListControleurTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoListService todoListService;

    private TodoList todoList;

    @BeforeEach
    void setUp() {
        todoList = new TodoList();
        todoList.setIdTodoList(1L);
        todoList.setNomTodoList("Liste Exemple");
    }

    @Test
    void creerTodoList_ShouldReturnCreatedStatus() throws Exception {
        when(todoListService.creerTodoList(any(TodoList.class))).thenReturn(todoList);

        mockMvc.perform(post("/todolists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Liste Exemple\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Liste Exemple"));
    }

    @Test
    void obtenirTodoListParId_ShouldReturnTodoList() throws Exception {
        when(todoListService.montreTodoList(1L)).thenReturn(todoList);

        mockMvc.perform(get("/todolists/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idTodoList").value(1))
                .andExpect(jsonPath("$.name").value("Liste Exemple"));
    }

    @Test
    void obtenirTodoListParId_ShouldReturnNotFound() throws Exception {
        when(todoListService.montreTodoList(1L)).thenThrow(new ResourceNotFoundException("TodoList non trouvée"));

        mockMvc.perform(get("/todolists/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("TodoList non trouvée"));
    }

/*
    @Test
    void modifierTodoList_ShouldReturnOkStatus() throws Exception {
        when(todoListService.modifierTodoList(any(TodoList.class))).thenReturn(todoList);

        mockMvc.perform(put("/todolists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idTodoList\":1,\"name\":\"Liste Modifiée\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Liste Exemple"));
    }*/
@Test
void testUpdateUser() {
    TodoList todoList = new TodoList();
    todoList.setNomTodoList("vroum vroum");

    TodoList savedTodoList = todoListService.modifierTodoList(todoList);

    savedTodoList.setNomTodoList("new vroum");
    TodoList updatedTodoList = todoListService.modifierTodoList(savedTodoList);

    assertThat(updatedTodoList.getNomTodoList()).isEqualTo("new vroum");
}

    @Test
    void supprimerTodoList_ShouldReturnNoContent() throws Exception {
        doNothing().when(todoListService).supprimerTodoList(1L);

        mockMvc.perform(delete("/todolists/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void supprimerTodoList_ShouldReturnNotFound() throws Exception {
        doThrow(new ResourceNotFoundException("TodoList non trouvée")).when(todoListService).supprimerTodoList(1L);

        mockMvc.perform(delete("/todolists/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("TodoList non trouvée"));
    }
}
