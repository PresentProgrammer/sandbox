import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Problem #78
 * Time complexity: O(2^n)
 * Space complexity: O(2^n)
 **/
public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        final List<List<Integer>> result = new ArrayList<>();
        subsets(result, Collections.emptyList(), Arrays.stream(nums)
                .boxed()
                .collect(Collectors.toList()));
        return result;
    }

    private static void subsets(final List<List<Integer>> result, final List<Integer> base, final List<Integer> remaining) {
        result.add(base);
        for (Integer el : remaining) {
            if (base.isEmpty() || el > base.get(base.size() - 1)) {
                subsets(result, newWithElem(base, el), newWithoutElement(remaining, el));
            }
        }
    }

    private static List<Integer> newWithElem(final List<Integer> list, final Integer el) {
        final List<Integer> newList = new ArrayList<>(list);
        newList.add(el);
        return newList;
    }

    private static <T> List<T> newWithoutElement(final List<T> list, final T el) {
        final List<T> newList = new ArrayList<>(list);
        newList.remove(el);
        return newList;
    }
    
    public static void main(final String[] args) {
	}
}