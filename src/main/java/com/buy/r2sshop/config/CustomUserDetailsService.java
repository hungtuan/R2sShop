package com.buy.r2sshop.config;

import com.buy.r2sshop.entity.Role;
import com.buy.r2sshop.entity.User;
import com.buy.r2sshop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), getAuthorities(user.getRole())
        );
    }


    private Collection<? extends GrantedAuthority> getAuthorities(Role role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role.getName()));
    }
}
