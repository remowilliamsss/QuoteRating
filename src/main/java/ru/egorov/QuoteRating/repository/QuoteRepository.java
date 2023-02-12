package ru.egorov.QuoteRating.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.egorov.QuoteRating.model.Quote;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Integer> {

    Quote findByContent(String content);
}
