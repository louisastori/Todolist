package com.example.tpnote1.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Todo {

    @Id
    private Long idTodo;

    private String nomTodo;
    private String contenuTodo;
    private String statut;

    public Todo(String statut, String contenuTodo,Long idTodo, String nomTodo) {
        this.statut = statut;
        this.contenuTodo = contenuTodo;
        this.idTodo = idTodo;
        this.nomTodo = nomTodo;
    }

    public Todo() {

    }


    public Long getIdTodo() {
        return idTodo;
    }
    public void setIdTodo(Long idTodo) {
        this.idTodo = idTodo;
    }
    public String getStatut() {
        return statut;
    }
    public void setStatut(String statut) {
        this.statut = statut;
    }
    public String getContenuTodo() {
        return contenuTodo;
    }
    public void setContenuTodo(String contenuTodo) {
        this.contenuTodo = contenuTodo;
    }
    public String getNomTodo() {return nomTodo;}
    public void setNomTodo(String nomTodo) {this.nomTodo = nomTodo;}
}
