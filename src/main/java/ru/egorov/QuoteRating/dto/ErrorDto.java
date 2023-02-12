package ru.egorov.QuoteRating.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDto {
    private String message;
    private LocalDateTime date;
}
