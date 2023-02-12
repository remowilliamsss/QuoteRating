package ru.egorov.QuoteRating.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Size(max = 512)
    @Column(unique = true)
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateOfCreation;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateOfUpdate;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private Integer score;

    @OneToMany(mappedBy = "embeddedId.quote", orphanRemoval = true)
    private List<Vote> votes;

    @Transient
    private Map voteStatistics;
}
