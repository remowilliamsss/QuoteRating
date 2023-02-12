package ru.egorov.QuoteRating.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.egorov.QuoteRating.exception.UserNotCreatedException;
import ru.egorov.QuoteRating.model.User;
import ru.egorov.QuoteRating.service.UserService;
import ru.egorov.QuoteRating.validator.UserValidator;

import static ru.egorov.QuoteRating.util.MessageBuilder.getErrorMessage;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final UserValidator userValidator;

    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new UserNotCreatedException(getErrorMessage(bindingResult));
        }

        userService.create(user);

        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
