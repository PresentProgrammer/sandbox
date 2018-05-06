package present.programmer.algorithms.sandbox.sort.pattern.recognition;

import java.util.Arrays;
import java.util.Iterator;

public class FastCollinearPoints {

    private final LineStack segments;

    public FastCollinearPoints(Point[] points) {
        requireNonNullValues(points);
        Arrays.sort(points);
        requireNoDuplicates(points);
        segments = new LineStack();
        findSegmentsOf4Points(points);
    }

    public int numberOfSegments() {
        return segments.size();
    }

    public LineSegment[] segments() {
        return segments.asArrayOfUniqueLineSegments();
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

    private void findSegmentsOf4Points(final Point[] points) {
        for (final Point origin : unmodifiableCopyOf(points)) {
            sortNaturally_willHelpFindingSegmentVertices(points);
            Arrays.sort(points, origin.slopeOrder());
            searchForSegments(points, origin);
        }
    }

    private static Point[] unmodifiableCopyOf(final Point[] points) {
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
                    segments.push(new Line(origin, lastCollinearPoint));
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

    private static class Line implements Comparable<Line> {

        private final Point first;
        private final Point last;

        private Line(final Point first, final Point last) {
            this.first = first;
            this.last = last;
        }

        private Point getFirst() {
            return first;
        }

        private Point getLast() {
            return last;
        }

        @Override
        public int compareTo(final Line that) {
            int comparisonOfFirstPoint = this.first.compareTo(that.first);
            return comparisonOfFirstPoint == 0 ? this.last.compareTo(that.last) : comparisonOfFirstPoint;
        }

        @Override
        public String toString() {
            return "Line{" +
                    "first=" + first +
                    ", last=" + last +
                    '}';
        }
    }

    private static class LineStack implements Iterable<Line> {

        private Node head;
        private int currentSize;

        void push(final Line element) {
            head = new Node(element, head);
            currentSize++;
        }

        boolean isEmpty() {
            return head == null;
        }

        int size() {
            return currentSize;
        }

        LineSegment[] asArrayOfUniqueLineSegments() {
            final LineStack resultStack = new LineStack();
            final Line[] lines = copyLines();
            if (lines.length > 0) {
                Arrays.sort(lines);
                resultStack.push(lines[0]);
                for (int i = 1; i < lines.length; i++) {
                    if (lines[i].compareTo(lines[i - 1]) != 0) {
                        resultStack.push(lines[i]);
                    } else {
                        System.out.println("removed duplicate: " + lines[i]);
                    }
                }
            }
            final LineSegment[] lineSegments = new LineSegment[resultStack.size()];
            int i = 0;
            for (final Line line : resultStack) {
                lineSegments[i++] = new LineSegment(line.getFirst(), line.getLast());
            }
            return lineSegments;
        }

        private Line[] copyLines() {
            final Line[] array = new Line[currentSize];
            int i = 0;
            for (final Line line : this) {
                array[i++] = line;
            }
            return array;
        }

        @Override
        public Iterator<Line> iterator() {
            return new PrimitiveIterator();
        }

        static class Node {

            Line item;
            Node next;

            Node(final Line item, final Node next) {
                this.item = item;
                this.next = next;
            }
        }

        private class PrimitiveIterator implements Iterator<Line> {

            private Node nextNode;

            PrimitiveIterator() {
                this.nextNode = head;
            }

            @Override
            public Line next() {
                final Line result = nextNode.item;
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
