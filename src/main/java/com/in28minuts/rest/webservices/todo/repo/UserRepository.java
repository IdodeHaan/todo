package com.in28minuts.rest.webservices.todo.repo;

import com.in28minuts.rest.webservices.todo.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Long> {
    UserDetails findByName(String name);
}
