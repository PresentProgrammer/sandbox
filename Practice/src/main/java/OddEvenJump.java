import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * Problem #975
 * Time complexity: O(N log N)
 * Space complexity: O(N)
 **/
public class OddEvenJump {

    public int oddEvenJumps(int[] arr) {
        final boolean[] odd = new boolean[arr.length];
        odd[odd.length - 1] = true;
        final boolean[] even = Arrays.copyOf(odd, odd.length);
        final TreeMap<Integer, Integer> pos = new TreeMap<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            final Map.Entry<Integer, Integer> ceiling = pos.ceilingEntry(arr[i]);
            if (ceiling != null) {
                odd[i] = even[ceiling.getValue()];
            }
            final Map.Entry<Integer, Integer> floor = pos.floorEntry(arr[i]);
            if (floor != null) {
                even[i] = odd[floor.getValue()];
            }
            pos.put(arr[i], i);
        }
        int result = 0;
        for (final boolean oddElem : odd) {
            if (oddElem) {
                result++;
            }
        }
        return result;
    }
}