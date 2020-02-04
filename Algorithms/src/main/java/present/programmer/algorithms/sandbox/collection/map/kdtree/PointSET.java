package present.programmer.algorithms.sandbox.collection.map.kdtree;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class PointSET {

    private final TreeSet<Point2D> points;

    /**
     * construct an empty set of points
     */
    public PointSET() {
        this.points = new TreeSet<>();
    }

    /**
     * is the set empty?
     */
    public boolean isEmpty() {
        return points.isEmpty();
    }

    /**
     * number of points in the set
     */
    public int size() {
        return points.size();
    }

    /**
     * add the point to the set (if it is not already in the set)
     */
    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("No null allowed!");
        }
        points.add(p);
    }

    /**
     * does the set contain point p?
     */
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("No null allowed!");
        }
        return points.contains(p);
    }

    /**
     * draw all points to standard draw
     */
    public void draw() {
        for (final Point2D point : points) {
            point.draw();
        }
    }

    /**
     * all points that are inside the rectangle (or on the boundary)
     */
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException("No null allowed!");
        }
        return points.stream()
              .filter(rect::contains)
              .collect(Collectors.toList());
    }

    /**
     * a nearest neighbor in the set to point p; null if the set is empty
     */
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("No null allowed!");
        }
        return points
                .stream()
                .min(Comparator.comparingDouble(p::distanceSquaredTo))
                .orElse(null);
    }
}
