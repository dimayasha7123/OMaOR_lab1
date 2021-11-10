/**
 * |_|>
 * |_|>    Created by Dimyasha on 10.11.2021
 * |_|>
 */

package MethodsLab1;

import MethodsLab1.Abstract.Function1Var;
import MethodsLab1.Abstract.OneDemMethod;
import UTFE.TableOutput.Table;
import Utils.Pair;

import java.util.ArrayList;

public class FibonacciMethod extends OneDemMethod {
    public FibonacciMethod(Function1Var func, Pair<Double, Double> interval) {
        super(func, interval);
        precountFibonacci();//один раз бы конечно
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

    @Override
    public Pair<Double, Double> Invoke() {
        int n = 1;
        while (getFib(n) * EPS <= (b - a)) n++;

        double alpha = a + (b - a) * (double) getFib(n - 2) / getFib(n); //getFib(n - 2) / getFib(n)
        double beta = a + (b - a) * (double) getFib(n - 1) / getFib(n); //getFib(n - 1) / getFib(n + 1)

        ArrayList<Object[]> tableList = new ArrayList<>();
        tableList.add(new Object[]{"№", "interval", "X*", "f(X*)"});

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
}