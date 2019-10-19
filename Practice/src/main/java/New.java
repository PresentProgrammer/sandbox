/**
 * Problem #x
 * Time complexity: O()
 * Space complexity: O()
 **/
public class New {

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        final Set<List<List<Integer>> candidates = connections.stream()
                .map(Solution::standartize)
                .collect(Collectors.toCollection(HashSet::new));

        final Map<Integer, List<Integer>> adjacency = new HashMap<>();
        for (final List<Integer> con : candidates) {
            adjacency.computeIfAbsent(con[0], ignored -> new ArrayList<>())
                    .add(con[1]);
            adjacency.computeIfAbsent(con[1], ignored -> new ArrayList<>())
                    .add(con[0]);
        }

        final Set<Integer> visited = new HashSet<>();
        final Deque<List<Integer>> path = new ArrayDeque<>();
        final Deque<Integer> stack = new ArrayDeque<>();
        stack.push(connections.get(0).get(0));
        while (!stack.isEmpty()) {
            final Integer curr = stack.pop();
            if (visited.contains(curr)) {

            } else {
                path.push()
                visited.add(curr);
                for (final Integer next : adjacency.get(curr)) {
                    stack.push(next);
                }
            }
        }

        return candidates.stream().collect(Collectors.toList());
    }

    private static List<Integer> standartize(final List<Integer> con) {
        final List<Integer> copy = new ArrayList<>(con);
        copy.sort();
        return copy;
    }
}