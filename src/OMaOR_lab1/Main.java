package OMaOR_lab1;

import MethodsLab1.*;
import MethodsLab1.Abstract.Function1Var;
import MethodsLab1.Abstract.OneDemMethod;
import UTFE.TableOutput.Table;
import Utils.Pair;


public class Main {
    public static void main(String[] args) {
        Table.SetDecimalPlaces(15);

        Function1Var myFunction = x -> (-1) * Math.exp((-1) * x) * Math.log(x);
        Pair<Double, Double> myInterval = new Pair<>(0.5, 3.0);

        Function1Var testFunction = x -> (-1) * Math.PI * (27 * x - x * x * x) / 4;
        Pair<Double, Double> testInterval = new Pair<>(0.0, 5.2);

        System.out.printf("EPS = %.3f\n", OneDemMethod.getEPS());
        System.out.printf("DELTA = %.4f\n", OneDemMethod.getDELTA());

        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("ТЕСТОВЫЙ ПРИМЕР (из презентации)");
        System.out.println("-------------------------------------------------------------------------------------------");

        Pair<Double, Double> t1 = (new HalfDivisionMethod(testFunction, testInterval)).Invoke();
        System.out.println("Ответ: " + t1.first + " " + t1.second + '\n');

        Pair<Double, Double> t2 = (new GoldenRatioMethod(testFunction, testInterval)).Invoke();
        System.out.println("Ответ: " + t2.first + " " + t2.second + '\n');

        Pair<Double, Double> t3 = (new FibonacciMethod(testFunction, testInterval)).Invoke();
        System.out.println("Ответ: " + t3.first + " " + t3.second + '\n');

        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("МОЙ ПРИМЕР");
        System.out.println("-------------------------------------------------------------------------------------------");

        Pair<Double, Double> t4 = (new HalfDivisionMethod(myFunction, myInterval)).Invoke();
        System.out.println("Ответ: " + t4.first + " " + t4.second + '\n');

        Pair<Double, Double> t5 = (new GoldenRatioMethod(myFunction, myInterval)).Invoke();
        System.out.println("Ответ: " + t5.first + " " + t5.second + '\n');

        Pair<Double, Double> t6 = (new FibonacciMethod(myFunction, myInterval)).Invoke();
        System.out.println("Ответ: " + t6.first + " " + t6.second + '\n');
    }
}
