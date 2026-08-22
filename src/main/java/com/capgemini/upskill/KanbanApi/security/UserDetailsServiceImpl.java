package com.capgemini.upskill.KanbanApi.security;

import com.capgemini.upskill.KanbanApi.dto.UserDTO;
import com.capgemini.upskill.KanbanApi.repository.UserRepository;
import com.capgemini.upskill.KanbanApi.service.UserService;
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.capgemini.upskill.KanbanApi.domain.User user = userRepository.findByEmail(username).orElseThrow();
        Set<GrantedAuthority> authorities = new HashSet<>();
        return new User(user.getEmail(), user.getPasswordHash(), authorities);
    }

}
