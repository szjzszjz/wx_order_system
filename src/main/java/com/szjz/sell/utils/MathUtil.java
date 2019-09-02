package com.szjz.sell.utils;

/**
 * @author szjz
 * @date 2019/5/13 19:46
 */
public class MathUtil {

    private final static double MONEY_RANGE = 0.01;

    public static boolean equal(double d1, double d2) {
        double abs = Math.abs(d1 - d2);
        if (abs < MONEY_RANGE) {
            return true;
        } else {
            return false;
        }
    }
}
