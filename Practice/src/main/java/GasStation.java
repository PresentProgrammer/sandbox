/**
 * Problem #134
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class GasStation {
    
    public int canCompleteCircuit(int[] gas, int[] cost) {
		int left = 0;
		while (left < gas.length) {
		    int right = left;
		    int tank = 0;
		    do {
		        tank += gas[right] - cost[right];
                right = right + 1 == gas.length ? 0 : right + 1;
            } while (tank >= 0 && right != left);
		    if (tank >= 0) {
		        return left;
            } else if (left >= right) {
		        return -1;
            } else {
		        left = right;
            }
        }
		return -1;
    }
    
    public static void main(final String[] args) {
        System.out.println(" == " + new GasStation().canCompleteCircuit(
                new int[] { 1, 2, 3, 4, 5 }, new int[] { 3, 4, 5, 1, 2 }
        ));
        System.out.println(" == " + new GasStation().canCompleteCircuit(
                new int[] { 2, 3, 4 }, new int[] { 3, 4, 3 }
        ));
        System.out.println(" == " + new GasStation().canCompleteCircuit(
                new int[] { 3, 1, 1 }, new int[] { 1, 2, 2 }
        ));
        System.out.println(" == " + new GasStation().canCompleteCircuit(
                new int[] { 3, 3, 4 }, new int[] { 3, 4, 4 }
        ));
	}
}