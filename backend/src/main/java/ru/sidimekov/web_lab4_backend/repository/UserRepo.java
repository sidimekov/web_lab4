package ru.sidimekov.web_lab4_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sidimekov.web_lab4_backend.model.Users;

public interface UserRepo extends JpaRepository<Users, Long> {
    Users findByLogin(String login);
}
