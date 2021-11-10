/**
 * |_|>
 * |_|>    Created by Dimyasha on 10.11.2021
 * |_|>
 */

package MethodsLab1;

import java.util.Random;

public class SvennAlgorithm {
    static Random RND = new Random();

    static double RndBetw(double l, double r) {
        return RND.nextDouble() * (r - l) + l;
    }

    /*static Pair<Double, Double> SvennAlgorithm(Main.Func func) {
        double t = 5;
        ArrayList<Double> xq = new ArrayList<>();
        double bound = 100;
        xq.add(RndBetw(0, bound));

        double xLeft = xq.get(0) - t;
        double xRight = xq.get(0) + t;

        double left = func.calc(xLeft);
        double middle = func.calc(xq.get(0));
        double right = func.calc(xRight);

        if (left >= middle && right >= middle) {
            System.out.println("Инфы не будет");
            return new Pair<>(xLeft, xRight);
        }
        if (middle >= left && middle >= right) return SvennAlgorithm(func);

        double delta = 0;
        double a = 0;
        double b = 0;

        boolean f = false;
        if (left >= middle && middle >= right) {
            delta = t;
            a = xq.get(0);
            xq.add(xRight);
            f = true;
        }
        if (left <= middle && middle <= right) {
            delta = -t;
            b = xq.get(0);
            xq.add(xLeft);
            f = true;
        }
        if (!f) System.out.println("Не зашел в ифы");
        int k = 1;
        while (true) {
            xq.add(xq.get(k) + Math.pow(2, k) * delta);
            if (func.calc(xq.get(k + 1)) > func.calc(xq.get(k))) {
                if (delta == t) b = xq.get(k + 1);
                if (delta == -t) a = xq.get(k + 1);
                if (delta != Math.abs(t)) System.out.println("delta != abs(t)");
                break;
            }
            if (func.calc(xq.get(k + 1)) < func.calc(xq.get(k)) && delta == t) a = xq.get(k);
            if (func.calc(xq.get(k + 1)) < func.calc(xq.get(k)) && delta == -t) b = xq.get(k);
            k++;
        }
        System.out.println("Алгоритм Свенна");
        Object[][] table = new Object[][]{
                {"Param", "Value"},
                {"t", t},
                {"bounds", bound},
                {"k", k},
                {"a", a},
                {"b", b}
        };
        System.out.println(Table.TableToString(table));
        return new Pair<>(a, b);
    }*/


}
