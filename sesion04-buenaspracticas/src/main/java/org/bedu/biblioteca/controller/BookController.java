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
     * 1. REST no es un estandar, es un ESTILO de arquitectura
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
     * 4. La transferencia de información es utilizando JSON o XML (ya nadie usa XML).
     * 
     * 5. El querystring se utiliza para filtrar información, por ejemplo
     * 
     *  GET /perros?nombre=Firulais: Filtra todos perros que se llaman Firulais
     *  GET /productos?categoria=eletrodomesticos&precio=5000
     * 
     * Para crear/actualizar información se utiliza el BODY, y los ID's se mandan
     * através de la URL.
     * 
     * GET /doctores/89657: Obtiene la información del doctor con ID 89657
     * 
     * 6. Para el nombrado de los endpoints se utilizan sustantivos (en plural) y no verbos.
     * 
     * getBooks -> books
     * createCar -> cars
     * updateDog -> dogs
     * 
     * getProduct ->    GET /products
     * createProduct -> POST /products
     * updateProduct -> PUT /products
     * deleteProduct -> DELETE /products
     * 
     * 7. Reflejar la jerarquía de la información a través de la URL.
     * 
     *  GET /cursos/89034/modulos/233434/alumnos: Obtiene todos los alumnos del módulo 233434
     *  para el curso 89034
     * 
     *  GET /books/908248923/authors: Obtiene todos los autores del libro 908248923
     * 
     *  GET /authors?book=908248923
     *  GET /alumnos?modulo=78423&curso=234786
     */
    
    // Obtener todos los libros
    @GetMapping("books")
    public List<Book> getBooks() {
        return db;
    }

    // Obtener un libro por ISBN

    // Crear un nuevo libro
    @PostMapping("books")
    public void createBook() {

    }

    // Editar un libro
    @PutMapping("books")
    public void updateBook() {

    }

    // Eliminar un libro
    @DeleteMapping("books")
    public void deleteBook() {
        
    }
}
