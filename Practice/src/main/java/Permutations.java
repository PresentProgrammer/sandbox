import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Problem #46
 * Time complexity: O(N!)
 * Space complexity: O(N!)
 **/
public class Permutations {

    public List<List<Integer>> permute(int[] nums) {
        return permute(nums, new ArrayList<>(nums.length), new boolean[nums.length], new ArrayList<>());
    }

    private static List<List<Integer>> permute(int[] nums, List<Integer> soFar, boolean[] visited, List<List<Integer>> result) {
        if (soFar.size() == nums.length) {
            result.add(new ArrayList<>(soFar));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    soFar.add(nums[i]);
                    permute(nums, soFar, visited, result);
                    soFar.remove(soFar.size() - 1);
                    visited[i] = false;
                }
            }
        }
        return result;
    }

    public static void main(final String[] args) {
        Instant start = Instant.now();
        final List<List<Integer>> permutations = new Permutations().permute(new int[] { 1, 2, 3 });
        System.out.println("Execution took " + Duration.between(start, Instant.now()));
        System.out.println(permutations);
    }
}