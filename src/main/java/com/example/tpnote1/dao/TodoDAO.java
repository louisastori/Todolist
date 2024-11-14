package com.example.tpnote1.dao;

import com.example.tpnote1.dto.Todo;
import com.example.tpnote1.repository.TodoRepository;
import com.example.tpnote1.services.TodoService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.springframework.beans.factory.annotation.Autowired;

public class TodoDAO implements TodoService {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("connect");

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public Todo creerTodo(Todo todo) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        em.persist(todo);
        tx.begin();
        tx.commit();
        return todo;
    }

    @Override
    public Todo modifierTodo(Todo todo) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();


        em.merge(todo);
        tx.begin();
        tx.commit();
        return todo;
    }

    @Override
    public void supprimerTodo(Long idTodo) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        em.remove(em.find(Todo.class, idTodo));
        tx.commit();
    }
    @Override
    public Todo montreTodo(Long idTodo) {
        EntityManager em = emf.createEntityManager();
        return em.find(Todo.class, idTodo);
    }



























}
