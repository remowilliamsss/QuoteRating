package ru.egorov.QuoteRating.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.egorov.QuoteRating.dto.QuoteDto;
import ru.egorov.QuoteRating.exception.QuoteNotAddedException;
import ru.egorov.QuoteRating.mapper.QuoteMapper;
import ru.egorov.QuoteRating.model.Quote;
import ru.egorov.QuoteRating.model.User;
import ru.egorov.QuoteRating.service.QuoteService;
import ru.egorov.QuoteRating.validator.QuoteValidator;

import java.util.List;

import static ru.egorov.QuoteRating.util.MessageBuilder.getErrorMessage;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quotes")
public class QuoteController {
    private final QuoteService quoteService;
    private final QuoteValidator quoteValidator;
    private final QuoteMapper quoteMapper;

    @PostMapping()
    public ResponseEntity<HttpStatus> add(@RequestBody Quote quote, BindingResult bindingResult) {
        quoteValidator.validate(quote, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new QuoteNotAddedException(getErrorMessage(bindingResult));
        }

        int id = quoteService.create(quote).getId();

        return ResponseEntity.ok()
                .header("Location", String.format("/api/quotes/%d", id))
                .body(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuoteDto> view(@PathVariable("id") int id) {
        QuoteDto quoteDto = quoteMapper.toDto(quoteService.find(id));

        return new ResponseEntity<>(quoteDto, HttpStatus.OK);
    }

    @GetMapping("/random")
    public ResponseEntity<QuoteDto> random() {
        QuoteDto quoteDto = quoteMapper.toDto(quoteService.findRandom());

        return new ResponseEntity<>(quoteDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> modify(@PathVariable("id") int id, @RequestBody @Valid Quote quote,
                                                 BindingResult bindingResult) {
        quoteValidator.validate(quote, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new QuoteNotAddedException(getErrorMessage(bindingResult));
        }

        quoteService.update(id, quote);

        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        quoteService.delete(id);

        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}/upvote")
    public ResponseEntity<HttpStatus> upvote(@PathVariable("id") int id, @RequestBody User user,
                                             BindingResult bindingResult) {
        quoteService.upvote(id, user, bindingResult);

        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}/downvote")
    public ResponseEntity<HttpStatus> downvote(@PathVariable("id") int id, @RequestBody User user,
                                               BindingResult bindingResult) {
        quoteService.downvote(id, user, bindingResult);

        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/top10")
    public ResponseEntity<List<QuoteDto>> top() {
        List<QuoteDto> quoteDtos = quoteService.getTop(10)
                .stream()
                .map(quoteMapper::toDto)
                .toList();

        return new ResponseEntity<>(quoteDtos, HttpStatus.OK);
    }

    @GetMapping("/flop10")
    public ResponseEntity<List<QuoteDto>> flop() {
        List<QuoteDto> quoteDtos = quoteService.getWorse(10)
                .stream()
                .map(quoteMapper::toDto)
                .toList();

        return new ResponseEntity<>(quoteDtos, HttpStatus.OK);
    }
}
