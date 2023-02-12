package ru.egorov.QuoteRating.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Vote {

    @EmbeddedId
    private VoteId embeddedId;

    @NotNull
    private Boolean isPositive;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateOfVote;

    private Integer score;

    public Vote(Quote quote, User user, Boolean isPositive) {
        this.embeddedId = new VoteId(quote, user);
        this.isPositive = isPositive;
    }

    @Getter
    @Setter
    @Embeddable
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VoteId implements Serializable {

        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "quote_id", referencedColumnName = "id")
        private Quote quote;

        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "user_id", referencedColumnName = "id")
        private User user;
    }
}
