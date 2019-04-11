/**
 * Problem #28
 * Time complexity: O(n * k), n — haystack.length, k — needle.length.
 * Space complexity: O(1)
 **/
public class ImplementStrStr {

    public int strStr(String haystack, String needle) {
		int left = 0;
		while (haystack.length() - left >= needle.length()) {
		    int i = 0;
		    while (i < needle.length() && haystack.charAt(left + i) == needle.charAt(i)) {
		        i++;
            }
		    if (i == needle.length()) {
		        return left;
            } else {
		        left++;
            }
        }
		return -1;
    }
    
    public static void main(final String[] args) {
        System.out.println("2 == " + new ImplementStrStr().strStr("hello", "ll"));
        System.out.println("-1 == " + new ImplementStrStr().strStr("aaaaa", "bba"));
        System.out.println("0 == " + new ImplementStrStr().strStr("aaaaa", ""));
	}
}