/**
 * Problem #1.5
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class OneAway {

    public boolean oneAway(String s1, String s2) {
        if (s1.length() > s2.length()) {
            final String temp = s1;
            s1 = s2;
            s2 = temp;
        }
        if (s2.length() - s1.length() > 1) {
            return false;
        }
        int i = 0, j = 0;
        boolean foundDifference = false;
        while (i < s1.length()) {
            final char s1Curr = s1.charAt(i);
            final char s2Curr = s2.charAt(j);
            if (s1Curr == s2Curr) {
                i++;
                j++;
            } else {
                if (foundDifference) {
                    return false;
                } else {
                    foundDifference = true;
                    if (s1.length() < s2.length()) {
                        j++;
                    } else {
                        i++;
                        j++;
                    }
                }
            }
        }
        return j == s2.length() || !foundDifference;
    }

    public static void main(final String[] args) {
        System.out.println("true == " + new OneAway().oneAway("pale", "ple"));
        System.out.println("true == " + new OneAway().oneAway("pales", "pale"));
        System.out.println("true == " + new OneAway().oneAway("pale", "bale"));
        System.out.println("false == " + new OneAway().oneAway("pale", "bake"));
        System.out.println("false == " + new OneAway().oneAway("pale", "paless"));
    }
}