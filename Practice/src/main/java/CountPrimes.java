/**
 * Problem #204
 * Time complexity: O(n log(log n))
 * Space complexity: O(n)
 **/
public class CountPrimes {

    public int countPrimes(int n) {
		final boolean[] marks= new boolean[n];
		int primeCount = 0;
		int i;
		for (i = 2; i * i < n; i++) {
		    if (!marks[i]) {
		        primeCount++;
		        for (int j = i * i; j < n; j += i) {
		            marks[j] = true;
                }
            }
        }
		while (i < n) {
		    primeCount += marks[i++] ? 0 : 1;
        }
		return primeCount;
    }
    
    public static void main(final String[] args) {
	}
}