package com.in28minuts.rest.webservices.todo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<Todo> todoList;

    public void addTodo (Todo todo) {
        if (todoList == null) {
            todoList = new ArrayList<>();
        }
        todoList.add(todo);
    }
}
