package com.example.tpnote1.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TodoList {

    @Id
    private Long idTodoList;

    private String nomTodoList;


    public TodoList(Long idTodoList, String nomTodoList) {
        this.idTodoList = idTodoList;
        this.nomTodoList = nomTodoList;
    }

    public TodoList() {}

    public Long getIdTodoList() {return idTodoList;}

    public void setIdTodoList(Long idTodoList) {this.idTodoList = idTodoList;}

    public String getNomTodoList() {return nomTodoList;}

    public void setNomTodoList(String nomTodoList) {this.nomTodoList = nomTodoList;}


}
