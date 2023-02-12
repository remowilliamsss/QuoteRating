package ru.egorov.QuoteRating.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.egorov.QuoteRating.model.User;
import ru.egorov.QuoteRating.service.UserService;

@Component
@RequiredArgsConstructor
public class UserValidator implements Validator {
    private final UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        User foundedUser = userService.findByNameOrEmail(user.getName(), user.getEmail());

        if (foundedUser == null) {
            return;
        }

        if (user.getName()
                .equals(foundedUser.getName())) {
            errors.rejectValue("name", "", "must be unique");
        }

        if (user.getEmail()
                .equals(foundedUser.getEmail())) {
            errors.rejectValue("email", "", "must be unique");
        }
    }
}
