/**
 * Problem #41
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class FirstMissingPositive {

    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 1 || nums.length < nums[i]) {
                nums[i] = -1;
            }
        }

		for (int i = 0; i < nums.length; i++) {
		    int j, temp = nums[i];
		    while (1 <= temp && temp <= nums.length) {
		        j = temp - 1;
		        temp = j > i ? nums[j] : 0;
		        nums[j] = 0;
            }
        }

		int i = 0;
		while (i < nums.length && nums[i] == 0) {
		    i++;
        }
		return i + 1;
    }
    
    public static void main(final String[] args) {
        System.out.println("3 == " + new FirstMissingPositive().firstMissingPositive(new int[]{1, 2, 0}));
        System.out.println("2 == " + new FirstMissingPositive().firstMissingPositive(new int[]{3, 4, -1, 1}));
        System.out.println("1 == " + new FirstMissingPositive().firstMissingPositive(new int[]{7, 8, 9, 11, 12}));
	}
}