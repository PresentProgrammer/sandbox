package present.programmer.algorithms.sandbox.union;

import java.util.stream.IntStream;

public class QuickFind implements UnionFind {

    private final int[] id;

    public QuickFind(final int n) {
        this.id = IntStream.range(0, n).toArray();
    }

    @Override
    public boolean union(final int p, final int q) {
        if (id[p] == id[q]) {
            return false;
        } else {
            final int idP = id[p];
            for (int i = 0; i < id.length; i++) {
                if (id[i] == idP) {
                    id[i] = id[q];
                }
            }
            return true;
        }
    }

    @Override
    public boolean connected(final int p, final int q) {
        return id[p] == id[q];
    }
}
