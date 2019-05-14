/**
 * Problem #1041
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class RobotBoundedInCircle {

    private static final int[][] DIRECTIONS = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public boolean isRobotBounded(final String instructions) {
		int x = 0;
		int y = 0;
		int direction = 0;
		for (int i = 0; i < 4; i++) {
		    for (int j = 0; j < instructions.length(); j++) {
                final char instruction = instructions.charAt(j);
                if (instruction == 'G') {
		            y += DIRECTIONS[getDirectionIndex(direction)][0];
		            x += DIRECTIONS[getDirectionIndex(direction)][1];
                } else if (instruction == 'L') {
                    direction--;
                } else if (instruction == 'R') {
                    direction++;
                }
            }
        }
		return x == 0 && y == 0;
    }

    private int getDirectionIndex(final int direction) {
        return (DIRECTIONS.length + direction % DIRECTIONS.length) % DIRECTIONS.length;
    }

    public static void main(final String[] args) {
        System.out.println("true == " + new RobotBoundedInCircle().isRobotBounded("GGLLGG"));
	}
}