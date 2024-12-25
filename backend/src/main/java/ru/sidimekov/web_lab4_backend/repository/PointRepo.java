package ru.sidimekov.web_lab4_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sidimekov.web_lab4_backend.model.AppUser;
import ru.sidimekov.web_lab4_backend.model.Point;

import java.util.List;

public interface PointRepo extends JpaRepository<Point, Long> {
    List<Point> findByAppUser(AppUser appUser);
}
