package ru.sidimekov.web_lab4_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sidimekov.web_lab4_backend.model.Point;
import ru.sidimekov.web_lab4_backend.model.User;

import java.util.List;

@Repository
public interface PointRepo extends JpaRepository<Point, Long> {
    List<Point> findByUser(User user);
}
