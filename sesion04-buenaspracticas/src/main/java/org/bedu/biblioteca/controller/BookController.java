package org.bedu.biblioteca.controller;

import java.util.LinkedList;
import java.util.List;

import org.bedu.biblioteca.model.Book;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

public class BookController {

    private List<Book> db = new LinkedList<>();

    // CRUD: Create, Read, Update, Delete
    // Exponer endpoints para manipular datos

    /**
     * Un API (Application Programming Interface) debería
     * de seguir las buenas prácticas de REST.
     * 
     * Representational State Transfer
     * 
     * 1. REST no es un estandard, es un ESTILO de arquitectura
     * y una RECOMENDACIÓN de buenas prácticas.
     * 
     * 2. REGLA DE ORO: REST se trata de que NO HAY ESTADO. Cada petición 
     * al backend está autocontenida (cada petición contiene
     * toda la información necesaria procesarse).
     * 
     * 3. Utilizar adecuadamente los verbos/métodos de HTTP.
     *  - GET: Leer información
     *  - POST: Crear nueva información
     *  - PUT: Actualizar información (parcial)
     *  - DELETE: Eliminar información
     * 
     * Hay otros pero casi no se usan en el desarrollo de APIs.
     * 
     * En Spring, por defecto @RequestMapping es "GET"
     * para utilizar los verbos adecuadamente se usan las anotaciones:
     * 
     *  - @GetMapping
     *  - @PostMapping
     *  - @PutMapping
     *  - @DeleteMapping
     * 
     *   
     * 
     */
    
    // Obtener todos los libros
    @GetMapping("getBooks")
    public List<Book> getBooks() {
        return db;
    }

    // Obtener un libro por ISBN

    // Crear un nuevo libro
    @PostMapping("createBook")
    public void createBook() {

    }

    // Editar un libro
    @PutMapping("updateBook")
    public void updateBook() {

    }

    // Eliminar un libro
    @DeleteMapping("deleteBook")
    public void deleteBook() {
        
    }
}
