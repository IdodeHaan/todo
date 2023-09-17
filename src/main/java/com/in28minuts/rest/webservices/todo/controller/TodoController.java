package com.in28minuts.rest.webservices.todo.controller;

import com.in28minuts.rest.webservices.todo.exceptionhandling.InputValidationException;
import com.in28minuts.rest.webservices.todo.exceptionhandling.ResourceNotFoundException;
import com.in28minuts.rest.webservices.todo.model.Todo;
import com.in28minuts.rest.webservices.todo.service.TodoService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
public class TodoController {

    private final TodoService service;

    @GetMapping("/users/{userName}/todos")
    public ResponseEntity<List<Todo>> retrieveTodosForUser(@PathVariable String userName) {
        try {
            return ResponseEntity.ok(service.retrieveTodosForUser(userName));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException();
        }
    }

    @GetMapping("/users/{userName}/todos/{id}")
    public ResponseEntity<Todo> getTodo(@PathVariable String userName, @PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getTodoById(userName, id));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @PostMapping("/users/{userName}/todos")
    public ResponseEntity createTodo(@PathVariable String userName, @Valid @RequestBody Todo todo) {
        Todo createdTodo = service.createTodoForUser(userName, todo);
        URI location = URI.create(String.format("users/%s/todos/%s", userName, createdTodo.getId()));
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{userName}/todos/{id}")
    public ResponseEntity deleteTodo(@PathVariable String userName, @PathVariable Long id) {
        service.deleteTodoForUser(userName, id);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/users/{userName}/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable String userName, @PathVariable Long id, @Valid @RequestBody Todo todo) {
        return ResponseEntity.ok(service.updateTodoForUser(userName, id, todo));
    }
}
