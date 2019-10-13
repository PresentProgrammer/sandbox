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
        final int[] oddWhere = calcWhereToJump(arr, (left, right) -> left - right);
        final int[] evenWhere = calcWhereToJump(arr, (left, right) -> right - left);

        System.out.println(Arrays.toString(oddWhere));
        System.out.println(Arrays.toString(evenWhere));

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
        final Deque<IndexValue> stack = new ArrayDeque<>();
        final int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && comparator.compare(stack.peek().value, arr[i]) <= 0) {
                result[stack.pop().index] = i;
            }
            stack.push(new IndexValue(i, arr[i]));
        }
        while (!stack.isEmpty()) {
            result[stack.pop().index] = -1;
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