package ru.egorov.QuoteRating.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.egorov.QuoteRating.dto.VoteDto;
import ru.egorov.QuoteRating.model.Vote;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface VoteMapper {

    @Mapping(target = "user", source = "embeddedId.user")
    VoteDto toDto(Vote vote);
}
