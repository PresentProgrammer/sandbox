import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Problem #1024
 * Time complexity: O(n + m)
 * Space complexity: O(n + m)
 **/
public class FlowerPlantingWithNoAdjacent {

    public int[] gardenNoAdj(int N, int[][] paths) {
		final Map<Integer, List<Integer>> adjLists = new HashMap<>();
		for (final int[] edge : paths) {
		    adjLists.compute(edge[0] - 1, listWithElement(edge[1]));
            adjLists.compute(edge[1] - 1, listWithElement(edge[0]));
        }
		final boolean[][] takenColors = new boolean[N][4];
		final int[] result = new int[N];
		for (int i = 0; i < N; i++) {
		    int color = 0;
		    for (final boolean taken : takenColors[i]) {
		        if (taken) {
		            color++;
                } else {
		            break;
                }
            }
		    result[i] = color + 1;
		    for (final int adjVertex : adjLists.getOrDefault(i, Collections.emptyList())) {
		        takenColors[adjVertex][color] = true;
            }
        }
		return result;
    }

    private BiFunction<Integer, List<Integer>, List<Integer>> listWithElement(int vertex) {
        return (k, v) -> {
            final List<Integer> vertexes = v == null ? new ArrayList<>() : v;
            vertexes.add(vertex - 1);
            return vertexes;
        };
    }

    public static void main(final String[] args) {
        System.out.println("1,2,3 == " + Arrays.toString(new FlowerPlantingWithNoAdjacent().gardenNoAdj(3, new int[][]{{1,2}, {2,3}, {3,1}})));
        System.out.println("1,2,1,2 == " + Arrays.toString(new FlowerPlantingWithNoAdjacent().gardenNoAdj(4, new int[][]{{1,2}, {3,4}})));
        System.out.println("1,2,3,4 == " + Arrays.toString(new FlowerPlantingWithNoAdjacent().gardenNoAdj(4, new int[][]{{1,2},{2,3},{3,4},{4,1},{1,3},{2,4}})));
	}
}