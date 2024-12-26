package ru.sidimekov.web_lab4_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sidimekov.web_lab4_backend.dto.HandledPointDTO;
import ru.sidimekov.web_lab4_backend.dto.PointDTO;
import ru.sidimekov.web_lab4_backend.model.AppUser;
import ru.sidimekov.web_lab4_backend.model.Point;
import ru.sidimekov.web_lab4_backend.repository.PointRepo;
import ru.sidimekov.web_lab4_backend.repository.UserRepo;
import ru.sidimekov.web_lab4_backend.util.AreaChecker;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class PointService {

    private final PointRepo pointRepo;
    private final UserRepo userRepo;
    private final JwtService jwtService;

    @Autowired
    public PointService(PointRepo pointRepo, UserRepo userRepo, JwtService jwtService) {
        this.pointRepo = pointRepo;
        this.userRepo = userRepo;
        this.jwtService = jwtService;
    }

    public List<HandledPointDTO> getAllPoints(String userToken) {
        String jwt = userToken.replace("Bearer ", "");

        String login = jwtService.extractLogin(jwt);
        AppUser appUser = userRepo.findByLogin(login);

        List<Point> points = pointRepo.findByAppUser(appUser);
        List<HandledPointDTO> handledPoints = new ArrayList<>();

        points.forEach(point -> {
            handledPoints.add(getHandledPointDTO(point));
        });

        return handledPoints;
    }

    public HandledPointDTO sendPoint(PointDTO reqPoint, String userToken) throws IllegalArgumentException {

        validatePoint(reqPoint);

        String jwt = userToken.replace("Bearer ", "");

        String login = jwtService.extractLogin(jwt);
        AppUser appUser = userRepo.findByLogin(login);

        Point point = new Point();
        point.setX(reqPoint.getX());
        point.setY(reqPoint.getY());
        point.setR(reqPoint.getR());

        point.setAppUser(appUser);

        handlePoint(point);

        pointRepo.save(point);

        return getHandledPointDTO(point);
    }

    private void handlePoint(Point point) {
        Instant start = Instant.now();
        boolean isInside = AreaChecker.checkArea(point.getX(), point.getY(), point.getR());
        Instant end = Instant.now();

        double execTime = Duration.between(start, end).toNanos() / 1_000_000.0;
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        point.setIn(isInside);
        point.setPoint_date(currentTime);
        point.setExecTime(execTime);
    }

    private HandledPointDTO getHandledPointDTO(Point point) {
        HandledPointDTO handledPoint = new HandledPointDTO();
        handledPoint.setX(point.getX());
        handledPoint.setY(point.getY());
        handledPoint.setR(point.getR());
        handledPoint.setIn(point.isIn());
        handledPoint.setPoint_date(point.getPoint_date());
        handledPoint.setExecTime(point.getExecTime());
        return handledPoint;
    }

    private void validatePoint(PointDTO point) throws IllegalArgumentException {

        if (point.getX() < -4 || point.getX() > 4) {
            throw new IllegalArgumentException("Значение X должно быть в диапазоне от -4 до 4");
        }
        if (point.getY() < -3 || point.getY() > 5) {
            throw new IllegalArgumentException("Значение Y должно быть в диапазоне от -3 до 5");
        }
        if (point.getR() < -4 || point.getR() > 4) {
            throw new IllegalArgumentException("Значение R должно быть в диапазоне от -4 до 4");
        }

    }
}
