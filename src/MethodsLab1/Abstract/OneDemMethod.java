/**
 * |_|>
 * |_|>    Created by Dimyasha on 10.11.2021
 * |_|>
 */

package MethodsLab1.Abstract;

import Utils.Pair;

public abstract class OneDemMethod {
    protected static final double EPS = Math.pow(10, -3);
    protected static final double DELTA = Math.pow(10, -4);

    public static double getEPS(){
        return EPS;
    }

    public static double getDELTA(){
        return DELTA;
    }

    protected double a;
    protected double b;
    protected int k = 1;
    protected Function1Var func;

    public OneDemMethod(Function1Var func, Pair<Double, Double> interval){
        a = interval.first;
        b = interval.second;
        this.func = func;
    }

    protected abstract Pair<Double, Double> Invoke();
}
