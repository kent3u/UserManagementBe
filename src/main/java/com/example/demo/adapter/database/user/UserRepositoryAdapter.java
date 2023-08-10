package com.example.demo.adapter.database.user;

import com.example.demo.appdomin.user.TokenUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserDetailsService {

    private final UserEntityRepository userEntityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userEntityRepository.findByUsername(username);
        if (userEntity == null) {
            log.error("User not found by username {}", username);
            throw new UsernameNotFoundException("User not found in the database");
        }
        return new TokenUser(userEntity.getUsername(), userEntity.getPassword(), userEntity.getId());
    }
}
