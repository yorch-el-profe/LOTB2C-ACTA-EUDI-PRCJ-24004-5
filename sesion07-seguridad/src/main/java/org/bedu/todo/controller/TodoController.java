package org.bedu.todo.controller;

import java.util.List;

import org.bedu.todo.entity.Todo;
import org.bedu.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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

@RestController
@RequestMapping("todos")
public class TodoController {

    @Autowired
    private TodoService service;
    
    @GetMapping
    public List<Todo> findAll(
        @RequestParam(name = "q", required = false) String q, @AuthenticationPrincipal UserDetails user) {
            if (q != null) {
                // Buscar todos las tareas que tengan por nombre "q"
                return service.filterByDescription(q);
            } else {
                return service.findAll(user.getUsername());
            }
        
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Todo save(@RequestBody Todo todo, @AuthenticationPrincipal UserDetails user) {
        return service.save(user.getUsername(), todo);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") int id) throws Exception {
        service.update(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") int id) {
        service.deleteById(id);
    }
}
