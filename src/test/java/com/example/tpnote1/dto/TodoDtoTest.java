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

class TodoDtoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void todoDto_ShouldPassValidation_WhenValid() {
        Todo todo = new Todo();
        todo.setNomTodo("Tâche Exemple");

        Set<ConstraintViolation<Todo>> violations = validator.validate(todo);
        assertTrue(violations.isEmpty(), "Les validations devraient être passées");
    }

    @Test
    void todoDto_ShouldFailValidation_WhenNameIsNull() {
        Todo todo = new Todo();
        todo.setNomTodo(null); // Nom nul

        Set<ConstraintViolation<Todo>> violations = validator.validate(todo);
        assertFalse(violations.isEmpty(), "La validation devrait échouer si le nom est null");
    }

    @Test
    void todoDto_ShouldFailValidation_WhenNameIsEmpty() {
        Todo todo = new Todo();
        todo.setNomTodo(""); // Nom vide

        Set<ConstraintViolation<Todo>> violations = validator.validate(todo);
        assertFalse(violations.isEmpty(), "La validation devrait échouer si le nom est vide");
    }

    @Test
    void todoDto_ShouldFailValidation_WhenNameExceedsMaxLength() {
        Todo todo = new Todo();
        todo.setNomTodo("Nom très long qui dépasse la longueur maximale..."); // Ajuste selon la limite réelle

        Set<ConstraintViolation<Todo>> violations = validator.validate(todo);
        assertFalse(violations.isEmpty(), "La validation devrait échouer si le nom dépasse la longueur maximale");
    }
}
