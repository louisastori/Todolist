package fr.eiffel.sio.todolist.repository;

import fr.eiffel.sio.todolist.model.Todolist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodolistRepository extends JpaRepository<Todolist, Long> {
}
