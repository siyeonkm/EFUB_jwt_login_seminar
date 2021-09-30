package com.security.testcode.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserRequestDto {
    private String email;
    private String password;

    public UserRequestDto (String email, String password) {
        this.email = email;
        this.password = password;
    }
}
