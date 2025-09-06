package org.bedu.biblioteca.service;

import java.util.List;

import org.bedu.biblioteca.model.Book;
import org.bedu.biblioteca.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * La capa de servicio aplica validaciones o reglas de negocio.
 * También es un puente entre Controller y Repository
 * 
 * Request -> Controller -> Service -> Repository
 */

@Service
public class BookService {

    @Autowired
    private BookRepository repository;
    
    public List<Book> getAll() {
        return repository.getAll();
    }

    public Book getByISBN(String isbn) {
        return repository.getByISBN(isbn);
    }

    public Book add(Book newBook) throws Exception {
        // Lógica
        if (newBook.getIsbn() == null  ||
            newBook.getTitle() == null || newBook.getYear() < 1500) {
                throw new Exception();
            }

        return repository.add(newBook);
    }

    public void update(String isbn, Book updatedBook) {
        repository.update(isbn, updatedBook);
    }

    public void delete(String isbn) {
        repository.delete(isbn);
    }
}
