package com.chitsoo.miniblog.core.auth;

import com.chitsoo.miniblog.model.user.User;
import com.chitsoo.miniblog.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {
    // UserDetailsService를 구현하는 이유는
    // UsernameAuthenticaionFilter가 UserDetailsService의 loadUserByUsername을 때리고
    // 로그인을 하는데, 우리가 이걸 지금 커스터마이징 하기 때문.
    // 커스터마이징을 안하면 시큐리티의 패스워드를 집어넣어야 하고 id는 user밖에 없게 된다.

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userPS = userRepository.findByUsername(username).orElseThrow( // 인증 안됨
                () -> new UsernameNotFoundException("Bad Credential") // 이래야 failHandler가 처리 <= translate Filter 라고 시큐리티가 Exception 처리하는 거가 발동.
        );
        return new MyUserDetails(userPS); // 그럼 이제 얘가 authentication에 담김
    }
}
