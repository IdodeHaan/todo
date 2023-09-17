package com.in28minuts.rest.webservices.todo.controller;

import com.in28minuts.rest.webservices.todo.model.AuthenticationBean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BasicAuthenticationController {

    @GetMapping("/basicauth")
    public AuthenticationBean authenticate() {

        return new AuthenticationBean("You are authenticated");
    }
}
