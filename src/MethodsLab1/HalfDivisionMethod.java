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

public class HalfDivisionMethod extends OneDemMethod {

    public HalfDivisionMethod(Function1Var func, Pair<Double, Double> interval) {
        super(func, interval);
    }

    @Override
    public Pair<Double, Double> Invoke() {
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
}
