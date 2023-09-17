package com.in28minuts.rest.webservices.todo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
@Entity
@Data
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Invalid title - Title is mandatory")
    private String Title;

    @NotBlank(message = "Invalid description - Description is mandatory")
    private String description;

    @FutureOrPresent(message = "Invalid target date - Date can't be in the past")
    private LocalDate targetDate;
    private Boolean completed;
}
