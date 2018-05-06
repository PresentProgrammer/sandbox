package present.programmer.algorithms.sandbox.sort.pattern.recognition;

public class LineSegment {

    private final Point p;
    private final Point q;

    public LineSegment(Point p, Point q) {
        if (p == null || q == null) {
            throw new NullPointerException("argument is null");
        }
        this.p = p;
        this.q = q;
    }

    public void draw() {
        p.drawTo(q);
    }

    public String toString() {
        return p + " -> " + q;
    }

    /**
     * The hashCode() method is not supported because hashing has not yet been introduced in this course.
     * Moreover, hashing does not typically lead to good *worst-case* performance guarantees, as required on this assignment.
     */
    public int hashCode() {
        throw new UnsupportedOperationException();
    }
}
