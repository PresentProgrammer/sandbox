import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Problem #47
 * Time complexity: O(N!)
 * Space complexity: O(N!)
 **/
public class PermutationsII {

    public List<List<Integer>> permuteUnique(int[] nums) {
        final List<Integer> temp = IntStream.of(nums).boxed().collect(Collectors.toCollection(ArrayList::new));
        return new ArrayList<>(permute(temp, 0, new HashSet<>()));
    }

    private static Set<List<Integer>> permute(List<Integer> temp, int depth, Set<List<Integer>> result) {
        if (depth == temp.size()) {
            result.add(new ArrayList<>(temp));
        } else {
            for (int i = depth; i < temp.size(); i++) {
                if (i == depth || !temp.get(depth).equals(temp.get(i))) {
                    Collections.swap(temp, depth, i);
                    permute(temp, depth + 1, result);
                    Collections.swap(temp, depth, i);
                }
            }
        }
        return result;
    }

    public static void main(final String[] args) {
        System.out.println(new PermutationsII().permuteUnique(new int[]{1, 1, 2}));
        System.out.println(new PermutationsII().permuteUnique(new int[]{1, 1, 2, 2}));
    }
}