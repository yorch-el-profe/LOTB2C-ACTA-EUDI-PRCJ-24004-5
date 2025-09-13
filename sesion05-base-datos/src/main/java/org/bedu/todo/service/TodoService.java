package org.bedu.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.bedu.todo.entity.Todo;
import org.bedu.todo.repository.TodoRepository;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    public List<Todo> findAll() {
        // findAll() = SELECT * FROM todos;
        return repository.findAll();
    }

    public Todo save(Todo newTodo) {
        // save() = INSERT INTO ...
        return repository.save(newTodo);
    }

    public void update(int id) throws Exception {
        // findById(id) = SELECT * FROM todos WHERE id = :id
        Optional<Todo> current = repository.findById(id);

        // La idea de Optional es evitar un NullPointerException
    
        // No encontró el TODO
        if (current.isEmpty()) {
            throw new Exception("No se encontro el TODO con ID " + id);
        }

        // En otro caso, si existe el TODO y lo actualizo
        Todo t = current.get();

        t.setCompleted(!t.isCompleted());

        // UPDATE ...
        repository.save(t);
    }

    public void deleteById(int id) {
        // DELETE FROM todos WHERE id = :id
        repository.deleteById(id);
    }

    public List<Todo> filterByDescription(String q) {
        return repository.findByDescriptionWithSQL(q);
    }
}
