package com.mycompany.learning.Application.Mappers;

import com.mycompany.learning.Application.Dtos.UserDto;
import com.mycompany.learning.Domain.Entities.User;

public class UserMapper {

    public static UserDto mapToDto(User user) {
        if (user == null) {
            return null;
        }
        UserDto dto = new UserDto();
        dto.id = user.getId();
        dto.firstName = user.getFirstName();
        dto.lastName = user.getLastName();
        dto.email = user.getEmail();
        dto.password = user.getPassword();
        dto.profilePictureUrl = user.getProfilePictureUrl();
        return dto;
    }

    public static User mapToEntity(UserDto dto) {
        if (dto == null) {
            return null;
        }
        User user = new User();
        user.setId(dto.id);
        user.setFirstName(dto.firstName);
        user.setLastName(dto.lastName);
        user.setEmail(dto.email);
        user.setPassword(dto.password);
        user.setProfilePictureUrl(dto.profilePictureUrl);
        return user;
    }
}

