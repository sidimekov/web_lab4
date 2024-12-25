package ru.sidimekov.web_lab4_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sidimekov.web_lab4_backend.dto.UserDTO;
import ru.sidimekov.web_lab4_backend.exception.AuthException;
import ru.sidimekov.web_lab4_backend.model.Users;
import ru.sidimekov.web_lab4_backend.repository.UserRepo;
import ru.sidimekov.web_lab4_backend.util.JwtUtil;

@Service
public class AuthService {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthService(UserRepo userRepo, BCryptPasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String login(UserDTO userDTO) throws AuthenticationException {
        String login = userDTO.getLogin();
        String requestPassword = userDTO.getPassword();
        Users loginUsers = userRepo.findByLogin(login);
        if (loginUsers != null && passwordEncoder.matches(requestPassword, loginUsers.getPassword())) {
            return jwtUtil.generateToken(loginUsers.getLogin());
        }
        throw new AuthException("Invalid username or password");
    }

    public String register(UserDTO userDTO) throws AuthenticationException {
        String login = userDTO.getLogin();
        String password = userDTO.getPassword();

        Users loginUsers = userRepo.findByLogin(login);

        if (loginUsers == null) {
            Users users = new Users();
            users.setLogin(login);
            users.setPassword(passwordEncoder.encode(password));
            this.userRepo.save(users);

            return jwtUtil.generateToken(users.getLogin());
        }
        throw new AuthException("This login already registered");
    }
}
