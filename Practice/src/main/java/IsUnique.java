/**
 * Problem #1.1
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class IsUnique {

    public boolean allUnique(final String s) {
        if (s == null) {
            return false;
        }
        final boolean[] seen = new boolean[(int) Math.pow(2, 16)];
        for (int i = 0; i < s.length(); i++) {
            if (seen[s.charAt(i)]) {
                return false;
            } else {
                seen[s.charAt(i)] = true;
            }
        }
        return true;
    }

    public static void main(final String[] args) {
        System.out.println("true == " + new IsUnique().allUnique("abcd"));
        System.out.println("false == " + new IsUnique().allUnique("abcdefa"));
        System.out.println("true == " + new IsUnique().allUnique(""));
    }
}