/**
 * |_|>
 * |_|>    Created by Dimyasha on 10.11.2021
 * |_|>
 */

package MethodsLab1;

import UTFE.TableOutput.Table;
import Utils.Pair;

import java.util.ArrayList;

public class GoldenRatioMethod extends OneDemMethod{
    public GoldenRatioMethod(Function func, Pair<Double, Double> interval) {
        super(func, interval);
    }

    @Override
    public Pair<Double, Double> Invoke() {
        double lambda = (Math.sqrt(5) - 1) / 2;

        double alpha = a + (1 - lambda) * (b - a);
        double beta = a + lambda * (b - a);

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
}
