import java.util.*;

/**
 * Created by ipototsky on 1/29/17.
 */
public class FastCollinearPoints {
    private List<LineSegment> segments = new ArrayList<>();
    private Map<Double, List<Point>> originsBySlope = new HashMap<>();

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
            Arrays.sort(originSorted, i + 1, length, comparator);
            double slope = Double.NEGATIVE_INFINITY;
            int b = i + 1;
            while (b < length) {
                Point current = originSorted[b];
                if (Double.compare(slope, origin.slopeTo(current)) == 0) {
                    pointsOnLineCounter++;
                } else {
                    if (pointsOnLineCounter >= 3) {
                        Point lastWithTheSameSlope = originSorted[b - 1];
                        addLineSegmentIfAbsent(origin, slope, lastWithTheSameSlope);


                    }
                    pointsOnLineCounter = 1;
                    slope = origin.slopeTo(current);
                }
                b++;
            }
            if (pointsOnLineCounter >= 3) {
                addLineSegmentIfAbsent(origin, slope, originSorted[b - 1]);
            }
        }
    }

    private void addLineSegmentIfAbsent(Point origin, double slope, Point lastWithTheSameSlope) {
        List<Point> origins = originsBySlope.get(slope);
        if (origins == null) {
            origins = new ArrayList<>();
            originsBySlope.put(slope, origins);
            addLineSegment(origin, lastWithTheSameSlope, origins);
        } else {
            if (!origins.contains(origin)) {
                for (Point prevOrigin : origins) {
                    if (Double.compare(slope, prevOrigin.slopeTo(origin)) == 0) {
                        return;
                    }
                }
                addLineSegment(origin, lastWithTheSameSlope, origins);
            }
        }
    }

    private void addLineSegment(Point origin, Point lastWithTheSameSlope, List<Point> endpoints) {
        endpoints.add(lastWithTheSameSlope);
        LineSegment segment = new LineSegment(origin, lastWithTheSameSlope);
        segments.add(segment);
    }


    public int numberOfSegments() {
        return segments.size();
    }

    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[numberOfSegments()]);
    }
}
