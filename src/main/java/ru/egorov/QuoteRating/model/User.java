package ru.egorov.QuoteRating.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Size(max = 128)
    @Column(unique = true)
    private String name;

    @Email
    @NotEmpty
    @Size(max = 128)
    @Column(unique = true)
    private String email;

    @NotEmpty
    @Size(max = 256)
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateOfCreation;
}
