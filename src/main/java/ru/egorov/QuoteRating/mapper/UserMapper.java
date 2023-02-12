package ru.egorov.QuoteRating.mapper;

import org.mapstruct.Mapper;
import ru.egorov.QuoteRating.dto.UserDto;
import ru.egorov.QuoteRating.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);
}
