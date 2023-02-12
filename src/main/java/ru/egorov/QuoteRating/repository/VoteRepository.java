package ru.egorov.QuoteRating.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.egorov.QuoteRating.model.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Vote.VoteId> {

    boolean existsVoteByEmbeddedId(Vote.VoteId id);
}
