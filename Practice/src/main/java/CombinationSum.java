import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Problem #39
 **/
public class CombinationSum {

    /**
     * Time complexity: O(N ^ (T / M + 2)), where N = candidates.length, T = target, M = min(candidates)
     *     Explanation: N ^ (T / M + 1) = number of nodes, * N for copying leaf elements.
     * Space complexity: O(T / M)
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        return combinationSumBacktracking(candidates, target, new ArrayList<>(), new ArrayList<>(), 0, 0);
    }

    private static List<List<Integer>> combinationSumBacktracking(int[] candidates, int target,
            List<List<Integer>> result, List<Integer> temp, int tempSum, int first) {
        if (tempSum == target) {
            result.add(new ArrayList<>(temp));
        } else if (tempSum < target) {
            for (int i = first; i < candidates.length; i++) {
                temp.add(candidates[i]);
                combinationSumBacktracking(candidates, target, result, temp, tempSum + candidates[i], i);
                temp.remove(temp.size() - 1);
            }
        }
        return result;
    }

    public List<List<Integer>> combinationSumDP(int[] candidates, int target) {
        Arrays.sort(candidates);
        final List<List<List<Integer>>> dp = new ArrayList<>();
        for (int i = 0; i <= target; i++) {
            final List<List<Integer>> curr = new ArrayList<>();
            for (final int c : candidates) {
                if (i - c > 0) {
                    for (final List<Integer> prevEl : dp.get(i - c)) {
                        if (prevEl.get(prevEl.size() - 1) <= c) {
                            final List<Integer> currEl = new ArrayList<>(prevEl);
                            currEl.add(c);
                            curr.add(currEl);
                        } else {
                            break;
                        }
                    }
                } else if (i - c == 0) {
                    curr.add(IntStream.of(c).boxed().collect(Collectors.toList()));
                } else {
                    break;
                }
            }
            dp.add(curr);
        }
        return dp.get(target);
    }

    public static void main(final String[] args) {
        System.out.println("[[2, 2, 3], [7]] == " + new CombinationSum().combinationSum(new int[]{2, 3, 6, 7}, 7));
        System.out.println("[[2, 2, 3], [7]] == " + new CombinationSum().combinationSumDP(new int[]{2, 3, 6, 7}, 7));
    }
}