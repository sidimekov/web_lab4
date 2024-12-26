package ru.sidimekov.web_lab4_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HandledPointDTO {
    private double x, y, r;

    private boolean isIn;

    private String point_date;

    private double execTime;
}
