import java.util.Arrays;
import java.util.Comparator;

/**
 * Problem #179
 * Time complexity: O(n log n)
 * Space complexity: O(n)
 **/
public class LargestNumber {

    public String largestNumber(int[] nums) {
        final String[] numsAsStrs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numsAsStrs[i] = Integer.toString(nums[i]);
        }
        Arrays.sort(numsAsStrs, new Comparator<String>(){
            @Override
            public int compare(String s1, String s2) {
                return (s2 + s1).compareTo(s1 + s2);
            }
        });
        final StringBuilder builder = new StringBuilder();
        for (final String num : numsAsStrs) {
            builder.append(num);
        }
        return builder.length() == 0 || builder.charAt(0) == '0' ? "0" : builder.toString();
    }

    public static void main(final String[] args) {
        System.out.println("210 == " + new LargestNumber().largestNumber(new int[]{10,2}));
        System.out.println("9534330 == " + new LargestNumber().largestNumber(new int[]{3,30,34,5,9}));
        System.out.println("9876543210 == " + new LargestNumber().largestNumber(new int[]{1,2,3,4,5,6,7,8,9,0}));
        System.out.println("0 == " + new LargestNumber().largestNumber(new int[]{0,0}));
        System.out.println("9609938824824769735703560743981399 == " + new LargestNumber().largestNumber(new int[]{824,938,1399,5607,6973,5703,9609,4398,8247}));
        System.out.println("8248247 == " + new LargestNumber().largestNumber(new int[]{824,8247}));
        System.out.println("12121 == " + new LargestNumber().largestNumber(new int[]{121,12}));
        System.out.println("21221 == " + new LargestNumber().largestNumber(new int[]{21,212}));
        System.out.println("21221 == " + new LargestNumber().largestNumber(new int[]{212,21}));
	}
}