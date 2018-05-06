package present.programmer.algorithms.sandbox.sort.pattern.recognition;

import edu.princeton.cs.algs4.Stack;

import java.util.Arrays;

public class FastCollinearPoints {

    private final Stack<LineSegment> segments;

    public FastCollinearPoints(Point[] points) {
        requireNonNullValues(points);
        final Point[] sortablePoints = copyOf(points);
        Arrays.sort(sortablePoints);
        requireNoDuplicates(sortablePoints);
        segments = new Stack<>();
        findSegmentsOf4Points(sortablePoints);
    }

    public int numberOfSegments() {
        return segments.size();
    }

    public LineSegment[] segments() {
        final LineSegment[] array = new LineSegment[segments.size()];
        int i = 0;
        for (final LineSegment lineSegment : segments) {
            array[i++] = lineSegment;
        }
        return array;
    }

    private static void requireNonNullValues(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("argument must not be null");
        }
        for (final Point point : points) {
            if (point == null) {
                throw new IllegalArgumentException("points in array must not be null");
            }
        }
    }

    private static void requireNoDuplicates(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].equals(points[i + 1])) {
                throw new IllegalArgumentException("points must be unique");
            }
        }
    }

    private void findSegmentsOf4Points(final Point[] pointsThatShouldNotBeSorted) {
        final Point[] points = copyOf(pointsThatShouldNotBeSorted);
        for (final Point origin : pointsThatShouldNotBeSorted) {
            sortNaturallyWhichWillHelpFindingSegmentVertices(points);
            Arrays.sort(points, origin.slopeOrder());
            searchForSegments(points, origin);
        }
    }

    private static Point[] copyOf(final Point[] points) {
        final Point[] copy = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            copy[i] = points[i];
        }
        return copy;
    }

    private void searchForSegments(final Point[] points, final Point origin) {
        int i = 1;
        while (i < points.length - 2) {
            final Point q = points[i];
            final Point s = points[i + 2];
            double slopeToQ = origin.slopeTo(q);
            double slopeToS = origin.slopeTo(s);
            if (areFloatEqual(slopeToQ, slopeToS)) {
                i += 3;
                Point lastCollinearPoint = s;
                while (i < points.length && isNextPointCollinear(points[i], origin, slopeToS)) {
                    lastCollinearPoint = points[i];
                    i++;
                }
                if (isOriginAtTheBottom(origin, q)) {
                    segments.push(new LineSegment(origin, lastCollinearPoint));
                }
            } else {
                i++;
            }
        }
    }

    private boolean isOriginAtTheBottom(final Point origin, final Point q) {
        return origin.compareTo(q) < 0;
    }

    private static boolean isNextPointCollinear(final Point nextPoint, final Point origin, final double slope) {
        return areFloatEqual(slope, origin.slopeTo(nextPoint));
    }

    private static void sortNaturallyWhichWillHelpFindingSegmentVertices(final Point[] points) {
        Arrays.sort(points);
    }

    private static boolean areFloatEqual(final double x, final double y) {
        final double EPSILON = 0.00001;
        return Math.abs(x - y) < EPSILON;
    }
}
