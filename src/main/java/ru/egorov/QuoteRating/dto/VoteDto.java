package ru.egorov.QuoteRating.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VoteDto {
    private UserDto user;
    private Boolean isPositive;
    private LocalDateTime dateOfVote;
}
