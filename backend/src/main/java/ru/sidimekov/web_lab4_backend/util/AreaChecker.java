package ru.sidimekov.web_lab4_backend.util;

public class AreaChecker {
    public static boolean checkArea(double x, double y, double r) {
        if (r > 0) {
            if (x >= 0 && y >= 0) {
                if (x <= r && y <= r) {
                    return true;
                }
            }
            if (x >= 0 && y <= 0) {
                if (y >= Math.sqrt(3) * x - r) {
                    return true;
                }
            }
            if (x <= 0 && y <= 0) {
                if (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2)) {
                    return true;
                }
            }
            return false;
        } else if (r < 0) {
            if (x >= 0 && y >= 0) {
                if (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2)) {
                    return true;
                }
            }
            if (x <= 0 && y >= 0) {
                if (y <= Math.sqrt(3) * x - r) {
                    return true;
                }
            }
            if (x <= 0 && y <= 0) {
                if (x >= r && y >= r) {
                    return true;
                }
            }
            return false;
        } else return false;
    }
}
