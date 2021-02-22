import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.function.IntPredicate;
import java.util.stream.Stream;

/**
 * Problem #420
 * Time complexity: O(N log(N))
 * Space complexity: O(N)
 **/
public class StrongPasswordChecker {

    private int length;
    private int missing;
    private PriorityQueue<Integer> pq;

    public int strongPasswordChecker(String password) {
        init(password);
        int result = 0;
        while (length < 6 || length > 20 || missing > 0 || !pq.isEmpty()) {
            if (length < 6) {
                insert();
            } else if (length > 20) {
                delete();
            } else {
                change();
            }
            result++;
        }
        return result;
    }

    private void delete() {
        decrementHeadInPq(1);
        length--;
    }

    private void insert() {
        decrementHeadInPq(2);
        if (missing > 0) {
            missing--;
        }
        length++;
    }

    private void change() {
        decrementHeadInPq(3);
        if (missing > 0) {
            missing--;
        }
    }

    private void decrementHeadInPq(int subtrahend) {
        if (!pq.isEmpty()) {
            final int polled = pq.poll();
            final int decremented = polled - subtrahend;
            if (decremented >= 3) {
                pq.offer(decremented);
            }
        }
    }

    private void init(String password) {
        this.length = password.length();
        this.missing = initMissing(password);
        this.pq = initPq(password);
    }

    private static int initMissing(String password) {
        return Stream.<IntPredicate>of(Character::isLowerCase, Character::isUpperCase, Character::isDigit)
                .mapToInt(predicate -> password.chars().anyMatch(predicate) ? 0 : 1)
                .sum();
    }

    private static PriorityQueue<Integer> initPq(String password) {
        final PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(i -> i % 3));
        int left = 0;
        while (left < password.length()) {
            int right = left + 1;
            while (right < password.length() && password.charAt(left) == password.charAt(right)) {
                right++;
            }
            if (right - left >= 3) {
                pq.offer(right - left);
            }
            left = right;
        }
        return pq;
    }

    public static void main(String[] args) {
        System.out.println("5 == " + new StrongPasswordChecker().strongPasswordChecker("a"));
        System.out.println("3 == " + new StrongPasswordChecker().strongPasswordChecker("aA1"));
        System.out.println("0 == " + new StrongPasswordChecker().strongPasswordChecker("1337C0d3"));
    }
}