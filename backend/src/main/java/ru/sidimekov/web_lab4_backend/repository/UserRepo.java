package ru.sidimekov.web_lab4_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sidimekov.web_lab4_backend.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByLogin(String login);
}
