package week4.balls;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

/**
 * @author ivan.pototsky on 05.04.2017.
 */
public class Ball {
    private double rx, ry;
    private double vx, vy;
    private final double radius;

    public Ball() {
        rx = StdRandom.uniform(0.0, 1.0);
        ry = StdRandom.uniform(0.0, 1.0);
        vx = StdRandom.uniform(-0.005, 0.005);
        vy = StdRandom.uniform(-0.005, 0.005);
        radius = 0.01;
    }

    public void move(double dt) {
        if ((rx + vx*dt < radius) || (rx + vx*dt > 1.0 - radius)) {
            vx = -vx;
        }
        if ((ry + vy*dt < radius) || (ry + vy*dt > 1.0 - radius)) {
            vy = -vy;
        }
        rx = rx + vx*dt;
        ry = ry + vy*dt;
    }

    public void draw() {
        StdDraw.filledCircle(rx, ry, radius);
    }
}