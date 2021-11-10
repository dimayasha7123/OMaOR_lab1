package OMaOR_lab1;

import UTFE.TableOutput.Table;

import java.util.ArrayList;
import java.util.Random;

class Pair<X, Y> {
    public final X first;
    public final Y second;

    public Pair(X first, Y second) {
        this.first = first;
        this.second = second;
    }
}

public class Main {

    interface Func {
        double calc(double x);
    }

    static double EPS = Math.pow(10, -3);
    static double DELTA = Math.pow(10, -4);

    static Random RND = new Random();
    static double RndBetw(double l, double r) {
        return RND.nextDouble() * (r - l) + l;
    }

    static Pair<Double, Double> SvennAlgorithm(Func func) {
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
    }


    static Pair<Double, Double> halfDivisionMethod(Func func, Pair<Double, Double> interval) {
        double a = interval.first;
        double b = interval.second;
        int k = 1;

        ArrayList<Object[]> tableList = new ArrayList<>();
        tableList.add(new Object[]{"№", "interval", "X*", "f(X*)"});

        for (; b - a >= EPS; k++) {
            double middle = (a + b) / 2;
            double alpha = middle - DELTA;
            double beta = middle + DELTA;

            tableList.add(new Object[]{k, "[ " + a + ", " + b + "]", middle, func.calc(middle)});

            if (func.calc(alpha) < func.calc(beta)) b = beta;
            else a = alpha;
        }
        double x = (a + b) / 2;
        double fx = func.calc(x);

        tableList.add(new Object[]{k, "[ " + a + ", " + b + "]", x, fx});
        System.out.println("Метод половинного деления (дихотомии)");
        System.out.println(Table.TableToString(tableList.toArray(Object[][]::new)));

        return new Pair<>(x, fx);
    }

    static Pair<Double, Double> goldenRatioMethod(Func func, Pair<Double, Double> interval) {
        double a = interval.first;
        double b = interval.second;
        double lambda = (Math.sqrt(5) - 1) / 2;

        double alpha = a + (1 - lambda) * (b - a);
        double beta = a + lambda * (b - a);

        int k = 1;

        ArrayList<Object[]> tableList = new ArrayList<>();
        tableList.add(new Object[]{"№", "interval", "X*", "f(X*)"});

        for (; b - a >= EPS; ++k) {
            double middle = (a + b) / 2;
            tableList.add(new Object[]{k, "[ " + a + ", " + b + "]", middle, func.calc(middle)});

            if (func.calc(alpha) < func.calc(beta)) {
                b = beta;
                beta = alpha;
                alpha = a + (1 - lambda) * (b - a);
            } else {
                a = alpha;
                alpha = beta;
                beta = a + lambda * (b - a);
            }

        }
        double x = (a + b) / 2;
        double fx = func.calc(x);

        tableList.add(new Object[]{k, "[ " + a + ", " + b + "]", x, fx});
        System.out.println("Метод золотого сечения");
        System.out.println(Table.TableToString(tableList.toArray(Object[][]::new)));

        return new Pair<>(x, fx);
    }

    static int fibCount = 92;
    static long[] fibNumbers = new long[fibCount];

    static void precountFibonacci() {
        fibNumbers[0] = 0;
        fibNumbers[1] = 1;
        for (int i = 2; i < fibCount; ++i) {
            fibNumbers[i] = fibNumbers[i - 1] + fibNumbers[i - 2];
            if (fibNumbers[i] < 0) {
                System.out.println(i + "Переполнение");
                break;
            }
        }
    }

    static long getFib(int n) {
        return fibNumbers[n];
        //return (long) ((Math.pow((1 + Math.sqrt(5)) / 2, n) - Math.pow((1 - Math.sqrt(5)) / 2, n)) / Math.sqrt(5));
    }

    static Pair<Double, Double> fibonacciMethod(Func func, Pair<Double, Double> interval) {
        double a = interval.first;
        double b = interval.second;

        int n = 1;
        while (getFib(n) * EPS <= (b - a)) n++;

        double alpha = a + (b - a) * (double) getFib(n - 2) / getFib(n); //getFib(n - 2) / getFib(n)
        double beta = a + (b - a) * (double) getFib(n - 1) / getFib(n); //getFib(n - 1) / getFib(n + 1)

        ArrayList<Object[]> tableList = new ArrayList<>();
        tableList.add(new Object[]{"№", "interval", "X*", "f(X*)"});

        int k = 1;
        for (; k != n - 2; ++k) {
            double middle = (a + b) / 2;
            tableList.add(new Object[]{k, "[ " + a + ", " + b + "]", middle, func.calc(middle)});
            if (func.calc(alpha) >= func.calc(beta)) {
                a = alpha;
                alpha = beta;
                beta = a + (b - a) * (double) getFib(n - k) / getFib(n - k + 1); //getFib(n - k - 1) / getFib(n - k + 1)
            } else {
                b = beta;
                beta = alpha;
                alpha = a + (b - a) * (double) getFib(n - k - 1) / getFib(n - k + 1); //getFib(n - k - 2) / getFib(n - k)
            }
        }

        alpha = a;
        beta = alpha + DELTA;
        if (func.calc(alpha) > func.calc(beta)) a = alpha;
        else b = beta;

        double x = (a + b) / 2;
        double fx = func.calc(x);

        tableList.add(new Object[]{k, "[ " + a + ", " + b + "]", x, fx});
        System.out.println("Метод Фибоначчи");
        System.out.println(Table.TableToString(tableList.toArray(Object[][]::new)));

        return new Pair<>(x, fx);
    }

    public static void main(String[] args) {
        Table.SetDecimalPlaces(15);
        //Pair x = SvennAlgorithm(myFunction);

        precountFibonacci();


        Func myFunction = x -> (-1) * Math.exp((-1) * x) * Math.log(x);
        Pair<Double, Double> myInterval = new Pair<>(0.5, 3.0);

        Func testFunction = x -> (-1) * Math.PI * (27 * x - x * x * x) / 4;
        Pair<Double, Double> testInterval = new Pair<>(0.0, 5.2);

        System.out.println("EPS = " + EPS);
        System.out.println("DELTA = " + DELTA);

        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("ТЕСТОВЫЙ ПРИМЕР (из презентации)");
        System.out.println("-------------------------------------------------------------------------------------------");

        Pair<Double, Double> t1 = halfDivisionMethod(testFunction, testInterval);
        System.out.println("Ответ: " + t1.first + " " + t1.second + '\n');

        Pair<Double, Double> t2 = goldenRatioMethod(testFunction, testInterval);
        System.out.println("Ответ: " + t2.first + " " + t2.second + '\n');

        Pair<Double, Double> t3 = fibonacciMethod(testFunction, testInterval);
        System.out.println("Ответ: " + t3.first + " " + t3.second + '\n');

        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("МОЙ ПРИМЕР");
        System.out.println("-------------------------------------------------------------------------------------------");

        Pair<Double, Double> t4 = halfDivisionMethod(myFunction, myInterval);
        System.out.println("Ответ: " + t4.first + " " + t4.second + '\n');

        Pair<Double, Double> t5 = goldenRatioMethod(myFunction, myInterval);
        System.out.println("Ответ: " + t5.first + " " + t5.second + '\n');

        Pair<Double, Double> t6 = fibonacciMethod(myFunction, myInterval);
        System.out.println("Ответ: " + t6.first + " " + t6.second + '\n');
    }

}
