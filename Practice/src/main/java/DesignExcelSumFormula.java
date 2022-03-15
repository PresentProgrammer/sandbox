import java.util.Arrays;

/**
 * Problem #631
 * Time complexity: O()
 * Space complexity: O()
 **/
public class DesignExcelSumFormula {

    private final Cell[][] mat;

    public DesignExcelSumFormula(int height, char width) {
        final int w = toIndex(width) + 1;
        final Cell[][] matrix = new Cell[height][toIndex(width) + 1];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < w; j++) {
                matrix[i][j] = new Cell();
            }
        }
        this.mat = matrix;
    }

    public void set(int row, char column, int val) {
        mat[toIndex(row)][toIndex(column)].setVal(val);
        refresh();
    }

    public int get(int row, char column) {
        return mat[toIndex(row)][toIndex(column)].getVal();
    }

    public int sum(int row, char column, String[] numbers) {
        mat[toIndex(row)][toIndex(column)].setNumbers(numbers);
        refresh();
        return get(row, column);
    }

    @Override
    public String toString() {
        return Arrays.deepToString(mat);
    }

    private void refresh() {
        final boolean[][] visited = new boolean[mat.length][mat[0].length];
        for (int row = 0; row < mat.length; row++) {
            for (int col = 0; col < mat[row].length; col++) {
                refresh(row, col, visited);
            }
        }
    }

    private int refresh(int row, int col, boolean[][] visited) {
        final Cell curr = mat[row][col];
        if (!visited[row][col]) {
            if (curr.isFunction()) {
                int sum = 0;
                for (final int[] number : curr.getNumbers()) {
                    if (number.length == 2) {
                        sum += refresh(number[0], number[1], visited);
                    } else {
                        for (int i = number[0]; i <= number[1]; i++) {
                            for (int j = number[2]; j <= number[3]; j++) {
                                sum += refresh(i, j, visited);
                            }
                        }
                    }
                }
                curr.setSum(sum);
            }
        }
        visited[row][col] = true;
        return curr.getVal();
    }

    private static int toIndex(int row) {
        return row - 1;
    }

    private static int toIndex(char col) {
        return col - 'A';
    }

    private static class Cell {

        private int val;
        private int[][] numbers;

        int getVal() {
            return this.val;
        }

        void setVal(int val) {
            this.val = val;
            this.numbers = null;
        }

        void setSum(int sum) {
            this.val = sum;
        }

        int[][] getNumbers() {
            return numbers;
        }

        void setNumbers(String[] numbers) {
            this.val = 0;
            this.numbers = Arrays.stream(numbers)
                    .map(Cell::parseNumber)
                    .toArray(int[][]::new);
        }

        boolean isFunction() {
            return numbers != null;
        }

        private static int[] parseNumber(String number) {
            final String[] numbers = number.split(":");
            if (numbers.length == 1) {
                return new int[]{ getRowIndex(numbers[0]), getColIndex(numbers[0]) };
            } else {
                return new int[]{
                        getRowIndex(numbers[0]), getRowIndex(numbers[1]),
                        getColIndex(numbers[0]), getColIndex(numbers[1])
                };
            }
        }

        private static int getRowIndex(String number) {
            return toIndex(Integer.parseInt(number.substring(1)));
        }

        private static int getColIndex(String number) {
            return toIndex(number.charAt(0));
        }

        @Override
        public String toString() {
            return Integer.toString(val);
        }
    }

    public static void main(final String[] args) {
        final DesignExcelSumFormula excel = new DesignExcelSumFormula(3, 'C');
        excel.set(1, 'A', 2);
        excel.sum(3, 'C', new String[] { "A1", "A1:B2" });
        excel.set(2, 'B', 2);
        System.out.println(excel.get(3, 'C'));
        System.out.println(excel);
    }
}