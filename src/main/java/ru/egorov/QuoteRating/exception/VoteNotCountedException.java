package ru.egorov.QuoteRating.exception;

public class VoteNotCountedException extends RuntimeException {

    public VoteNotCountedException(String message) {
        super(message);
    }
}
