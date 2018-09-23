package present.programmer.algorithms.sandbox.union;

public interface UnionFind {

    /**
     * As in Java Collections Framework's Collection interface, method returns `true` if the operation
     * has modified the object; false — otherwise. Effectively, method will return `true` if the nodes
     * have not been connected yet.
     */
    boolean union(int p, int q);

    boolean connected(int p, int q);
}
