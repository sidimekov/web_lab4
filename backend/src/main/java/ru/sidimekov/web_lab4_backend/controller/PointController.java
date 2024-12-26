package ru.sidimekov.web_lab4_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sidimekov.web_lab4_backend.dto.HandledPointDTO;
import ru.sidimekov.web_lab4_backend.dto.PointDTO;
import ru.sidimekov.web_lab4_backend.model.Point;
import ru.sidimekov.web_lab4_backend.service.PointService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/points")
public class PointController {

    private final PointService pointService;

    @Autowired
    public PointController(PointService pointService) {
        this.pointService = pointService;
    }

    @GetMapping
    public ResponseEntity<?> getAllPoints(@RequestHeader("Authorization") String token) {
        List<HandledPointDTO> handledPoints = pointService.getAllPoints(token);
        return ResponseEntity.ok(handledPoints);
    }

    @PostMapping("/sendPoint")
    public ResponseEntity<?> sendPoint(
            @RequestBody PointDTO pointDTO,
            @RequestHeader("Authorization") String token
    ) {
        try {
            HandledPointDTO handledPoint = pointService.sendPoint(pointDTO, token);
            return ResponseEntity.ok(handledPoint);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
