package com.in28minuts.rest.webservices.todo.service;

import com.in28minuts.rest.webservices.todo.exceptionhandling.ResourceNotFoundException;
import com.in28minuts.rest.webservices.todo.model.Todo;
import com.in28minuts.rest.webservices.todo.model.UserDetails;
import com.in28minuts.rest.webservices.todo.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

@Service
@AllArgsConstructor
public class TodoService {

    private final UserRepository userRepository;

    public UserDetails getUserByName(String userName) {
        UserDetails user = userRepository.findByName(userName);
        if (user == null) {
            throw new ResourceNotFoundException("user with name " + userName + " not found");
        }
        return user;
    }

    public List<Todo> retrieveTodosForUser(String userName) throws ResourceNotFoundException {
        UserDetails user = getUserByName(userName);
        return user.getTodoList();
    }

    public Todo getTodoById(String userName, Long id) {
        List<Todo> todos = retrieveTodosForUser(userName);
        Optional<Todo> _todo = todos.stream()
                .filter(todo -> todo.getId() == id)
                .findFirst();
        if (_todo.isPresent()) {
            return _todo.get();
        } else {
            throw new ResourceNotFoundException("Todo with id " + id + " not found for user " + userName);
        }
    }

    public Todo createTodoForUser(String userName, Todo todo) {
        UserDetails user = getUserByName(userName);
        todo.setId(0L);
        user.addTodo(todo);
        UserDetails updatedUser = userRepository.save(user);
        List<Todo> todos = updatedUser.getTodoList();
        long newId = todos.stream().mapToLong(t -> t.getId()).max().getAsLong();
        return todos.stream().filter(t -> t.getId() == newId).findFirst().get();
    }

    public void deleteTodoForUser(String userName, Long id) {
        UserDetails user = getUserByName(userName);
        List<Todo> todos = user.getTodoList();
        Todo todo = getTodoById(userName, id);
        todos.remove(todo);
        user.setTodoList(todos);
        userRepository.save(user);
    }

    public Todo updateTodoForUser(String userName, Long id, Todo todo) {
        UserDetails user = getUserByName(userName);
        List<Todo> todos = user.getTodoList();
        Todo existingTodo = getTodoById(userName, id);
        todo.setId(id);
        todos.set(todos.indexOf(existingTodo), todo);
        user.setTodoList(todos);
        userRepository.save(user);
        return todo;
    }
}
