package com.example.tpnote1.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TodoListDtoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    void todoListDto_ShouldPassValidation_WhenValid() {
        TodoList todoList = new TodoList();
        todoList.setNomTodoList("Ma Liste de Tâches");

        Set<ConstraintViolation<TodoList>> violations = validator.validate(todoList);
        assertTrue(violations.isEmpty(), "Les validations devraient être passées");
    }
    @Test
    void todoListDto_ShouldFailValidation_WhenNameIsNull() {
        TodoList todoList = new TodoList();
        todoList.setNomTodoList(null); // Nom nul

        Set<ConstraintViolation<TodoList>> violations = validator.validate(todoList);
        assertFalse(violations.isEmpty(), "La validation devrait échouer si le nom est null");
    }
    @Test
    void todoListDto_ShouldFailValidation_WhenNameIsEmpty() {
        TodoList todoList = new TodoList();
        todoList.setNomTodoList(""); // Nom vide

        Set<ConstraintViolation<TodoList>> violations = validator.validate(todoList);
        assertFalse(violations.isEmpty(), "La validation devrait échouer si le nom est vide");
    }
    @Test
    void todoListDto_ShouldFailValidation_WhenNameExceedsMaxLength() {
        TodoList todoList = new TodoList();
        todoList.setNomTodoList("Nom très long qui dépasse la longueur maximale..."); // Ajuste selon la limite réelle

        Set<ConstraintViolation<TodoList>> violations = validator.validate(todoList);
        assertFalse(violations.isEmpty(), "La validation devrait échouer si le nom dépasse la longueur maximale");
    }
}
