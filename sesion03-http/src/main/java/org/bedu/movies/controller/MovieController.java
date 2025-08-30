package org.bedu.movies.controller;

import java.util.LinkedList;
import java.util.List;

import org.bedu.movies.model.Movie;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // En un controlador que regresa datos
// @Controller: En un controlador que puede regresar HTML o datos
public class MovieController {

    private List<Movie> db = new LinkedList<>();

    public MovieController() {
        Movie superman = new Movie();

        superman.setTitle("Superman");
        superman.setDirector("James Gunn");
        superman.setYear(2025);

        db.add(superman);
    }
    
    // Endpoint: Acciones/operaciones que realiza el backend
    // Endpoint = Método + anotaciones
    // Los endpoint se acceden a través de una URL
    // url-backend/endpoint

    // Obtener todas las peliculas
    // URL: localhost:8080/getMovies
    @RequestMapping("getMovies")
    public List<Movie> getMovies() {
        return db;
    }

    // Obtener una sola pelicula

    // Crear una nueva pelicula
}
