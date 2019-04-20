import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Problem #46
 * Time complexity: O(n ^ 2)
 * Space complexity: O(n ^ 2)
 **/
public class Permutations {

    public List<List<Integer>> permute(int[] nums) {
		final List<List<Integer>> result = new ArrayList<>();
		final Set<Integer> remaining = new HashSet<>();
		for (final int num : nums) {
		    remaining.add(num);
        }
		permute(result, new ArrayList<>(), remaining);
		return result;
    }

    private static void permute(List<List<Integer>> result, List<Integer> temp, Set<Integer> remaining) {
        if (remaining.isEmpty()) {
            result.add(new ArrayList<>(temp));
        } else {
            for (final Integer remainingEl : new ArrayList<>(remaining)) {
                temp.add(remainingEl);
                remaining.remove(remainingEl);
                permute(result, temp, remaining);
                temp.remove(temp.size() - 1);
                remaining.add(remainingEl);
            }
        }
    }
    
    public static void main(final String[] args) {
        System.out.println(new Permutations().permute(new int[]{ 1, 2, 3 }));
	}
}