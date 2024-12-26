package ru.sidimekov.web_lab4_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sidimekov.web_lab4_backend.dto.UserDTO;
import ru.sidimekov.web_lab4_backend.exception.AuthException;
import ru.sidimekov.web_lab4_backend.model.AppUser;
import ru.sidimekov.web_lab4_backend.repository.UserRepo;

@Service
public class AuthService {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Autowired
    public AuthService(UserRepo userRepo, BCryptPasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String login(UserDTO userDTO) throws AuthenticationException {
        String login = userDTO.getLogin();
        String requestPassword = userDTO.getPassword();
        AppUser loginAppUser = userRepo.findByLogin(login);
        if (loginAppUser != null && passwordEncoder.matches(requestPassword, loginAppUser.getPassword())) {
            return jwtService.generateToken(loginAppUser.getLogin());
        }
        throw new AuthException("Invalid username or password");
    }

    public String register(UserDTO userDTO) throws AuthenticationException {
        String login = userDTO.getLogin();
        String password = userDTO.getPassword();

        if (!userRepo.existsByLogin(login)) {
            AppUser appUser = new AppUser();
            appUser.setLogin(login);
            appUser.setPassword(passwordEncoder.encode(password));
            this.userRepo.save(appUser);

            return jwtService.generateToken(appUser.getLogin());
        }
        throw new AuthException("This login already registered");
    }
}
