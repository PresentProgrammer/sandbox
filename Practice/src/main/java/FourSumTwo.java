import java.util.Arrays;

/**
 * Problem #454
 * Time complexity: O(n ^ 3)
 * Space complexity: O(n)
 **/
public class FourSumTwo {

    public int fourSumCount(int[] inputA, int[] inputB, int[] inputC, int[] inputD) {
        final int[] a = copyAndSort(inputA);
        final int[] b = copyAndSort(inputB);
        final int[] c = copyAndSort(inputC);
        final int[] d = copyAndSort(inputD);

        int result = 0;
        int iaResult = 0;
        for (int ia = 0; ia < a.length; ia++) {
            if (ia != 0 && a[ia - 1] == a[ia]) {
                result += iaResult;
            } else {
                iaResult = 0;
                int ibResult = 0;
                for (int ib = 0; ib < b.length; ib++) {
                    if (ib != 0 && b[ib - 1] == b[ib]) {
                        iaResult += ibResult;
                    } else {
                        ibResult = 0;
                        final int abSumNeg = -(a[ia] + b[ib]);
                        int ic = 0;
                        int id = d.length - 1;
                        while (ic < c.length && id >= 0) {
                            final int cdSum = c[ic] + d[id];
                            if (cdSum < abSumNeg) {
                                ic++;
                            } else if (cdSum > abSumNeg) {
                                id--;
                            } else {
                                int cDuplicates = 1;
                                while (ic + 1 < c.length && c[ic] == c[ic + 1]) {
                                    ic++;
                                    cDuplicates++;
                                }
                                int dDuplicates = 1;
                                while (id - 1 >= 0 && d[id - 1] == d[id]) {
                                    id--;
                                    dDuplicates++;
                                }
                                ic++;
                                id--;
                                ibResult += cDuplicates * dDuplicates;
                            }
                        }
                        iaResult += ibResult;
                    }
                }
                result += iaResult;
            }
        }
        return result;
    }

    private static int[] copyAndSort(final int[] input) {
        final int[] copy = Arrays.copyOf(input, input.length);
        Arrays.sort(copy);
        return copy;
    }

    public static void main(final String[] args) {
        System.out.println("2 == " + new FourSumTwo().fourSumCount(
                new int[]{1, 2},
                new int[]{-2, -1},
                new int[]{-1, 2},
                new int[]{0, 2}
        ));
        System.out.println("132 == " + new FourSumTwo().fourSumCount(
                new int[]{-1, 1, 1, 1, -1},
                new int[]{0, -1, -1, 0, 1},
                new int[]{-1, -1, 1, -1, -1},
                new int[]{0, 1, 0, -1, -1}
        ));
    }
}