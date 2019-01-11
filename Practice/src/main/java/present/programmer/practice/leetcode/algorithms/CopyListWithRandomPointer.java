import java.util.Map;
import java.util.HashMap;

/**
 * Time: O(n): one pass over n elements, but each has 2 calls on HashMap (which are O(1)).
 * Space: O(n): for result and for HashMap.
 **/
public class CopyListWithRandomPointer {
    
    private final Map<RandomListNode, RandomListNode> originalToCopy = new HashMap<>();
    
    public RandomListNode copyRandomList(RandomListNode head) {
        RandomListNode originalCurrent = head;
        RandomListNode copyCurrent = getKnownOrCreateNewNode(originalCurrent);
        final RandomListNode copyHead = copyCurrent;
        while (originalCurrent != null) {
            copyCurrent.random = getKnownOrCreateNewNode(originalCurrent.random);
            copyCurrent.next = getKnownOrCreateNewNode(originalCurrent.next);
            originalCurrent = originalCurrent.next;
            copyCurrent = copyCurrent.next;
        }
        return copyHead;
    }
    
    private RandomListNode getKnownOrCreateNewNode(final RandomListNode originalNode) {
        return originalNode == null ? null : originalToCopy.computeIfAbsent(originalNode, k -> new RandomListNode(k.label));
    }
    
    public static void main(final String... args) {
        final RandomListNode head = new RandomListNode(1);
        head.next = new RandomListNode(2);
        final RandomListNode third = new RandomListNode(3);
        head.random = third;
        head.next.next = third;
        RandomListNode copy = new CopyListWithRandomPointer().copyRandomList(head);
        while (copy != null) {
            System.out.println(copy.label);
            copy = copy.next;
        }
        new CopyListWithRandomPointer().copyRandomList(null);
    }
}

class RandomListNode {
    
    int label;
    RandomListNode next, random;
    
    RandomListNode(int x) {
        this.label = x;
    }
};