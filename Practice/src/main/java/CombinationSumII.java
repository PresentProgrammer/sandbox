import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Problem #40
 * Time complexity: O(2 ^ N)
 * Space complexity: O(N)
 **/
public class CombinationSumII {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        return combine(sorted(candidates), new ArrayList<>(), new ArrayList<>(), target, 0);
    }

    private static List<List<Integer>> combine(int[] candidates,
            List<Integer> temp, List<List<Integer>> result,
            int rem, int first) {
        if (rem == 0) {
            result.add(new ArrayList<>(temp));
        } else if (rem > 0) {
            for (int i = first; i < candidates.length; i++) {
                if (i == first || candidates[i - 1] != candidates[i]) {
                    temp.add(candidates[i]);
                    combine(candidates, temp, result, rem - candidates[i], i + 1);
                    temp.remove(temp.size() - 1);
                }
            }
        }
        return result;
    }

    private static int[] sorted(int[] arr) {
        return IntStream.of(arr)
                .sorted()
                .toArray();
    }

    public static void main(final String[] args) {
        System.out.println("[[1, 2, 2], [5]] == " + new CombinationSumII().combinationSum2(new int[]{2, 5, 2, 1, 2}, 5));
    }
}