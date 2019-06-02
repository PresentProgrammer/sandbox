import java.util.NoSuchElementException;

import static java.util.Arrays.fill;

/**
 * Problem #3.1
 * Time complexity: O(1) for push, O(n) on average for pop.
 * Space complexity: O(n)
 **/
public class ThreeStacksInOne {
    
    private Node[] array;
    private int[] tops;
    private int top;

    public ThreeStacksInOne(final int size) {
        this.array = new Node[size];
        this.tops = new int[3];
        fill(this.tops, -1);
        this.top = -1;
    }

    public boolean pushFirst(final int val) {
        return push(val, 0);
    }

    public boolean pushSecond(final int val) {
        return push(val, 1);
    }

    public boolean pushThird(final int val) {
        return push(val, 2);
    }

    public int popFirst() {
        return pop(0);
    }

    public int popSecond() {
        return pop(1);
    }

    public int popThird() {
        return pop(2);
    }

    private boolean push(final int val, final int stackNum) {
        if (top == array.length - 1) {
            return false;
        } else {
            final Node newNode = new Node(val, tops[stackNum]);
            array[++top] = newNode;
            tops[stackNum] = top;
            return true;
        }
    }

    private int pop(final int stackNum) {
        if (tops[stackNum] == -1) {
            throw new NoSuchElementException();
        }
        final int poppedInd = tops[stackNum];
        final Node poppedNode = array[poppedInd];
        updateNextIndexes(poppedInd);
        shiftElements(poppedInd);
        updateTops(stackNum, poppedNode.nextInd, poppedInd);
        return poppedNode.val;
    }

    private void updateNextIndexes(final int poppedInd) {
        for (int i = poppedInd + 1; i <= top; i++) {
            final Node curr = array[i];
            if (curr.nextInd > poppedInd) {
                curr.nextInd--;
            }
        }
    }

    private void shiftElements(final int poppedInd) {
        for (int i = poppedInd; i < top; i++) {
            array[i] = array[i + 1];
        }
    }

    private void updateTops(final int stackNum, final int ind, final int poppedInd) {
        tops[stackNum] = ind;
        for (int i = 0; i < tops.length; i++) {
            if (i != stackNum && tops[i] > poppedInd) {
                tops[i]--;
            }
        }
        top--;
    }

    private static class Node {

        final int val;
        int nextInd;

        public Node(final int val, final int nextInd) {
            this.val = val;
            this.nextInd = nextInd;
        }
    }
    
    public static void main(final String[] args) {
        final ThreeStacksInOne threeStack = new ThreeStacksInOne(8);
        System.out.println(threeStack.pushFirst(1));
        System.out.println(threeStack.pushSecond(2));
        System.out.println(threeStack.pushThird(3));
        System.out.println(threeStack.pushFirst(4));
        System.out.println(threeStack.pushSecond(5));
        System.out.println(threeStack.pushThird(6));
        System.out.println(threeStack.pushFirst(7));
        System.out.println(threeStack.pushSecond(8));
        System.out.println(threeStack.pushThird(9));
        System.out.println();
        System.out.println(threeStack.popFirst());
        System.out.println(threeStack.popFirst());
        System.out.println(threeStack.popFirst());
        System.out.println();
        System.out.println(threeStack.pushThird(9));
        System.out.println(threeStack.pushThird(10));
        System.out.println(threeStack.pushThird(11));
        System.out.println(threeStack.popSecond());
        System.out.println(threeStack.popSecond());
        System.out.println(threeStack.popSecond());
        System.out.println();
        System.out.println(threeStack.popThird());
        System.out.println(threeStack.popThird());
        System.out.println(threeStack.popThird());
        System.out.println(threeStack.popThird());
        System.out.println(threeStack.popThird());
        System.out.println(threeStack.popThird());
	}
}