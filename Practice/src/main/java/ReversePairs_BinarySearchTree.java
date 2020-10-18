import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

/**
 * Problem #493
 * Solved with custom Binary Search Tree. See the approach of creating balanced BST.
 * Time complexity: O(N log N)
 * Space complexity: O(N)
 **/
public class ReversePairs_BinarySearchTree {

    public int reversePairs(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        final Node root = toCustomBinarySearchTree(nums);
        int count = 0;
        for (final int num : nums) {
            count += root.countGreater((long) num * 2);
            root.insert(num);
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("4 == " + new ReversePairs_BinarySearchTree().reversePairs(new int[]{5,4,3,2,1}));
        System.out.println("0 == " + new ReversePairs_BinarySearchTree().reversePairs(
                IntStream.range(0, 50_000).toArray()));
        System.out.println("??? == " + new ReversePairs_BinarySearchTree().reversePairs(
                IntStream.range(0, 50_000)
                        .boxed()
                        .sorted(Comparator.reverseOrder())
                        .mapToInt(Integer::intValue)
                        .toArray()));
    }

    private static Node toCustomBinarySearchTree(int[] nums) {
        final int[] arr = Arrays.stream(nums)
                .sorted()
                .distinct()
                .toArray();
        return toBST(arr, 0, arr.length - 1);
    }

    private static Node toBST(int[] arr, int start, int endIncl) {
        if (start > endIncl) {
            return null;
        }
        final int mid = (start + endIncl) >>> 1;
        final Node root = new Node(arr[mid]);
        root.left = toBST(arr, start, mid - 1);
        root.right = toBST(arr, mid + 1, endIncl);
        return root;
    }

    private static class Node {
        private final int val;
        private int countGE;
        private Node left;
        private Node right;

        Node(int val) {
            this.val = val;
        }

        void insert(int newVal) {
            if (newVal >= this.val) {
                this.countGE++;
            }
            if (newVal > this.val) {
                this.right.insert(newVal);
            } else if (newVal < this.val) {
                this.left.insert(newVal);
            }
        }

        int countGreater(long other) {
            if (other < this.val) {
                return this.countGE + (this.left == null ? 0 : this.left.countGreater(other));
            } else {
                return this.right == null ? 0 : this.right.countGreater(other);
            }
        }
    }
}