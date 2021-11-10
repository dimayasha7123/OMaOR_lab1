/**
 * |_|>
 * |_|>    Created by Dimyasha on 10.11.2021
 * |_|>
 */

package MethodsLab1;

import Utils.Pair;

public abstract class OneDemMethod {
    static final double EPS = Math.pow(10, -3);
    static final double DELTA = Math.pow(10, -4);

    public static double getEPS(){
        return EPS;
    }

    public static double getDELTA(){
        return DELTA;
    }

    double a;
    double b;
    int k = 1;
    Function func;

    public OneDemMethod(Function func, Pair<Double, Double> interval){
        a = interval.first;
        b = interval.second;
        this.func = func;
    }

    abstract Pair<Double, Double> Invoke();
}
