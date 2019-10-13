import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;

/**
 * Problem #975
 * Time complexity: O()
 * Space complexity: O()
 **/
public class OddEvenJump {

    public int oddEvenJumps(int[] arr) {
        final int[] oddWhere = calcWhereToJump(arr, Comparator.naturalOrder());
        final int[] evenWhere = calcWhereToJump(arr, Comparator.comparingInt((Integer i) -> i).reversed());

        final Boolean[] oddReachable = new Boolean[arr.length];
        oddReachable[oddReachable.length - 1] = true;
        final Boolean[] evenReachable = Arrays.copyOf(oddReachable, oddReachable.length);
        for (int i = 0; i < arr.length; i++) {
            int currPos = i;
            boolean currOdd = true;
            final Deque<Integer> jumpsFrom = new ArrayDeque<>();
            jumpsFrom.push(currPos);
            Boolean isReachable = isReachable(currOdd, currPos, oddReachable, evenReachable, oddWhere, evenWhere);
            while (isReachable == null) {
                currPos = currOdd ? oddWhere[currPos] : evenWhere[currPos];
                jumpsFrom.push(currPos);
                currOdd = !currOdd;
                isReachable = isReachable(currOdd, currPos, oddReachable, evenReachable, oddWhere, evenWhere);
            }
            while (!jumpsFrom.isEmpty()) {
                final Boolean[] reachable = currOdd ? oddReachable : evenReachable;
                reachable[jumpsFrom.pop()] = isReachable;
                currOdd = !currOdd;
            }
        }

        System.out.println(Arrays.toString(oddReachable));
        int goodIndexes = 0;
        for (final Boolean reachable : oddReachable) {
            if (Boolean.TRUE.equals(reachable)) {
                goodIndexes++;
            }
        }
        return goodIndexes;
    }

    private static Boolean isReachable(final boolean currOdd, final int currPos,
            final Boolean[] oddReachable, final Boolean[] evenReachable,
            final int[] oddWhere, final int[] evenWhere) {
        if (currOdd) {
            if (oddReachable[currPos] == null) {
                return oddWhere[currPos] >= 0 ? null : false;
            } else {
                return oddReachable[currPos];
            }
        } else {
            if (evenReachable[currPos] == null) {
                return evenWhere[currPos] >= 0 ? null : false;
            } else {
                return evenReachable[currPos];
            }
        }
    }

    private static int[] calcWhereToJump(final int[] arr, final Comparator<Integer> comparator) {
        final int[] result = new int[arr.length];
        Arrays.fill(result, -1);
        final IndexValue[] aux = new IndexValue[arr.length];
        for (int i = 0; i < arr.length; i++) {
            aux[i] = new IndexValue(i, arr[i]);
        }
        Arrays.sort(aux, ((Comparator<IndexValue>) (left, right) -> comparator.compare(left.value, right.value))
                .thenComparingInt(indexValue -> indexValue.index));
        for (int i = 0; i < aux.length - 1; i++) {
            int j = i + 1;
            while (j < aux.length && aux[i].index > aux[j].index) {
                j++;
            }
            if (j < aux.length) {
                result[aux[i].index] = aux[j].index;
            }
        }
        return result;
    }

    private static class IndexValue {
        final int index;
        final int value;

        IndexValue(final int index, final int value) {
            this.index = index;
            this.value = value;
        }
    }
}