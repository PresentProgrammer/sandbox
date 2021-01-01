/**
 * Problem #149
 * Time complexity: O(n ^ 3)
 * Space complexity: O(1)
 **/
public class MaxPointsOnLine {

    public int maxPoints(int[][] points) {
        if (points == null) {
            return 0;
        } else if (points.length < 3) {
            return points.length;
        } else {
            int maxOnLine = 2;
            for (int i = 0; i < points.length - 1; i++) {
                int sameCoordinatesWithI = 1;
                for (int j = i + 1; j < points.length; j++) {
                    if (points[i][0] == points[j][0] && points[i][1] == points[j][1]) {
                        sameCoordinatesWithI++;
                        maxOnLine = Math.max(maxOnLine, sameCoordinatesWithI);
                    } else {
                        int currLineCount = sameCoordinatesWithI + 1;
                        if (points[i][0] == points[j][0]) {
                            for (int k = j + 1; k < points.length; k++) {
                                if (points[k][0] == points[j][0]) {
                                    currLineCount++;
                                }
                            }
                        } else {
                            final double m = (double) (points[j][1] - points[i][1]) / (points[j][0] - points[i][0]);
                            final double b = points[j][1] - m * points[j][0];
                            for (int k = j + 1; k < points.length; k++) {
                                if (points[j][0] == points[k][0]) {
                                    if (points[j][1] == points[k][1]) {
                                        currLineCount++;
                                    }
                                } else {
                                    final double mk = (double) (points[j][1] - points[k][1]) / (points[j][0] - points[k][0]);
                                    final double bk = points[k][1] - mk * points[k][0];
                                    if (equalWithEps(m, mk) && equalWithEps(b, bk)) {
                                        currLineCount++;
                                    }
                                }
                            }
                        }
                        maxOnLine = Math.max(maxOnLine, currLineCount);
                    }
                }
            }
            return maxOnLine;
        }
    }

    private static boolean equalWithEps(final double left, final double right) {
        return Math.abs(right - left) < 0.0000000001;
    }

    private static class Point {
        int x;
        int y;

        Point() {
        }

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }

    public static void main(final String[] args) {
        System.out.println("3 == " + new MaxPointsOnLine().maxPoints(new int[][]{{1, 1}, {2, 2}, {3, 3}}));
        System.out.println("4 == " + new MaxPointsOnLine().maxPoints(new int[][]{{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}}));
        System.out.println("3 == " + new MaxPointsOnLine().maxPoints(new int[][]{{1, 1}, {1, 1}, {2, 3}}));
        System.out.println("2 == " + new MaxPointsOnLine().maxPoints(new int[][]{{0, 0}, {94911151, 94911150}, {94911152, 94911151}}));
        System.out.println("4 == " + new MaxPointsOnLine().maxPoints(new int[][]{{1, 1}, {1, 1}, {2, 2}, {2, 2}}));
    }
}