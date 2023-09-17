package com.in28minuts.rest.webservices.todo.helleworld;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class Controller {

    @GetMapping("/hi")
    public String sayHi() {
        return "I'm saying hi";
    }
    @GetMapping("/hi/{name}")
    public MyBean sayHiWithVariable(@PathVariable String name) {
        return new MyBean("I'm saying hi to " + name);
    }
    @GetMapping("/hi/bean")
    public MyBean sayHiWithBean() {
//        throw new RuntimeException("An error has occurred. Contact support.");
        return new MyBean("hello from bean");
    }
}
