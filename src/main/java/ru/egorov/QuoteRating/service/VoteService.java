package ru.egorov.QuoteRating.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import ru.egorov.QuoteRating.model.Quote;
import ru.egorov.QuoteRating.model.User;
import ru.egorov.QuoteRating.model.Vote;
import ru.egorov.QuoteRating.repository.VoteRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;

    @Transactional
    public Vote create(Vote vote) {
        vote.setScore(countScore(vote));
        vote.setDateOfVote(LocalDateTime.now());

        return voteRepository.save(vote);
    }

    private int countScore(Vote vote) {
        int upvoteCount = (int) voteRepository.count(getExample(vote, true));
        int downvoteCount = (int) voteRepository.count(getExample(vote, false));

        int thisVote = vote.getIsPositive() ? 1 : -1;

        return upvoteCount - downvoteCount + thisVote;
    }

    private Example<Vote> getExample(Vote vote, boolean isPositive) {
        Vote probe = new Vote();
        Quote quote = vote.getEmbeddedId()
                .getQuote();

        Vote.VoteId voteId = new Vote.VoteId();
        voteId.setQuote(quote);

        probe.setEmbeddedId(voteId);
        probe.setIsPositive(isPositive);

        return Example.of(probe);
    }

    public boolean existsVote(Vote vote) {
        Quote quote = vote.getEmbeddedId()
                .getQuote();
        User user = vote.getEmbeddedId()
                .getUser();

        return voteRepository.existsVoteByEmbeddedId(new Vote.VoteId(quote, user));
    }
}
