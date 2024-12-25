package ru.sidimekov.web_lab4_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sidimekov.web_lab4_backend.model.Point;
import ru.sidimekov.web_lab4_backend.model.User;
import ru.sidimekov.web_lab4_backend.repository.PointRepo;
import ru.sidimekov.web_lab4_backend.repository.UserRepo;
import ru.sidimekov.web_lab4_backend.util.JwtUtil;

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
        User user = userRepo.findByLogin(login);

        return pointRepo.findByUser(user);
    }

    public void sendPoint(Point point, String userToken) {
        String jwt = userToken.replace("Bearer ", "");

        String login = jwtUtil.extractUsername(jwt);
        User user = userRepo.findByLogin(login);

        point.setUser(user);

        pointRepo.save(point);
    }

}
