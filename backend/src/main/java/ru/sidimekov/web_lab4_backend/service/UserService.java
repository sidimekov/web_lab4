package ru.sidimekov.web_lab4_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.sidimekov.web_lab4_backend.repository.UserRepo;

@Service
public class UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserDetails getByLogin(String login) throws UsernameNotFoundException {
        return userRepo.findByLogin(login);
    }

}
