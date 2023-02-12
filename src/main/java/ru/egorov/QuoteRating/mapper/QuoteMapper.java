package ru.egorov.QuoteRating.mapper;

import org.mapstruct.Mapper;
import ru.egorov.QuoteRating.dto.QuoteDto;
import ru.egorov.QuoteRating.model.Quote;

@Mapper(componentModel = "spring", uses = {UserMapper.class, VoteListMapper.class})
public interface QuoteMapper {

    QuoteDto toDto(Quote quote);
}
