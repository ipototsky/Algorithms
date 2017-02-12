package week3.assignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ipototsky on 1/28/17.
 */
public class BruteCollinearPoints {
    private List<LineSegment> segments = new ArrayList<>();

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException();
        }
        int length = points.length;
        for (int i = 0; i < length; i++) {
            if (points[i] == null) {
                throw new NullPointerException();
            }
        }

        Point[] copy = points.clone();
        Arrays.sort(copy);
        for (int i = 0; i < length - 1; i++) {
            Point current = copy[i];
            Point next = copy[i + 1];
            if (current.slopeTo(next) == Double.NEGATIVE_INFINITY) {
                throw new IllegalArgumentException();
            }
        }
        for (int a = 0; a < length - 3; a++) {
            Point p = copy[a];
            for (int b = a + 1; b < length - 2; b++) {
                Point q = copy[b];
                double qSlope = p.slopeTo(q);
                for (int c = b + 1; c < length - 1; c++) {
                    Point r = copy[c];
                    double rSlope = p.slopeTo(r);
                    if (Double.compare(qSlope, rSlope) == 0) {
                        for (int d = c + 1; d < length; d++) {
                            Point s = copy[d];
                            double sSlope = p.slopeTo(s);
                            if (Double.compare(rSlope, sSlope) == 0) {
                                segments.add(new LineSegment(p, s));
                            }
                        }
                    }
                }
            }
        }

    }

    public int numberOfSegments() {
        return segments.size();
    }

    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[segments.size()]);
    }
}
