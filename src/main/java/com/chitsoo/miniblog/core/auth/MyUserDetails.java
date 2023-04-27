package com.chitsoo.miniblog.core.auth;

import com.chitsoo.miniblog.model.user.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Setter // 유저 정보 변경하게 되면, MyUserDetails의 User값을 변경해줘야 함.
@Getter
public class MyUserDetails implements UserDetails {
    private User user;

    public MyUserDetails(User user) {
        this.user = user;
    }

    // GrantedAuthority : 인증된 사용자의 권한 정보를 나타내는 인터페이스 (Role 또는 권한)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collector = new ArrayList<>();
        collector.add(() -> user.getRole()); // GrantedAuthority 인터페이스를 구현한 람다식으로 구현한 객체를 추가
        return collector; // 즉, 인증된 사용자가 가지고 있는 권한 정보를 람다식으로 구현한 객체로 생성하여 Collection 객체에 추가한 후 반환
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
