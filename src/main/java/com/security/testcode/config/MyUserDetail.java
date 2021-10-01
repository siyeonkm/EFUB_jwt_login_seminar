package com.security.testcode.config;

import com.security.testcode.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class MyUserDetail implements UserDetails {
    private String email;
    private String password;
    private String auth;

    public MyUserDetail(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.auth = "ROLE_" + user.getRole();
    }

    //계정이 갖는 권한 목록 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.auth));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    //만료되지않았는지
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //잠겨있지않은지
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //패스워드가 만료되지않았는지
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //사용가능한 계정인지
    @Override
    public boolean isEnabled() {
        return true;
    }
}
