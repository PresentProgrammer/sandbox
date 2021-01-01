import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Problem #49
 * Time complexity: O(n * m), where n is strs.length, and m - average size of strs.
 * Space complexity: O(n)
 **/
public class GroupAnagrams {

    public List<List<String>> groupAnagrams(final String[] strs) {
        final Map<List<Integer>, List<String>> groups = new HashMap<>();
        for (final String str : strs) {
            final List<Integer> countsOfStr = countsOf(str);
            final List<String> group = groups.getOrDefault(countsOfStr, new ArrayList<>());
            group.add(str);
            groups.put(countsOfStr, group);
        }
        return new ArrayList<>(groups.values());
    }

    private static List<Integer> countsOf(final String str) {
        final int[] counts = new int[26];
        for (int i = 0; i < str.length(); i++) {
            counts[str.charAt(i) - 'a']++;
        }
        final List<Integer> list = new ArrayList<>(26);
        for (final int count : counts) {
            list.add(count);
        }
        return list;
    }

    public static void main(final String[] args) {
        System.out.println(new GroupAnagrams().groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }
}