package ru.egorov.QuoteRating.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.egorov.QuoteRating.model.Quote;
import ru.egorov.QuoteRating.service.QuoteService;
import ru.egorov.QuoteRating.service.UserService;

@Component
@RequiredArgsConstructor
public class QuoteValidator implements Validator {
    private final QuoteService quoteService;
    private final UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Quote.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Quote quote = (Quote) target;

        boolean isExistentVoter = userService.existsByName(quote.getUser()
                .getName());

        if (!isExistentVoter) {
            errors.rejectValue("user", "", "user with this name is not exist");
        }

        if (quoteService.findByContent(quote.getContent()) != null) {
            errors.rejectValue("content", "", "must be unique");
        }
    }
}
