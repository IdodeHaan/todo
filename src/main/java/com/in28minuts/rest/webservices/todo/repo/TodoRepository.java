package com.in28minuts.rest.webservices.todo.repo;

import com.in28minuts.rest.webservices.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
}

