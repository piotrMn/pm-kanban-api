package com.capgemini.upskill.KanbanApi.security;

import com.capgemini.upskill.KanbanApi.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.capgemini.upskill.KanbanApi.domain.User user = userRepository.findByEmail(username).orElseThrow();
        Set<GrantedAuthority> authorities = new HashSet<>();
        String role = user.getRole();
        if (role != null) {
            authorities.add((GrantedAuthority) () -> role);
        }
        return new User(user.getEmail(), user.getPasswordHash(), authorities);
    }

}
