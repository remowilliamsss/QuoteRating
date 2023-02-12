package ru.egorov.QuoteRating.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.egorov.QuoteRating.exception.VoteNotCountedException;
import ru.egorov.QuoteRating.model.Quote;
import ru.egorov.QuoteRating.model.User;
import ru.egorov.QuoteRating.model.Vote;
import ru.egorov.QuoteRating.repository.QuoteRepository;
import ru.egorov.QuoteRating.validator.VoteValidator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import static ru.egorov.QuoteRating.util.MessageBuilder.getErrorMessage;

@Service
@RequiredArgsConstructor
public class QuoteService {
    private final QuoteRepository quoteRepository;
    private final UserService userService;
    private final VoteService voteService;
    private final VoteValidator voteValidator;

    @Transactional
    public Quote create(Quote quote) {
        User user = userService.findByName(quote.getUser()
                .getName());
        quote.setUser(user);

        quote.setDateOfCreation(LocalDateTime.now());

        return quoteRepository.save(quote);
    }

    public Quote find(int id) {
        Quote quote = findById(id);

        quote.setVoteStatistics(getVoteStatistics(id));

        return quote;
    }

    private Quote findById(int id) {
        return quoteRepository.findById(id)
                .orElseThrow();
    }

    public Quote findRandom() {
        long quoteCount = quoteRepository.count();
        int randomNumber = (int) (Math.random() * quoteCount);

        Quote quote = quoteRepository.findAll(PageRequest.of(randomNumber, 1))
                .getContent()
                .stream()
                .findFirst()
                .orElseThrow();

        quote.setVoteStatistics(getVoteStatistics(quote.getId()));

        return quote;
    }

    public Quote findByContent(String content) {
        return quoteRepository.findByContent(content);
    }

    @Transactional
    public Quote update(int id, Quote updatedQuote) {
        User user = userService.findByName(updatedQuote.getUser()
                .getName());
        updatedQuote.setUser(user);

        Quote quote = findById(id);

        updatedQuote.setId(id);
        updatedQuote.setDateOfCreation(quote.getDateOfCreation());
        updatedQuote.setScore(quote.getScore());
        updatedQuote.setVotes(quote.getVotes());
        updatedQuote.setDateOfUpdate(LocalDateTime.now());

        return quoteRepository.save(updatedQuote);
    }

    @Transactional
    public void delete(int id) {
        quoteRepository.deleteById(id);
    }

    public void upvote(int id, User user, BindingResult bindingResult) {
        vote(id, user, true, bindingResult);
    }

    public void downvote(int id, User user, BindingResult bindingResult) {
        vote(id, user, false, bindingResult);
    }

    @Transactional
    private void vote(int id, User user, boolean isPositive, BindingResult bindingResult) {
        Quote quote = findById(id);
        user = userService.findByName(user.getName());
        Vote vote = new Vote(quote, user, isPositive);

        voteValidator.validate(vote, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new VoteNotCountedException(getErrorMessage(bindingResult));
        }

        vote = voteService.create(vote);

        quote.setScore(vote.getScore());
        quote.getVotes()
                .add(vote);

        quoteRepository.save(quote);
    }

    public List<Quote> getTop(int size) {
        return getSomeMost(size, Sort.Direction.DESC);
    }

    public List<Quote> getWorse(int size) {
        return getSomeMost(size, Sort.Direction.ASC);
    }

    private List<Quote> getSomeMost(int size, Sort.Direction direction) {
        List<Quote> quotes =  quoteRepository.findAll(PageRequest.of(0, size, Sort.by(direction, "score")))
                .getContent()
                .stream()
                .filter(quote -> Objects.nonNull(quote.getScore()))
                .toList();

        quotes.forEach(quote ->
                quote.setVoteStatistics(getVoteStatistics(quote.getId())));

        return quotes;
    }

    public Map<LocalDateTime, Integer> getVoteStatistics(int id) {
        Map<LocalDateTime, Integer> voteStatistics = new TreeMap<>((o1, o2) ->
                o1.isEqual(o2) ? 0 : o1.isBefore(o2) ? -1 : 1);

        findById(id).getVotes()
                .forEach(vote -> voteStatistics.put(vote.getDateOfVote(), vote.getScore()));

        return voteStatistics;
    }
}
