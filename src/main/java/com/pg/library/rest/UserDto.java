package com.pg.library.rest;

import com.pg.library.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter // getter + setter
@Setter
@NoArgsConstructor
public class UserDto {

    private String name;
    private String surname;
    private String course;


    public static UserDto fromUser(User user) {
        UserDto dto = new UserDto();
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setCourse(user.getCourse());
        return dto;
    }

    public static User fromDto(UserDto dto) {
        User u = new User();
        u.setName(dto.getName());
        u.setSurname(dto.getSurname());
        u.setCourse(dto.getCourse());
        return u;
    }
}
