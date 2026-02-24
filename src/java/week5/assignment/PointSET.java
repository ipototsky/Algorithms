package week5.assignment;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.Set;
import java.util.TreeSet;

public class PointSET {
    private TreeSet<Point2D> pointSet = new TreeSet<>();
    // construct an empty set of points
    public PointSET() {
    }

    // is the set empty?
    public boolean isEmpty() {
        return pointSet.isEmpty();
    }

    // number of points in the set
    public int size() {
        return pointSet.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        pointSet.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        return pointSet.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        for (Point2D point2D : pointSet) {
            StdDraw.point(point2D.x(), point2D.y());
        }
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        Set<Point2D> result = new TreeSet<>();
        for (Point2D point : pointSet) {
            if (rect.contains(point)) {
                result.add(point);
            }
        }
        return result;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        Point2D nearestPoint = null;
        double nearestDistance = Double.POSITIVE_INFINITY;
        for (Point2D point : pointSet) {
            if (point.distanceSquaredTo(p) < nearestDistance) {
                nearestPoint = point;
            }
        }
        return nearestPoint;
    }
}