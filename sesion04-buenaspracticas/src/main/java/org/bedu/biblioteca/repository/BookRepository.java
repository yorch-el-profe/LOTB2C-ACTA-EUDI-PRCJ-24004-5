package org.bedu.biblioteca.repository;

import java.util.LinkedList;
import java.util.List;

import org.bedu.biblioteca.model.Book;
import org.springframework.stereotype.Repository;

/*
 * La capa de Repository se encarga únicamente del acceso/manipulación
 * a base de datos proporcionado métodos.
 */
@Repository
public class BookRepository {

    private List<Book> db = new LinkedList<>();

    // Obtener toda la info de la BD
    public List<Book> getAll() {
        return db;
    }

    // Obtener un libro por ISBN
    public Book getByISBN(String isbn) {
        for (Book book : db) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }

        return null;
    }

    // Agregar un nuevo libro a la BD
    public Book add(Book newBook) {
        db.add(newBook);
        return newBook;
    }

    // Actualizar un libro 
    public void update(String isbn, Book updatedBook) {
        Book oldBook = getByISBN(isbn);

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
    public void delete(String isbn) {
        Book book = getByISBN(isbn);
        db.remove(book);
    }
}
