package present.programmer.algorithms.sandbox.sort.pattern.recognition;

import java.util.Arrays;
import java.util.Iterator;

public class FastCollinearPoints {

    private final LineSegmentStack segments;

    public FastCollinearPoints(Point[] points) {
        final Point[] sortablePoints = copyOf(points);
        requireNonNullValues(sortablePoints);
        Arrays.sort(sortablePoints);
        requireNoDuplicates(sortablePoints);
        segments = new LineSegmentStack();
        findSegmentsOf4Points(sortablePoints);
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

    private void findSegmentsOf4Points(final Point[] pointsThatShouldNotBeSorted) {
        final Point[] points = copyOf(pointsThatShouldNotBeSorted);
        for (final Point origin : pointsThatShouldNotBeSorted) {
            sortNaturally_willHelpFindingSegmentVertices(points);
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
            if (slopeToQ == slopeToS) {
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
        return slope == origin.slopeTo(nextPoint);
    }

    private static void sortNaturally_willHelpFindingSegmentVertices(final Point[] points) {
        Arrays.sort(points);
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
