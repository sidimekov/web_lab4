package ru.sidimekov.web_lab4_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sidimekov.web_lab4_backend.dto.PointDTO;
import ru.sidimekov.web_lab4_backend.model.Point;
import ru.sidimekov.web_lab4_backend.model.Users;
import ru.sidimekov.web_lab4_backend.repository.PointRepo;
import ru.sidimekov.web_lab4_backend.repository.UserRepo;
import ru.sidimekov.web_lab4_backend.util.AreaChecker;
import ru.sidimekov.web_lab4_backend.util.JwtUtil;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PointService {

    private final PointRepo pointRepo;
    private final UserRepo userRepo;
    private final JwtUtil jwtUtil;

    @Autowired
    public PointService(PointRepo pointRepo, UserRepo userRepo, JwtUtil jwtUtil) {
        this.pointRepo = pointRepo;
        this.userRepo = userRepo;
        this.jwtUtil = jwtUtil;
    }

    public List<Point> getAllPoints(String userToken) {
        String jwt = userToken.replace("Bearer ", "");

        String login = jwtUtil.extractUsername(jwt);
        Users users = userRepo.findByLogin(login);

        return pointRepo.findByUsers(users);
    }

    public void sendPoint(PointDTO reqPoint, String userToken) {
        String jwt = userToken.replace("Bearer ", "");

        String login = jwtUtil.extractUsername(jwt);
        Users users = userRepo.findByLogin(login);

        Point point = new Point();
        point.setX(reqPoint.getX());
        point.setY(reqPoint.getY());
        point.setR(reqPoint.getR());

        point.setUsers(users);

        handlePoint(point);

        pointRepo.save(point);
    }

    private void handlePoint(Point point) {
        Instant start = Instant.now();
        boolean isInside = AreaChecker.checkArea(point.getX(), point.getY(), point.getR());
        Instant end = Instant.now();

        double execTime = Duration.between(start, end).toNanos() / 1_000_000.0;
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        point.setIn(isInside);
        point.setDate(currentTime);
        point.setExecTime(execTime);
    }
}
