package ru.egorov.QuoteRating.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserDto {
    private String name;
    private LocalDateTime dateOfCreation;
}
