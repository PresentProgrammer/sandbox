import java.util.ArrayList;
import java.util.List;

/**
 * Problem #118
 * Time complexity: O(n ^ 2)
 * Space complexity: O(1)
 **/
public class PascalsTriangle {

    public List<List<Integer>> generate(int numRows) {
        final List<List<Integer>> triangle = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            final List<Integer> row = new ArrayList<>();
            row.add(1);
            if (i > 0) {
                final List<Integer> prevRow = triangle.get(i - 1);
                for (int j = 1; j < prevRow.size(); j++) {
                    row.add(prevRow.get(j - 1) + prevRow.get(j));
                }
                row.add(1);
            }
            triangle.add(row);
        }
        return triangle;
    }

    public static void main(final String[] args) {
        System.out.println(new PascalsTriangle().generate(5));
    }
}