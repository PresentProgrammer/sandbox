import java.util.ArrayList;
import java.util.List;

/**
 * Problem #77
 * Time complexity: O(N! / K! * (N - K)!)
 * Space complexity: O(K)
 **/
public class Combinations {

    public List<List<Integer>> combine(int n, int k) {
        return combine(n, k, new ArrayList<>(), new ArrayList<>(), 1);
    }

    private static List<List<Integer>> combine(int n, int k, List<Integer> temp, List<List<Integer>> result, int first) {
        if (temp.size() == k) {
            result.add(new ArrayList<>(temp));
        } else {
            for (int i = first; i <= n; i++) {
                temp.add(i);
                combine(n, k, temp, result, i + 1);
                temp.remove(temp.size() - 1);
            }
        }
        return result;
    }

    public static void main(final String[] args) {
        System.out.println("[[1, 2], [1, 3], [2, 3]] == " + new Combinations().combine(3, 2));
    }
}