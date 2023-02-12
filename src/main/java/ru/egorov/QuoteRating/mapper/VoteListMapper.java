package ru.egorov.QuoteRating.mapper;

import org.mapstruct.Mapper;
import ru.egorov.QuoteRating.dto.VoteDto;
import ru.egorov.QuoteRating.model.Vote;

import java.util.List;

@Mapper(componentModel = "spring", uses = VoteMapper.class)
public interface VoteListMapper {

    List<VoteDto> toDtoList(List<Vote> votes);
}
