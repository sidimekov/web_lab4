package ru.sidimekov.web_lab4_backend.util;

public class AreaChecker {
    public static boolean checkArea(double x, double y, double r) {
        if (x >= 0 && y >= 0) {
            if (x <= r && y <= r) {
                return true;
            }
        }
        if (x >= 0 && y <= 0) {
            if (y >= Math.sqrt(3) * x / 2 - r) {
                return true;
            }
        }
        if (x <= 0 && y <= 0) {
            if (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2)) {
                return true;
            }
        }
        return false;
    }
}
