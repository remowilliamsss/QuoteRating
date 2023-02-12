package ru.egorov.QuoteRating.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class QuoteDto {
    private String content;
    private LocalDateTime dateOfCreation;
    private LocalDateTime dateOfUpdate;
    private UserDto user;
    private Integer score;
    private List<VoteDto> votes;
    private Map voteStatistics;
}
