package com.security.testcode.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNum;

    @Column
    private String nickname;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String role;

    @Builder
    public User(String nickname, String password, String email) {
        this.nickname = nickname;
        this.password = password;
        this.email =email;
        role = "USER";
    }
}
