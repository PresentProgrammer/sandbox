package present.programmer.algorithms.sandbox.sort.pattern.recognition;

import java.util.Arrays;
import java.util.Iterator;

public class BruteCollinearPoints {

    private final LineSegmentStack segments;

    public BruteCollinearPoints(Point[] points) {
        final Point[] sortablePoints = copyOf(points);
        requireNonNullValues(sortablePoints);
        Arrays.sort(sortablePoints);
        requireNoDuplicates(sortablePoints);
        segments = new LineSegmentStack();
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
        return segments.asArray();
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

    private static class LineSegmentStack implements Iterable<LineSegment> {

        private Node head;
        private int currentSize;

        void push(final LineSegment element) {
            head = new Node(element, head);
            currentSize++;
        }

        boolean isEmpty() {
            return head == null;
        }

        int size() {
            return currentSize;
        }

        LineSegment[] asArray() {
            final LineSegment[] array = new LineSegment[currentSize];
            int i = 0;
            for (final LineSegment lineSegment : this) {
                array[i++] = lineSegment;
            }
            return array;
        }

        @Override
        public Iterator<LineSegment> iterator() {
            return new PrimitiveIterator();
        }

        static class Node {

            LineSegment item;
            Node next;

            Node(final LineSegment item, final Node next) {
                this.item = item;
                this.next = next;
            }
        }

        private class PrimitiveIterator implements Iterator<LineSegment> {

            private Node nextNode;

            PrimitiveIterator() {
                this.nextNode = head;
            }

            @Override
            public LineSegment next() {
                final LineSegment result = nextNode.item;
                nextNode = nextNode.next;
                return result;
            }

            @Override
            public boolean hasNext() {
                return nextNode != null;
            }
        }
    }
}
