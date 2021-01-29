import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<List<Integer>> permuteWithSwap(int[] nums) {
        final List<Integer> original = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
        return permuteWithSwap(original, 0, new ArrayList<>());
    }

    private static List<List<Integer>> permuteWithSwap(List<Integer> soFar, int depth, List<List<Integer>> result) {
        if (depth == soFar.size()) {
            result.add(new ArrayList<>(soFar));
        } else {
            for (int i = depth; i < soFar.size(); i++) {
                Collections.swap(soFar, i, depth);
                permuteWithSwap(soFar, depth + 1, result);
                Collections.swap(soFar, i, depth);
            }
        }
        return result;
    }

    public static void main(final String[] args) {
        System.out.println(new Permutations().permute(new int[]{1, 2, 3}));
        System.out.println(new Permutations().permuteWithSwap(new int[]{1, 2, 3}));
    }
}