import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Problem #465
 * Time complexity: O()
 * Space complexity: O()
 **/
public class OptimalAccountBalancing {

    public int minTransfers(int[][] transactions) {
        final Map<Integer, Integer> balances = new HashMap<>();
        for (final int[] t : transactions) {
            balances.put(t[0], balances.getOrDefault(t[0], 0) - t[2]);
            balances.put(t[1], balances.getOrDefault(t[1], 0) + t[2]);
        }

        final List<Integer> positives = collect(balances, v -> v > 0);
        if (positives.isEmpty()) {
            return 0;
        }
        final List<Integer> negatives = collect(balances, v -> v < 0);

        int minTransactions = Integer.MAX_VALUE;
        for (final List<Integer> posPerm : toPermutations(positives)) {
            for (List<Integer> negPerm : toPermutations(negatives)) {
                minTransactions = Math.min(minTransactions, resolveDebt(posPerm, negPerm));
            }
        }
        return minTransactions;
    }

    private static int resolveDebt(List<Integer> pos, List<Integer> neg) {
        final Deque<Integer> posStack = new ArrayDeque<>(pos);
        final Deque<Integer> negStack = new ArrayDeque<>(neg);
        int count = 0;
        while (!posStack.isEmpty()) {
            final int rem = posStack.pop() + negStack.pop();
            if (rem < 0) {
                negStack.push(rem);
            } else if (rem > 0) {
                posStack.push(rem);
            }
            count++;
        }
        return count;
    }

    private static List<Integer> collect(Map<?, Integer> balances, Predicate<Integer> predicate) {
        return balances.values().stream()
                .filter(predicate)
                .sorted()
                .collect(Collectors.toList());
    }

    private static Set<List<Integer>> toPermutations(List<Integer> list) {
       return toPermutations(list, new ArrayList<>(), new boolean[list.size()], new HashSet<>());
    }

    private static Set<List<Integer>> toPermutations(List<Integer> list, List<Integer> temp, boolean[] visited, Set<List<Integer>> result) {
        if (temp.size() == list.size()) {
            result.add(new ArrayList<>(temp));
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    temp.add(list.get(i));
                    toPermutations(list, temp, visited, result);
                    temp.remove(temp.size() - 1);
                    visited[i] = false;
                }
            }
        }
        return result;
    }


    public static void main(final String[] args) {
        System.out.println("2 == " + new OptimalAccountBalancing().minTransfers(new int[][]{{2,0,5},{3,4,4}}));
    }
}