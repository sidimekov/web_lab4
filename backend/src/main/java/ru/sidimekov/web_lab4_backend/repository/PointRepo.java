package ru.sidimekov.web_lab4_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sidimekov.web_lab4_backend.model.Point;
import ru.sidimekov.web_lab4_backend.model.Users;

import java.util.List;

public interface PointRepo extends JpaRepository<Point, Long> {
    List<Point> findByUsers(Users users);
}
