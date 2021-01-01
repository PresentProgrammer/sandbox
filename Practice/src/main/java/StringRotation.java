/**
 * Problem #1.9
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class StringRotation {

    public boolean isRotation(final String s1, final String s2) {
        return s1.length() == s2.length() && isSubstring(s2 + s2, s1);
    }

    private static boolean isSubstring(final String longer, final String shorter) {
        return longer.contains(shorter);
    }

    public static void main(final String[] args) {
        System.out.println("true == " + new StringRotation().isRotation("waterbottle", "erbottlewat"));
    }
}