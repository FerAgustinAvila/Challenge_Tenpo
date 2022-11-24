package org.ferdev.challengetenpo.challengetenpo.mapper;

import org.ferdev.challengetenpo.challengetenpo.dto.UserDto;
import org.ferdev.challengetenpo.challengetenpo.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto map(User user);
}
