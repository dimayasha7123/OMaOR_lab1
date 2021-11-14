/**
 * |_|>
 * |_|>    Created by Dimyasha on 14.11.2021
 * |_|>
 */

package Functions.Abstract;

import Functions.Dots.Dot1Var;

public abstract class Function {

    public double calc(double x){
        return calc(new Dot1Var(x));
    }

    public double calc(Dot1Var x){
        Function subFunc = new Function() {
            @Override
            public double calc(Function subFunc, Dot1Var dot) {
                return dot.getX();
            }
        };
        return calc(subFunc, x);
    }
    public abstract double calc(Function subFunc, Dot1Var dot);
}
