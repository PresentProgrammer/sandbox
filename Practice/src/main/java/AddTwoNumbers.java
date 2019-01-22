public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        final ListNode resultAnchor = new ListNode(-1);
        ListNode current = resultAnchor;
        int overflow = 0;
        int digit;
        while (l1 != null && l2 != null) {
            digit = l1.val + l2.val + overflow;
            overflow = digit / 10;
            current.next = new ListNode(digit % 10);
            current = current.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        ListNode l = l1 == null ? l2 : l1;
        while (l != null) {
            digit = l.val + overflow;
            overflow = digit / 10;
            current.next = new ListNode(digit % 10);
            current = current.next;
            l = l.next;
        }
        if (overflow > 0) {
            current.next = new ListNode(overflow);
        }
        return resultAnchor.next;
    }
    
    public static void main(final String[] args) {
        final ListNode ln342 = new ListNode(2);
        ln342.next = new ListNode(4);
        ln342.next.next = new ListNode(3);
        final ListNode ln465 = new ListNode(5);
        ln465.next = new ListNode(6);
        ln465.next.next = new ListNode(4);
        System.out.println(ln342 + " + " + ln465 + " = " + new AddTwoNumbers().addTwoNumbers(ln342, ln465));
    }
}

class ListNode {
   int val;
   ListNode next;
   ListNode(int x) { 
       val = x;
   }
   
   @Override
   public String toString() {
       final StringBuilder builder = new StringBuilder();
       ListNode current = this;
       while (current != null) {
           builder.insert(0, current.val);
           current = current.next;
       }
       return builder.toString();
   }
}