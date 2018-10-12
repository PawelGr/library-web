package com.pg.library.rest;

import com.pg.library.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private String name;
    private String surname;
    private String email;


    public static UserDto fromUser(User user) {
        UserDto dto = new UserDto();
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setEmail(user.getEmail());
        return dto;
    }

    public static User fromDto(UserDto dto) {
        User u = new User();
        u.setName(dto.getName());
        u.setSurname(dto.getSurname());
        u.setEmail(dto.getEmail());
        return u;
    }
}
