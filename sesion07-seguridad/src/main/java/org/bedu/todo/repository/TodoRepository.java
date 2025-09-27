package org.bedu.todo.repository;

import java.util.List;

import org.bedu.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
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
    
    // Objetivo: Filtrar las tareas por "description"

    /**
     * 1. Usando "findBy"
     * 
     * "findBy" regresa todos (0 o más) resultados por ciertos campos,
     * cada campo se especifica separado por un "And"/"Or"
     * 
     * Buscar personas por nombre y edad = findByNameAndAge
     * Buscar perros por nombre o raza = findByNameOrBreed
     * 
     * Este tipo de búsquedas son "EXACTAS"
     */
    List<Todo> findByDescription(String q);

    /**
     * 2. Usando findBy pero con modificadores
     * 
     * Containing = Que contenga el texto especificado
     */
    List<Todo> findByDescriptionContaining(String q);

    /**
     * 3. Usando findBy pero con modificadores + IgnoreCase
     * 
     * Containing = Que contenga el texto especificado sin importar mayúsculas y minúsculas
     */
    List<Todo> findByDescriptionContainingIgnoreCase(String q);

    /**
     * 4. Usando lenguaje de SQL
     * 
     * Los parámetros se especifican ?n , donde n es la posición del parámetro
     * 
     * Si tengo 3 parámetros, serían ?1 , ?2 y ?3
     * 
     * Nota: Aquí no importa el nombre del método
     */
    @NativeQuery("SELECT * FROM todos WHERE description ILIKE %?1%")
    List<Todo> findByDescriptionWithSQL(String q);
}
