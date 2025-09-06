package org.bedu.biblioteca.controller;

import java.util.LinkedList;
import java.util.List;

import org.bedu.biblioteca.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("books") // Todos los endpoints tendrán el prefijo /books
@RestController
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
     * 
     * 8. Deben usarse adecuadamente los códigos de respuesta de HTTP.
     * 
     *  Códigos de éxito
     *  - 200 (Ok): Todo bien
     *  - 201 (Created): Todo bien y se creó información
     *  - 204 (No Content):Todo bien pero no hay respuesta adicional
     * 
     *  Códigos de error
     *  - 400 (Bad Request): La información proporcionada es incorrecta/invalida
     *  o se provoca un error por culpa del usuario.
     *  - 401 (Acceso denagado): No se tiene acceso al recurso solicitado
     *  - 404 (Not Found): No se encontró la información buscada
     *  - 500 (Internal Server Error): Ocurrió un error inesperado
     * 
     *  Por defecto en Spring, todas las respuestas son 200/Ok (excepto en los errores)
     */
    
    // Obtener todos los libros
    @GetMapping
    public List<Book> getBooks() {
        return db;
    }

    // Obtener un libro por ISBN
    @GetMapping("{isbn}") // books/{isbn}
    public Book getBookByISBN(@PathVariable("isbn") String isbn) {
        // Imperativo
        for (Book book : db) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }

        // Funcional
        // return db.stream().filter(x -> x.getIsbn().equals(isbn)).findFirst();

        return null;
    }

    // Crear un nuevo libro
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book newBook) {
        db.add(newBook);
        return newBook;
    }

    // Editar un libro
    @PutMapping("{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBook(@PathVariable("isbn") String isbn,
        @RequestBody Book updatedBook) throws Exception {

        Book oldBook = null;

        for (Book book : db) {
            if (book.getIsbn().equals(isbn)) {
                oldBook = book;
                break;
            }
        }

        // Si no encuentra el libro entonces regresa un error
        if (oldBook == null) {
            throw new Exception();
        }

        if (updatedBook.getIsbn() != null) {
            oldBook.setIsbn(updatedBook.getIsbn());
        }

        if (updatedBook.getTitle() != null) {
            oldBook.setTitle(updatedBook.getTitle());
        }

        if (updatedBook.getAuthor() != null) {
            oldBook.setAuthor(updatedBook.getAuthor());
        }

        if (updatedBook.getYear() != 0) {
            oldBook.setYear(updatedBook.getYear());
        }
    }

    // Eliminar un libro
    @DeleteMapping("{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable("isbn") String isbn) {
        // Imperativa
        for (int i = 0; i < db.size(); i++) {
            Book book = db.get(i);

            if (book.getIsbn().equals(isbn)) {
                db.remove(i);
                return;
            }
        }

        // Funcional
        // db.removeIf(x -> x.getIsbn().equals(isbn));
    }
}
