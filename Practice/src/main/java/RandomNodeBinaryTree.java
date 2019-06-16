import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Problem #4.11
 **/
public class RandomNodeBinaryTree {

    private final Random random = new Random();
    private Node root;

    /**
     * Time: O(log n), O(n) in the worst case.
     * Space: O(log n), O(n) in the worst case.
     */
    public boolean insert(final int val) {
        if (root == null) {
            root = new Node(val);
        } else {
            final List<AtomicInteger> toIncrement = new ArrayList<>();
            Node curr = root;
            while (true) {
                if (val <= curr.val) {
                    toIncrement.add(curr.leftSize);
                    if (curr.left == null) {
                        curr.left = new Node(val);
                        break;
                    } else {
                        curr = curr.left;
                    }
                } else {
                    toIncrement.add(curr.rightSize);
                    if (curr.right == null) {
                        curr.right = new Node(val);
                        break;
                    } else {
                        curr = curr.right;
                    }
                }
            }
            for (final AtomicInteger incrementable : toIncrement) {
                incrementable.incrementAndGet();
            }
        }
        return true;
    }

    /**
     * Time: O(log n), O(n) in the worst case.
     * Space: O(log n), O(n) in the worst case.
     */
    public boolean delete(final int val) {
        final List<AtomicInteger> toDecrement = new ArrayList<>();
        Node curr = root;
        Node parent = null;
        Direction direction = null;
        while (curr != null && curr.val != val) {
            parent = curr;
            if (val < curr.val) {
                toDecrement.add(curr.leftSize);
                curr = curr.left;
                direction = Direction.LEFT;
            } else {
                toDecrement.add(curr.rightSize);
                curr = curr.right;
                direction = Direction.RIGHT;
            }
        }
        if (curr == null) {
            return false;
        } else if (parent == null) {
            root = null;
        } else {
            if (curr.left == null && curr.right == null) {
                changeParentBranch(parent, direction, null);
            } else if (curr.left == null) {
                changeParentBranch(parent, direction, curr.right);
            } else if (curr.right == null) {
                changeParentBranch(parent, direction, curr.left);
            } else {
                final Node removing = curr;
                toDecrement.add(curr.rightSize);
                parent = curr;
                direction = Direction.RIGHT;
                curr = curr.right;
                while (curr.left != null) {
                    parent = curr;
                    toDecrement.add(curr.leftSize);
                    curr = curr.left;
                    direction = Direction.LEFT;
                }
                removing.val = curr.val;
                if (curr.right == null) {
                    changeParentBranch(parent, direction, null);
                } else {
                    changeParentBranch(parent, direction, curr.right);
                }
            }
        }
        for (final AtomicInteger decrementable : toDecrement) {
            decrementable.decrementAndGet();
        }
        return true;
    }

    /**
     * Time: O(log n), O(n) in the worst case.
     * Space: O(1)
     */
    public boolean find(final int val) {
        Node curr = root;
        while (curr != null && curr.val != val) {
            if (val < curr.val) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return curr != null;
    }

    /**
     * Time: O(log n), O(n) in the worst case.
     * Space: O(1)
     */
    public int getRandomNode() {
        if (root == null) {
            throw new IllegalStateException("No nodes in the tree");
        }
        Node curr = root;
        while (true) {
            final int bound = 1 + curr.leftSize.get() + curr.rightSize.get();
            final int nextRand = random.nextInt(bound);
            if (nextRand == 0) {
                return curr.val;
            } else if (nextRand <= curr.leftSize.get()) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
    }

    private static void changeParentBranch(final Node parent, final Direction direction, final Node branch) {
        if (direction == Direction.LEFT) {
            parent.left = branch;
        } else {
            parent.right = branch;
        }
    }

    private enum Direction { LEFT, RIGHT }
    
    private static class Node {

        int val;
        Node left;
        Node right;
        AtomicInteger leftSize = new AtomicInteger(0);
        AtomicInteger rightSize = new AtomicInteger(0);

        private Node(final int val) {
            this.val = val;
        }
    }

    public static void main(final String[] args) {
        final RandomNodeBinaryTree tree = new RandomNodeBinaryTree();
        tree.insert(50);
        tree.insert(10);
        tree.insert(1);
        tree.insert(30);
        tree.insert(20);
        tree.insert(25);
        tree.insert(26);
        tree.insert(21);

        tree.delete(10);

        final Map<Integer, Integer> counts = new HashMap<>();
        for (int i = 0; i < 700000; i++) {
            counts.compute(tree.getRandomNode(), (key, value) -> value == null ? 1 : value + 1);
        }
        System.out.println(counts);
    }
}