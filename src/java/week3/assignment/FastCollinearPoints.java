package week3.assignment;

import java.util.*;

/**
 * Created by ipototsky on 1/29/17.
 */
public class FastCollinearPoints {
    private List<LineSegment> segments = new ArrayList<>();
    private List<Point> pointSameSlope = new ArrayList<>();

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException();
        }
        int length = points.length;
        for (int i = 0; i < length; i++) {
            if (points[i] == null) {
                throw new NullPointerException();
            }
        }
        Point[] naturalSorted = points.clone();
        Arrays.sort(naturalSorted);
        for (int i = 0; i < length - 1; i++) {
            Point current = naturalSorted[i];
            Point next = naturalSorted[i + 1];
            if (current.slopeTo(next) == Double.NEGATIVE_INFINITY) {
                throw new IllegalArgumentException();
            }
        }
        Point[] originSorted = naturalSorted.clone();
        for (int i = 0; i < length; i++) {
            Arrays.sort(originSorted);
            int pointsOnLineCounter = 0;
            Point origin = naturalSorted[i];
            Comparator<Point> comparator = origin.slopeOrder();
            Arrays.sort(originSorted, comparator);
            double slope = Double.NEGATIVE_INFINITY;
            int b = 1;
            pointSameSlope.clear();
            while (b < length) {
                Point current = originSorted[b];
                if (Double.compare(slope, origin.slopeTo(current)) == 0) {
                    pointSameSlope.add(current);
                    pointsOnLineCounter++;
                } else {

                    if (pointsOnLineCounter >= 3) {
                        addLineSegment(origin, i, naturalSorted);
                    }
                    pointSameSlope.clear();
                    pointsOnLineCounter = 1;
                    slope = origin.slopeTo(current);
                    pointSameSlope.add(current);
                }
                b++;
            }
            if (pointsOnLineCounter >= 3) {
                addLineSegment(origin, i, naturalSorted);
                pointSameSlope.clear();
            }
        }
    }

    private int binarySearch(Point[] naturalSorted, Point point) {
        int lo = 0;
        int hi = naturalSorted.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int compare = point.compareTo(naturalSorted[mid]);
            if (compare < 0) {
                hi = mid - 1;
            } else if (compare > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    private void addLineSegment(Point origin, int indexOfOrigin, Point[] naturalSorted) {
        int size = pointSameSlope.size();
        for (int i = 0; i < size; i++) {
            Point current = pointSameSlope.get(i);
            int index = binarySearch(naturalSorted, current);
            if (index < indexOfOrigin) {        //We found subsegment. We already have this line.
                return;
            }
        }
        LineSegment segment = new LineSegment(origin, pointSameSlope.get(size-1));
        segments.add(segment);
    }

    public int numberOfSegments() {
        return segments.size();
    }

    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[numberOfSegments()]);
    }
}
