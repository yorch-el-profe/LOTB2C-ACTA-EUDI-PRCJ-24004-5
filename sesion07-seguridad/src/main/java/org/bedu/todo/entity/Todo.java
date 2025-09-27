package org.bedu.todo.entity;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

// Entidad = Tabla de base de datos
@Entity
// Renombrando la tabla
@Table(name = "todos")
public class Todo {
    
    @Id
    // Va a generar su propio ID en cada inserción
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    // @Column agrega una columna a la tabla
    // nullable hace obligatoria la columna
    // columnDefinition permite cambiar el tipo (es especifico para cada base)
    @Column(nullable = false)
    private String description;

    @Column
    // @ColumnDefault agrega un valor por defecto a la columna
    @ColumnDefault("false")
    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Boolean isCompleted() {
        return completed;
    }
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
