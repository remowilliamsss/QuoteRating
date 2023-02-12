package ru.egorov.QuoteRating.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.egorov.QuoteRating.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByName(String name);

    User findByNameOrEmail(String name, String email);

    boolean existsByName(String name);
}
