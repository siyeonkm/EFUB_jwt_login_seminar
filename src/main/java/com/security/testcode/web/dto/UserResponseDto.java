package com.security.testcode.web.dto;

import com.security.testcode.domain.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private Long userNum;
    private String password;
    private String email;

    public UserResponseDto(User entity) {
        this.userNum = entity.getUserNum();
        this.password = entity.getPassword();
        this.email = entity.getEmail();
    }
}
