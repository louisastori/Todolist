package com.example.tpnote1.dao;

import com.example.tpnote1.dto.TodoList;
import com.example.tpnote1.repository.TodoListRepository;
import com.example.tpnote1.services.TodoListService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.springframework.beans.factory.annotation.Autowired;

public class TodoListDAO implements TodoListService {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("connect");

    @Autowired
    private TodoListRepository TodoListRepository;

    @Override
    public TodoList creerTodoList(TodoList todoList) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        em.persist(todoList);
        tx.begin();
        tx.commit();
        return todoList;
    }

    @Override
    public TodoList modifierTodoList(TodoList todoList) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();


        em.merge(todoList);
        tx.begin();
        tx.commit();
        return todoList;
    }

    @Override
    public void supprimerTodoList(Long idTodoList) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        em.remove(em.find(TodoList.class, idTodoList));
        tx.commit();
    }
    @Override
    public TodoList montreTodoList(Long idTodoList) {
        EntityManager em = emf.createEntityManager();
        return em.find(TodoList.class, idTodoList);
    }


}
