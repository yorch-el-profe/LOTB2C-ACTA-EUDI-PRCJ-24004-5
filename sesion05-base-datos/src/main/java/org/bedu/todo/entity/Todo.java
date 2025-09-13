package org.bedu.todo.entity;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Column(nullable = false)
    // @ColumnDefault agrega un valor por defecto a la columna
    @ColumnDefault("false")
    private Boolean isCompleted;

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
        return isCompleted;
    }
    public void setCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}
