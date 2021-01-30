import java.util.stream.IntStream;

/**
 * Problem #552
 * Time complexity: O(N)
 * Space complexity: O(1)
 **/
public class StudentAttendanceRecordII {

    public int checkRecord(int n) {
        DP dp = DP.first();
        for (int i = 1; i < n; i++) {
            dp = dp.next();
        }
        return dp.result();
    }

    /**
     * Object represents counts of nodes at current depth.
     *
     * Notation:
     *     p   - P's that don't have A as ancestor
     *     pa  - P's that have A as ancestor
     *     l1  - L's that don't have A as ancestor, and don't have L as direct parent
     *     l1a - L's that have A as ancestor, and don't have L as direct parent
     *     l2  - L's that don't have A as ancestor, and have L as direct parent
     *     l2a - L's that have A as ancestor, and don't have L as direct parent
     *     a   - A's
     */
    private static class DP {

        private static final int MOD = 1_000_000_007;

        private int p;
        private int pa;
        private int l1;
        private int l1a;
        private int l2;
        private int l2a;
        private int a;

        static DP first() {
            final DP dp = new DP();
            dp.p = 1;
            dp.l1 = 1;
            dp.a = 1;
            return dp;
        }

        DP next() {
            final DP next = new DP();
            next.p = sum(p, sum(l1, l2));
            next.pa = sum(pa, sum(l1a, sum(l2a, a)));
            next.l1 = p;
            next.l1a = sum(pa, a);
            next.l2 = l1;
            next.l2a = l1a;
            next.a = sum(p, sum(l1, l2));
            return next;
        }

        int result() {
            return IntStream.of(p, pa, l1, l1a, l2, l2a, a)
                    .reduce(0, DP::sum);
        }

        private static int sum(int a, int b) {
            return (a + b) % MOD;
        }
    }

    public static void main(final String[] args) {
        System.out.println("3 == " + new StudentAttendanceRecordII().checkRecord(1));
        System.out.println("8 == " + new StudentAttendanceRecordII().checkRecord(2));
        System.out.println("19 == " + new StudentAttendanceRecordII().checkRecord(3));
        System.out.println("67802379 == " + new StudentAttendanceRecordII().checkRecord(10_000));
    }
}