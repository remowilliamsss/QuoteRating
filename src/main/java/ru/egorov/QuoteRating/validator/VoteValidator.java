package ru.egorov.QuoteRating.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.egorov.QuoteRating.model.Vote;
import ru.egorov.QuoteRating.service.VoteService;

@Component
@RequiredArgsConstructor
public class VoteValidator implements Validator {
    private final VoteService voteService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Vote.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Vote vote = (Vote) target;

        if (vote.getEmbeddedId().getUser() == null) {
            errors.rejectValue("name", "", "user with this name is not exist");
        }

        if (voteService.existsVote(vote)) {
            errors.rejectValue("name", "", "this user has already voted");
        }
    }
}
