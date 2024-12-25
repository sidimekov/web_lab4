package ru.sidimekov.web_lab4_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double x;

    @Column(nullable = false)
    private double y;

    @Column(nullable = false)
    private double r;

    @Column(nullable = false)
    private boolean isIn;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private double execTime;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

}
