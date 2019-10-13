import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.TreeMap;

/**
 * Problem #975
 * Time complexity: O(N log N)
 * Space complexity: O(N)
 **/
public class OddEvenJump {

    public int oddEvenJumps(int[] arr) {
        final int[] oddWhere = calcWhereToJump(arr, true);
        final int[] evenWhere = calcWhereToJump(arr, false);

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

    private static int[] calcWhereToJump(final int[] arr, final boolean odd) {
        final int[] result = new int[arr.length];
        Arrays.fill(result, -1);
        final TreeMap<Integer, Integer> valuePos = new TreeMap<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            final Integer identicalValue = valuePos.get(arr[i]);
            if (identicalValue != null) {
                result[i] = identicalValue;
            } else {
                final Integer closestKey = odd ? valuePos.higherKey(arr[i]) : valuePos.lowerKey(arr[i]);
                if (closestKey != null) {
                    result[i] = valuePos.get(closestKey);
                }
            }
            valuePos.put(arr[i], i);
        }
        return result;
    }
}