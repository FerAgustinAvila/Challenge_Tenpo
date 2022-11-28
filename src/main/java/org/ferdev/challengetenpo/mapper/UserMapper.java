package org.ferdev.challengetenpo.mapper;

import org.ferdev.challengetenpo.dto.UserDto;
import org.ferdev.challengetenpo.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto map(User user);
}
