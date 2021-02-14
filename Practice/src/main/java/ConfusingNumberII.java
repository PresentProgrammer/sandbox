import java.util.ArrayList;
import java.util.List;

/**
 * Problem #1088
 * Time complexity: O(log10(N) ^ 5 * log10(N))
 * Space complexity: O(log10(N))
 **/
public class ConfusingNumberII {

    private static final int[] VALID = {0, 1, 6, 8, 9};
    private static final int[] ROTATIONS = {0, 1, -1, -1, -1, -1, 9, -1, 8, 6};

    public int confusingNumberII(int N) {
        final Counter counter = new Counter();
        generate(N, countDigits(N), new ArrayList<>(), counter);
        return counter.count;
    }

    private static boolean generate(int N, int digitsRem, List<Integer> temp, Counter counter) {
        if (digitsRem == 0) {
            final List<Integer> candidate = withoutLeadingZeroes(temp);
            if (isConfusing(candidate)) {
                final int candidateAsInt = toInt(candidate);
                if (candidateAsInt > N) {
                    return false;
                } else {
                    counter.increment();
                }
            }
        } else {
            for (final int valid : VALID) {
                temp.add(valid);
                final boolean shouldContinue = generate(N, digitsRem - 1, temp, counter);
                temp.remove(temp.size() - 1);
                if (!shouldContinue) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isConfusing(List<Integer> c) {
        int left = 0;
        int right = c.size() - 1;
        while (left < right) {
            if (ROTATIONS[c.get(left)] == c.get(right)) {
                left++;
                right--;
            } else {
                return true;
            }
        }
        return left == right && (c.get(left) == 6 || c.get(left) == 9);
    }

    private static List<Integer> withoutLeadingZeroes(List<Integer> temp) {
        int i = 0;
        while (i < temp.size() - 1 && temp.get(i) == 0) {
            i++;
        }
        return i == 0 ? temp : temp.subList(i, temp.size());
    }

    private static int toInt(List<Integer> c) {
        int result = 0;
        for (final int el : c) {
            result *= 10;
            result += el;
        }
        return result;
    }

    private static int countDigits(int N) {
        int count = 0;
        while (N > 0) {
            count++;
            N /= 10;
        }
        return count;
    }

    private static class Counter {

        int count;

        void increment() {
            count++;
        }
    }

    public static void main(final String[] args) {
        System.out.println("1950627 == " + new ConfusingNumberII().confusingNumberII(1000000000));
    }
}