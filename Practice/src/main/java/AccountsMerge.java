import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Problem #721
 * Time complexity: O(N log N), where N — total count of emails
 * Space complexity: O(N)
 **/
public class AccountsMerge {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        final UnionFind uf = buildUnionFind(accounts);
        final Map<String, MergedAccount> rootToMerged = new HashMap<>();
        for (final List<String> account : accounts) {
            if (account.size() > 1) {
                final MergedAccount mergedAcc = rootToMerged.computeIfAbsent(uf.rootOf(account.get(1)),
                        root -> new MergedAccount(account.get(0)));
                for (int i = 1; i < account.size(); i++) {
                    mergedAcc.add(account.get(i));
                }
            }

        }
        return rootToMerged.values().stream()
                .map(MergedAccount::toList)
                .collect(Collectors.toList());
    }

    private static UnionFind buildUnionFind(List<List<String>> accounts) {
        final UnionFind uf = new UnionFind();
        for (final List<String> account : accounts) {
            for (int i = 2; i < account.size(); i++) {
                uf.union(account.get(i - 1), account.get(i));
            }
        }
        return uf;
    }

    private static class UnionFind {

        private final Map<String, String> parent = new HashMap<>();
        private final Map<String, Integer> size = new HashMap<>();

        void union(String first, String second) {
            final String firstRoot = rootOf(first);
            final String secondRoot = rootOf(second);
            final int firstSize = sizeOf(firstRoot);
            final int secondSize = sizeOf(secondRoot);
            if (firstSize <= secondSize) {
                parent.put(firstRoot, secondRoot);
                size.put(secondRoot, firstSize + secondSize);
            } else {
                parent.put(secondRoot, firstRoot);
                size.put(firstRoot, firstSize + secondSize);
            }
        }

        String rootOf(String s) {
            final String directParent = parent.computeIfAbsent(s, unused -> s);
            if (directParent.equals(s)) {
                return directParent;
            } else {
                final String root = rootOf(directParent);
                parent.put(s, root);
                return root;
            }
        }

        private int sizeOf(String s) {
            return size.computeIfAbsent(s, unused -> 1);
        }
    }

    private static class MergedAccount {
        private final String name;
        private final Set<String> emails = new HashSet<>();

        MergedAccount(String name) {
            this.name = name;
        }

        void add(String email) {
            emails.add(email);
        }

        List<String> toList() {
            return Stream.concat(Stream.of(name), emails.stream().sorted())
                    .collect(Collectors.toUnmodifiableList());
        }
    }
}