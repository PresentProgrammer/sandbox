package present.programmer.algorithms.sandbox.collection.map.kdtree;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KdTree {

    private Node root;
    private int size;

    /**
     * construct an empty set of points
     */
    public KdTree() {
    }

    /**
     * is the set empty?
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * number of points in the set
     */
    public int size() {
        return size;
    }

    /**
     * add the point to the set (if it is not already in the set)
     */
    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("No null allowed!");
        }
        if (isEmpty()) {
            root = new VertNode(p);
            size++;
        } else {
            if (root.insert(p)) {
                size++;
            }
        }
    }

    /**
     * does the set contain point p?
     */
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("No null allowed!");
        }
        return !isEmpty() && root.contains(p);
    }

    /**
     * draw all points to standard draw
     */
    public void draw() {
        if (!isEmpty()) {
            root.draw();
        }
    }

    /**
     * all points that are inside the rectangle (or on the boundary)
     */
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException("No null allowed!");
        }
        return isEmpty() ? new ArrayList<>()
                : root.range(rect, wholeArea(), new ArrayList<>());
    }

    /**
     * a nearest neighbor in the set to point p; null if the set is empty
     */
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("No null allowed!");
        }
        return isEmpty() ? null : root.nearest(p, wholeArea(), new Point2D(3.0, 3.0));
    }

    private static RectHV wholeArea() {
        return new RectHV(0.0, 0.0, 1.0, 1.0);
    }

    private static class Node {

        private final Point2D point;
        private Node left;
        private Node right;

        Node(Point2D point) {
            this.point = point;
        }

        Comparator<Point2D> pointComparator() {
            throw new UnsupportedOperationException();
        };

        Node createChild(Point2D p) {
            throw new UnsupportedOperationException();
        };

        void drawLine() {
            throw new UnsupportedOperationException();
        };

        RectHV leftBound(RectHV bound) {
            throw new UnsupportedOperationException();
        };

        RectHV rightBound(RectHV bound) {
            throw new UnsupportedOperationException();
        };

        boolean insert(Point2D p) {
            if (point.equals(p)) {
                return false;
            } else {
                if (pointComparator().compare(p, point) <= 0) {
                    if (left == null) {
                        left = createChild(p);
                        return true;
                    } else {
                        return left.insert(p);
                    }
                } else {
                    if (right == null) {
                        right = createChild(p);
                        return true;
                    } else {
                        return right.insert(p);
                    }
                }
            }
        }

        boolean contains(Point2D p) {
            if (point.equals(p)) {
                return true;
            } else {
                if (pointComparator().compare(p, point) <= 0) {
                    return left != null && left.contains(p);
                } else {
                    return right != null && right.contains(p);
                }
            }
        }

        void draw() {
            StdDraw.setPenColor();
            StdDraw.setPenRadius(0.01);
            point.draw();
            StdDraw.setPenRadius();
            drawLine();
            if (left != null) {
                left.draw();
            }
            if (right != null) {
                right.draw();
            }
        }

        List<Point2D> range(RectHV rect, RectHV bound, List<Point2D> inRange) {
            if (rect.contains(point)) {
                inRange.add(point);
            }

            if (left != null) {
                final RectHV leftBound = leftBound(bound);
                if (rect.intersects(leftBound)) {
                    left.range(rect, leftBound, inRange);
                }
            }

            if (right != null) {
                final RectHV rightBound = rightBound(bound);
                if (rect.intersects(rightBound)) {
                    right.range(rect, rightBound, inRange);
                }
            }

            return inRange;
        }

        Point2D nearest(Point2D p, RectHV bound, Point2D nearestFromParent) {
            Point2D nearestSoFar = point.distanceSquaredTo(p) < nearestFromParent.distanceSquaredTo(p)
                    ? point : nearestFromParent;

            final RectHV leftBound = leftBound(bound);
            final RectHV rightBound = rightBound(bound);

            final RectHV firstBound;
            final Node firstNode;
            if (leftBound.contains(p)) {
                firstBound = leftBound;
                firstNode = left;
            } else {
                firstBound = rightBound;
                firstNode = right;
            }
            if (firstNode != null) {
                nearestSoFar = firstNode.nearest(p, firstBound, nearestSoFar);
            }

            final RectHV secondBound = firstBound == leftBound ? rightBound : leftBound;
            final Node secondNode = firstBound == leftBound ? right : left;
            if (secondNode != null && secondBound.distanceSquaredTo(p) < nearestSoFar.distanceSquaredTo(p)) {
                nearestSoFar = secondNode.nearest(p, secondBound, nearestSoFar);
            }

            return nearestSoFar;
        }

        double x() {
            return point.x();
        }

        double y() {
            return point.y();
        }
    }

    private static class HorizNode extends Node {

        private static final Comparator<Point2D> COMPARATOR = Comparator.comparingDouble(Point2D::y);

        public HorizNode(Point2D point) {
            super(point);
        }

        @Override
        Comparator<Point2D> pointComparator() {
            return COMPARATOR;
        }

        @Override
        Node createChild(Point2D p) {
            return new VertNode(p);
        }

        @Override
        void drawLine() {
            StdDraw.setPenColor(Color.BLUE);
            StdDraw.line(0.0, y(), 1.0, y());
        }

        @Override
        RectHV leftBound(RectHV bound) {
            return new RectHV(bound.xmin(), bound.ymin(), bound.xmax(), y());
        }

        @Override
        RectHV rightBound(RectHV bound) {
            return new RectHV(bound.xmin(), y(), bound.xmax(), bound.ymax());
        }
    }

    private static class VertNode extends Node {

        private static final Comparator<Point2D> COMPARATOR = Comparator.comparingDouble(Point2D::x);

        public VertNode(Point2D point) {
            super(point);
        }

        @Override
        Comparator<Point2D> pointComparator() {
            return COMPARATOR;
        }

        @Override
        Node createChild(Point2D p) {
            return new HorizNode(p);
        }

        @Override
        void drawLine() {
            StdDraw.setPenColor(Color.RED);
            StdDraw.line(x(), 0.0, x(), 1.0);
        }

        @Override
        RectHV leftBound(RectHV bound) {
            return new RectHV(bound.xmin(), bound.ymin(), x(), bound.ymax());
        }

        @Override
        RectHV rightBound(RectHV bound) {
            return new RectHV(x(), bound.ymin(), bound.xmax(), bound.ymax());
        }
    }
}
