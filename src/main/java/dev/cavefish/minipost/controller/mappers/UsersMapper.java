package dev.cavefish.minipost.controller.mappers;

import dev.cavefish.minipost.domain.users.User;
import dev.cavefish.minipost.domain.users.UserCreateRequest;
import dev.cavefish.minipost.dto.UserCreateDto;
import dev.cavefish.minipost.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UsersMapper {
    public UserDto toDto(User user) {
        return new UserDto(user.alias(), user.email(), user.firstName(), user.lastName());
    }

    public UserCreateRequest fromDto(UserCreateDto dto) {
        return new UserCreateRequest(
                dto.getAlias(),
                dto.getPassword(),
                dto.getEmail(),
                dto.getFirstName(),
                dto.getLastName()
        );
    }
}
