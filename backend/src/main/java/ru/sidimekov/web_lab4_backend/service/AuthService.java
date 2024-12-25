package ru.sidimekov.web_lab4_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sidimekov.web_lab4_backend.dto.UserDTO;
import ru.sidimekov.web_lab4_backend.exception.AuthException;
import ru.sidimekov.web_lab4_backend.model.User;
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
        User loginUser = userRepo.findByLogin(login);
        if (loginUser != null && passwordEncoder.matches(requestPassword, loginUser.getPassword())) {
            return jwtUtil.generateToken(loginUser.getLogin());
        }
        throw new AuthException("Invalid username or password");
    }

    public String register(UserDTO userDTO) throws AuthenticationException {
        String login = userDTO.getLogin();
        String password = userDTO.getPassword();

        User loginUser = userRepo.findByLogin(login);

        if (loginUser == null) {
            User user = new User();
            user.setLogin(login);
            user.setPassword(passwordEncoder.encode(password));
            this.userRepo.save(user);

            return jwtUtil.generateToken(user.getLogin());
        }
        throw new AuthException("This login already registered");
    }
}
