import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Problem #380
 * Time complexity: O(1)
 * Space complexity: O(n)
 **/
public class RandomizedSet {

    private final Map<Integer, Integer> valueToIndex = new HashMap<>();
    private final List<Integer> values = new ArrayList<>();
    private final Random rand = new Random();

    public boolean insert(int val) {
        if (valueToIndex.containsKey(val)) {
            return false;
        } else {
            valueToIndex.put(val, values.size());
            values.add(val);
            return true;
        }
    }

    public boolean remove(int val) {
        final Integer index = valueToIndex.remove(val);
        if (index == null) {
            return false;
        } else {
            final int lastIndex = values.size() - 1;
            if (lastIndex != index) {
                final Integer lastValue = values.get(lastIndex);
                values.set(index, lastValue);
                valueToIndex.put(lastValue, index);
            }
            values.remove(lastIndex);
            return true;
        }
    }

    public int getRandom() {
        return values.get(rand.nextInt(values.size()));
    }
    
    public static void main(final String[] args) {
	}
}