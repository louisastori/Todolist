package controller;

import fr.eiffel.sio.todolist.controller.TodolistController;
import fr.eiffel.sio.todolist.exceptions.ExceptionHandlingAdvice;
import fr.eiffel.sio.todolist.model.Todolist;
import fr.eiffel.sio.todolist.service.TodolistService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest(TodolistController.class)

@ExtendWith(SpringExtension.class)
@WebMvcTest
@ContextConfiguration(classes = TodolistController.class)
@Import(ExceptionHandlingAdvice.class)
public class TodolistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodolistService todolistService;

    @Test
    public void testGetTodolistById() throws Exception {
        Todolist todolist = new Todolist("Perso", null);
        when(todolistService.getTodolistById(1L)).thenReturn(Optional.of(todolist));

        mockMvc.perform(MockMvcRequestBuilders.get("/todolists/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("Perso"));
    }

    @Test
    public void testCreateTodolist() throws Exception {
        Todolist todolist = new Todolist("Pro", null);
        when(todolistService.createTodolist(Mockito.any(Todolist.class))).thenReturn(todolist);

        mockMvc.perform(MockMvcRequestBuilders.post("/todolists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nom\": \"Pro\", \"todos\": []}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("Pro"));
    }

    @Test
    public void testDeleteTodolistById() throws Exception {
        doNothing().when(todolistService).deleteTodolistById(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/todolists/1"))
                .andExpect(status().isNoContent());
    }
}
