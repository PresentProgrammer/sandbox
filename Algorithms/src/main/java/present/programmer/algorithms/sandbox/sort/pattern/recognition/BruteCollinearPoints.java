package present.programmer.algorithms.sandbox.sort.pattern.recognition;

import edu.princeton.cs.algs4.Stack;

import java.util.Arrays;

public class BruteCollinearPoints {

    private final Stack<LineSegment> segments;

    public BruteCollinearPoints(Point[] points) {
        final Point[] sortablePoints = copyOf(points);
        requireNonNullValues(sortablePoints);
        Arrays.sort(sortablePoints);
        requireNoDuplicates(sortablePoints);
        segments = new Stack<>();
        findLineSegmentsOf4Points(sortablePoints);
    }

    private static Point[] copyOf(final Point[] points) {
        final Point[] copy = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            copy[i] = points[i];
        }
        return copy;
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

    private void findLineSegmentsOf4Points(Point[] points) {
        for (int p = 0; p < points.length - 3; p++) {
            for (int q = p + 1; q < points.length - 2; q++) {
                for (int r = q + 1; r < points.length - 1; r++) {
                    for (int s = r + 1; s < points.length; s++) {
                        Point pointP = points[p];
                        Point pointQ = points[q];
                        Point pointR = points[r];
                        Point pointS = points[s];
                        if (areCollinear(pointP, pointQ, pointR, pointS)) {
                            segments.push(new LineSegment(pointP, pointS));
                        }
                    }
                }
            }
        }
    }

    private boolean areCollinear(final Point p, final Point q, final Point r, final Point s) {
        final double slopePQ = p.slopeTo(q);
        final double slopeQR = q.slopeTo(r);
        final double slopeRS = r.slopeTo(s);
        return slopePQ == slopeQR && slopeQR == slopeRS;
    }
}
