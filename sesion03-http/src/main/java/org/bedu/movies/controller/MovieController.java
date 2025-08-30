package org.bedu.movies.controller;

import java.util.LinkedList;
import java.util.List;

import org.bedu.movies.model.Movie;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // En un controlador que regresa datos
// @Controller: En un controlador que puede regresar HTML o datos
public class MovieController {

    private List<Movie> db = new LinkedList<>();

    public MovieController() {
        Movie superman = new Movie();

        superman.setId(1);
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

    // Obtener una sola pelicula por su ID
    // URL: localhost:8080/getMovieById/30
    @RequestMapping("getMovieById/{id}")
    public Movie getMovieById(@PathVariable("id") int id) {

        for (int i = 0; i < db.size(); i++) {
            Movie movie = db.get(i); // Obtiene la película en la posición i

            if (movie.getId() == id) { // Es la película que estamos buscando
                return movie; // Enviando la película encontrada
            }   
        }

        return null;// Es cuando NO encontró una película
    }

    // Crear una nueva pelicula
    // Parte de las buenas prácticas es regresar el elemento creado
    @RequestMapping("createMovie")
    public Movie createMovie(@RequestBody Movie newMovie) {
        newMovie.setId(db.size() + 1); // Asignando un nuevo ID
        db.add(newMovie); // Agregamos la nueva película a la "base de datos"
        return newMovie;
    }

    /*
     * Un backend puede recibir parámetros de 4 maneras diferentes.
     * 
     * 1. Querystring (@RequestParam) --------------------------------------------
     * 
     * Son parámetros proporcionados al final de la URL. Los parámetros son opcionales
     * pero a través del código se pueden volver obligatorios.
     * 
     * url_backend/endpoint ? key=value & key2=value2 & key3=value3 ...
     * 
     * Por ejemplo:
     * 
     * url_backend/endpoint ? nombre=Jorge & puesto=Desarrollador & edad=32
     * 
     * url_backend/endpoint ? id=10
     * 
     * Desventaja: 
     * 1. La URL tiene un tamaño máximo (2000 caracteres)
     * 2. Los parámetros son opcionales (en general)
     * 
     * 2. Variable en la URL/Path (@PathVariable) --------------------------------------------
     * 
     * Los paths pueden tener variables y son obligatorios.
     * 
     * url_backend/endpoint/{param}/other  <- es una varible
     * 
     * Desventaja:
     * 1. La URL tiene un tamaño máximo
     * 
     * 3. Cuerpo de la petición (@RequestBody) --------------------------------------------
     * 
     * Son los datos que no se pueden enviar por URL. Se pueden enviar en cualquier formato
     * (depende del backend cómo interpretarlos) y no hay un límite en el tamaño.
     * 
     * Nota: Para poder obtenerlo en el código, es necesario tener una clase que lo represente.
     */
}
