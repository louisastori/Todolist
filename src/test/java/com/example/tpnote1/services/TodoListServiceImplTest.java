package com.example.tpnote1.services;



import com.example.tpnote1.exceptions.ResourceAlreadyExistsException;
import com.example.tpnote1.exceptions.ResourceNotFoundException;
import com.example.tpnote1.repository.TodoListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import com.example.tpnote1.dto.TodoList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

public class TodoListServiceImplTest {

    @Mock
    private TodoListRepository todoListRepository;

    @InjectMocks
    private TodoListServiceImpl todoListService;

    private TodoList todoList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        todoList = new TodoList(14L, "bleu");
        todoList.setIdTodoList(1L);
        todoList.setNomTodoList("Liste de tâches");
    }


        @Test
        void creerTodoList_ShouldCreateTodoList() throws ResourceAlreadyExistsException {
            when(todoListRepository.existsById(todoList.getIdTodoList())).thenReturn(false);
            when(todoListRepository.save(any(TodoList.class))).thenReturn(todoList);

            TodoList createdTodoList = todoListService.creerTodoList(todoList);

            assertNotNull(createdTodoList);
            assertEquals("Liste de tâches", createdTodoList.getNomTodoList());
            verify(todoListRepository, times(1)).save(todoList);
        }

    private void assertEquals(String listeDeTâches, String nomTodoList) {
    }

    @Test
        void creerTodoList_ShouldThrowExceptionWhenTodoListAlreadyExists() {
            when(todoListRepository.existsById(todoList.getIdTodoList())).thenReturn(true);

            assertThrows(ResourceAlreadyExistsException.class, () -> {
                todoListService.creerTodoList(todoList);
            });

            verify(todoListRepository, never()).save(any(TodoList.class));
        }

        @Test
        void modifierTodoList_ShouldUpdateExistingTodoList() throws ResourceNotFoundException {
            when(todoListRepository.existsById(todoList.getIdTodoList())).thenReturn(true);
            todoList.setNomTodoList("Nouvelle liste");

            todoListService.modifierTodoList(todoList);

            verify(todoListRepository, times(1)).save(todoList);
        }

        @Test
        void modifierTodoList_ShouldThrowExceptionWhenTodoListNotFound() {
            when(todoListRepository.existsById(todoList.getIdTodoList())).thenReturn(false);

            assertThrows(ResourceNotFoundException.class, () -> {
                todoListService.modifierTodoList(todoList);
            });

            verify(todoListRepository, never()).save(any(TodoList.class));
        }

        @Test
        void supprimerTodoList_ShouldDeleteTodoList() throws ResourceNotFoundException {
            when(todoListRepository.existsById(todoList.getIdTodoList())).thenReturn(true);

            todoListService.supprimerTodoList(todoList.getIdTodoList());

            verify(todoListRepository, times(1)).deleteById(todoList.getIdTodoList());
        }

        @Test
        void supprimerTodoList_ShouldThrowExceptionWhenTodoListNotFound() {
            when(todoListRepository.existsById(todoList.getIdTodoList())).thenReturn(false);

            assertThrows(ResourceNotFoundException.class, () -> {
                todoListService.supprimerTodoList(todoList.getIdTodoList());
            });

            verify(todoListRepository, never()).deleteById(anyLong());
        }

        @Test
        void montreTodoList_ShouldReturnTodoList() {
            when(todoListRepository.findById(todoList.getIdTodoList())).thenReturn(Optional.of(todoList));

            TodoList foundTodoList = todoListService.montreTodoList(todoList.getIdTodoList());

            assertNotNull(foundTodoList);
            assertEquals("Liste de tâches", foundTodoList.getNomTodoList());
            verify(todoListRepository, times(1)).findById(todoList.getIdTodoList());
        }

        @Test
        void montreTodoList_ShouldThrowExceptionWhenTodoListNotFound() {
            when(todoListRepository.findById(todoList.getIdTodoList())).thenReturn(Optional.empty());

            assertThrows(ResourceNotFoundException.class, () -> {
                todoListService.montreTodoList(todoList.getIdTodoList());
            });

            verify(todoListRepository, times(1)).findById(todoList.getIdTodoList());
        }



















}
