package week4.balls;

import edu.princeton.cs.algs4.StdDraw;

/**
 * @author ivan.pototsky on 05.04.2017.
 */
public class BouncingBalls {
    public static void main(String[] args) {
        int length = Integer.parseInt(args[0]);
        Ball[] balls = new Ball[length];
        for (int i = 0; i < length; i++) {
            balls[i] = new Ball();
        }
        while (true) {
            StdDraw.clear();
            for (int i = 0; i < length; i++) {
                balls[i].move(0.5);
                balls[i].draw();
            }
            StdDraw.show(50);
        }
    }
}