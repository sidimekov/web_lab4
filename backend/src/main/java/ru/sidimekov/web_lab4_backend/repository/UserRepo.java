package ru.sidimekov.web_lab4_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sidimekov.web_lab4_backend.model.AppUser;

public interface UserRepo extends JpaRepository<AppUser, Long> {
    AppUser findByLogin(String login);
    boolean existsByLogin(String login);
}
