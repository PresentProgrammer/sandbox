/**
 * Problem #493
 * Time complexity: O()
 * Space complexity: O()
 **/
public class ReversePairs {

    public int reversePairs(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        final Node root = new Node(nums[0]);
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            count += root.countGreater(((long) nums[i]) * 2);
            root.insert(nums[i]);
        }
        return count;
    }

    private static class Node {
        private final int val;
        private int countGE = 1;
        private Node left = null;
        private Node right = null;

        Node(int val) {
            this.val = val;
        }

        void insert(int newVal) {
            if (newVal >= this.val) {
                this.countGE++;
            }
            if (newVal > this.val) {
                if (this.right == null) {
                    this.right = new Node(newVal);
                } else {
                    this.right.insert(newVal);
                }
            } else if (newVal < this.val) {
                if (this.left == null) {
                    this.left = new Node(newVal);
                } else {
                    this.left.insert(newVal);
                }
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