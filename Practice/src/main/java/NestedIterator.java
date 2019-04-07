import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Problem #341
 * Time complexity: O(n), where n — number of elements in nested integer list (also counting list objects as elements)
 * Space complexity: O(k), where k — maximal depth
 **/
public class NestedIterator implements Iterator<Integer> {

    private final Deque<ListIterator<NestedInteger>> stack;

    public NestedIterator(final List<NestedInteger> nestedList) {
        this.stack = new ArrayDeque<>();
        if (nestedList != null) {
            stack.add(nestedList.listIterator());
        }
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return stack.peek().next().getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            cleanUpStack();
            if (!stack.isEmpty()) {
                final ListIterator<NestedInteger> peekIter = stack.peek();
                final NestedInteger nestedInt = peekIter.next();
                if (nestedInt.isInteger()) {
                    peekIter.previous();
                    return true;
                } else {
                    stack.push(nestedInt.getList().listIterator());
                }
            }
        }
        return false;
    }

    private void cleanUpStack() {
        while (!stack.isEmpty() && !stack.peek().hasNext()) {
            stack.pop();
        }
    }

    /**
     * Given by specification of the task.
     */
    private interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        List<NestedInteger> getList();
    }
}