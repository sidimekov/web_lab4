package ru.sidimekov.web_lab4_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sidimekov.web_lab4_backend.model.Point;
import ru.sidimekov.web_lab4_backend.service.PointService;

import java.util.List;

@RestController
@RequestMapping("/api/points")
public class PointController {

    private final PointService pointService;

    @Autowired
    public PointController(PointService pointService) {
        this.pointService = pointService;
    }

    @GetMapping
    public List<Point> getAllPoints(@RequestHeader("Authorization") String token) {
        return pointService.getAllPoints(token);
    }

    @PostMapping("/sendPoint")
    public void sendPoint(@RequestBody Point point, @RequestHeader("Authorization") String token) {
        pointService.sendPoint(point, token);
    }
}
