/**
 * |_|>
 * |_|>    Created by Dimyasha on 14.11.2021
 * |_|>
 */

package Functions.Dots;

public class Dot2Var extends Dot1Var{
    protected double y;

    public double getY() {
        return y;
    }

    public Dot2Var(double x, double y) {
        super(x);
        this.y = y;
    }
}
