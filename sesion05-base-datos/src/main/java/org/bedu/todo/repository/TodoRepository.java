package org.bedu.todo.repository;

import org.bedu.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JpaRepository es una interfaz que contiene las consultas esenciales:
 *  - findAll() = Obtener toda la tabla
 *  - findById(id) = Obtener un objeto por id
 *  - save(entidad) = Guardar/actualizar un objeto
 *  - delete(entidad) = Elimina un objeto en particular
 *  - deleteById(id) = Elimina un objeto por id
 */

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
    
}
