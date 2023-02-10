package ru.egorov.QuoteRating.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private LocalDateTime date;
}
