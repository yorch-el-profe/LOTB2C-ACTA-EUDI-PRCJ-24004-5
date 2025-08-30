package org.bedu.movies.model;

// POJO (Plain Old Java Object)
// En un objeto que no tiene ninguna dependecia externa, más que
// sus métodos getters/setters.
// Nota: Muy importante seguir los estándares de Java.

// Los POJOs se convierten en JSON y viceversa.
// Request: JSON -> POJO
// Response: POJO -> JSON

public class Movie {
    
    private String title;
    private String director;
    private int year;
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
}
