package com.capgemini.upskill.KanbanApi.service;

import com.capgemini.upskill.KanbanApi.domain.Team;
import com.capgemini.upskill.KanbanApi.domain.User;
import com.capgemini.upskill.KanbanApi.dto.UserDTO;
import com.capgemini.upskill.KanbanApi.mapper.UserMapper;
import com.capgemini.upskill.KanbanApi.repository.TeamRepository;
import com.capgemini.upskill.KanbanApi.repository.UserRepository;
import com.capgemini.upskill.KanbanApi.request.LoginUserRequest;
import com.capgemini.upskill.KanbanApi.request.RegisterUserRequest;
import com.capgemini.upskill.KanbanApi.response.LoginUserResponse;
import com.capgemini.upskill.KanbanApi.security.JwtService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final PasswordService passwordService;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, TeamRepository teamRepository, PasswordService passwordService, UserMapper userMapper, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.passwordService = passwordService;
        this.userMapper = userMapper;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public void registerUser(RegisterUserRequest request) throws NoSuchAlgorithmException, InvalidKeySpecException {
        logger.info("New user registration request {}", request);
        String hashedPassword = passwordService.hashPassword(request.getPassword(), passwordService.generateSalt());
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getUsername());
        user.setPasswordHash(hashedPassword);
        userRepository.save(user);
    }

    public LoginUserResponse loginUser(LoginUserRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        Authentication authentication = authenticationManager.authenticate(token);
        if (authentication.isAuthenticated()) {
            User user = userRepository.findByEmail(request.email()).orElseThrow();
            String jwt = jwtService.generateToken(request.email());
            return new LoginUserResponse(jwt, user.getName(), user.getEmail(), mapRolesToAuthorities(user.getRole()));
        } else {
            return null;
        }
    }

    public List<UserDTO> findUsersByTeam(UUID teamId) {
        List<User> byTeamId = userRepository.findByTeamId(teamId);
        return userMapper.toDTOs(byTeamId);
    }

    public UserDTO findUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        return userMapper.toDTO(user);
    }

    @Transactional
    public void addUserToTeam(String userId, String teamId) {
        logger.info("Adding user {} to team {}", userId, teamId);
        User user = userRepository.findById(UUID.fromString(userId)).orElseThrow();
        Team team = teamRepository.findById(UUID.fromString(teamId)).orElseThrow();
        team.getUsers().add(user);
        user.getTeams().add(team);
    }

    @Transactional
    public void removeUserFromTeam(UUID userId, UUID teamId) {
        Team team = teamRepository.findById(teamId).orElseThrow();
        User user = team.getUsers().stream().filter(u -> u.getId().equals(userId)).findAny().orElseThrow();
        team.getUsers().remove(user);
        user.getTeams().remove(team);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAllByOrderByNameAsc();
        return userMapper.toDTOs(users);
    }

    private String[] mapRolesToAuthorities(String role) {
        if ("ROLE_ADMIN".equalsIgnoreCase(role)) {
            return new String[]{"TABLE_READ", "TABLE_WRITE", "BOARD_READ", "BOARD_WRITE", "ITEM_READ", "ITEM_READ"};
        } else if ("ROLE_USER".equalsIgnoreCase(role)) {
            return new String[]{"TABLE_READ", "BOARD_READ", "ITEM_READ"};
        } else {
            return new String[]{};
        }
    }

}
