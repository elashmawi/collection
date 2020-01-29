
import java.util.*;

public class _96 {

    static int[][] grid;
    static TreeSet<Integer>[] availableRow = new TreeSet[9];
    static TreeSet<Integer>[] availableColumn = new TreeSet[9];
    static TreeSet<Integer>[][] availableBox = new TreeSet[3][3];
    static TreeSet<Integer[]> blanks = new TreeSet(new Comparator<Integer[]>() {
        @Override
        public int compare(Integer[] o1, Integer[] o2) {
            return o1[0] - o2[0] == 0 ? o1[1] - o2[1] : o1[0] - o2[0];
        }
    });

    static int sudoku() {
        if (blanks.isEmpty()) {
            return 100 * grid[0][0] + 10 * grid[0][1] + grid[0][2];
        }
        int result;
        Integer[] currentBlank = blanks.pollFirst();
        ArrayDeque<Integer> tmp = new ArrayDeque(availableBox[currentBlank[0] / 3][currentBlank[1] / 3].descendingSet());
        for (int i : tmp) {
            if (availableRow[currentBlank[0]].contains(i) && availableColumn[currentBlank[1]].contains(i)) {
                grid[currentBlank[0]][currentBlank[1]] = i;
                availableRow[currentBlank[0]].remove(i);
                availableColumn[currentBlank[1]].remove(i);
                availableBox[currentBlank[0] / 3][currentBlank[1] / 3].remove(i);
                result = sudoku();
                if (result > 0) {
                    return result;
                }
                availableBox[currentBlank[0] / 3][currentBlank[1] / 3].add(i);
                availableColumn[currentBlank[1]].add(i);
                availableRow[currentBlank[0]].add(i);
                grid[currentBlank[0]][currentBlank[1]] = 0;
            }
        }
        blanks.add(currentBlank);
        return 0;
    }

    public static void main(String[] args) {
        int[][][] input = {{{0, 0, 3, 0, 2, 0, 6, 0, 0}, {9, 0, 0, 3, 0, 5, 0, 0, 1}, {0, 0, 1, 8, 0, 6, 4, 0, 0}, {0, 0, 8, 1, 0, 2, 9, 0, 0}, {7, 0, 0, 0, 0, 0, 0, 0, 8}, {0, 0, 6, 7, 0, 8, 2, 0, 0}, {0, 0, 2, 6, 0, 9, 5, 0, 0}, {8, 0, 0, 2, 0, 3, 0, 0, 9}, {0, 0, 5, 0, 1, 0, 3, 0, 0}}, {{2, 0, 0, 0, 8, 0, 3, 0, 0}, {0, 6, 0, 0, 7, 0, 0, 8, 4}, {0, 3, 0, 5, 0, 0, 2, 0, 9}, {0, 0, 0, 1, 0, 5, 4, 0, 8}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {4, 0, 2, 7, 0, 6, 0, 0, 0}, {3, 0, 1, 0, 0, 7, 0, 4, 0}, {7, 2, 0, 0, 4, 0, 0, 6, 0}, {0, 0, 4, 0, 1, 0, 0, 0, 3}}, {{0, 0, 0, 0, 0, 0, 9, 0, 7}, {0, 0, 0, 4, 2, 0, 1, 8, 0}, {0, 0, 0, 7, 0, 5, 0, 2, 6}, {1, 0, 0, 9, 0, 4, 0, 0, 0}, {0, 5, 0, 0, 0, 0, 0, 4, 0}, {0, 0, 0, 5, 0, 7, 0, 0, 9}, {9, 2, 0, 1, 0, 8, 0, 0, 0}, {0, 3, 4, 0, 5, 9, 0, 0, 0}, {5, 0, 7, 0, 0, 0, 0, 0, 0}}, {{0, 3, 0, 0, 5, 0, 0, 4, 0}, {0, 0, 8, 0, 1, 0, 5, 0, 0}, {4, 6, 0, 0, 0, 0, 0, 1, 2}, {0, 7, 0, 5, 0, 2, 0, 8, 0}, {0, 0, 0, 6, 0, 3, 0, 0, 0}, {0, 4, 0, 1, 0, 9, 0, 3, 0}, {2, 5, 0, 0, 0, 0, 0, 9, 8}, {0, 0, 1, 0, 2, 0, 6, 0, 0}, {0, 8, 0, 0, 6, 0, 0, 2, 0}}, {{0, 2, 0, 8, 1, 0, 7, 4, 0}, {7, 0, 0, 0, 0, 3, 1, 0, 0}, {0, 9, 0, 0, 0, 2, 8, 0, 5}, {0, 0, 9, 0, 4, 0, 0, 8, 7}, {4, 0, 0, 2, 0, 8, 0, 0, 3}, {1, 6, 0, 0, 3, 0, 2, 0, 0}, {3, 0, 2, 7, 0, 0, 0, 6, 0}, {0, 0, 5, 6, 0, 0, 0, 0, 8}, {0, 7, 6, 0, 5, 1, 0, 9, 0}}, {{1, 0, 0, 9, 2, 0, 0, 0, 0}, {5, 2, 4, 0, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 7, 0}, {0, 5, 0, 0, 0, 8, 1, 0, 2}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {4, 0, 2, 7, 0, 0, 0, 9, 0}, {0, 6, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 3, 0, 9, 4, 5}, {0, 0, 0, 0, 7, 1, 0, 0, 6}}, {{0, 4, 3, 0, 8, 0, 2, 5, 0}, {6, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 1, 0, 9, 4}, {9, 0, 0, 0, 0, 4, 0, 7, 0}, {0, 0, 0, 6, 0, 8, 0, 0, 0}, {0, 1, 0, 2, 0, 0, 0, 0, 3}, {8, 2, 0, 5, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 5}, {0, 3, 4, 0, 9, 0, 7, 1, 0}}, {{4, 8, 0, 0, 0, 6, 9, 0, 2}, {0, 0, 2, 0, 0, 8, 0, 0, 1}, {9, 0, 0, 3, 7, 0, 0, 6, 0}, {8, 4, 0, 0, 1, 0, 2, 0, 0}, {0, 0, 3, 7, 0, 4, 1, 0, 0}, {0, 0, 1, 0, 6, 0, 0, 4, 9}, {0, 2, 0, 0, 8, 5, 0, 0, 7}, {7, 0, 0, 9, 0, 0, 6, 0, 0}, {6, 0, 9, 2, 0, 0, 0, 1, 8}}, {{0, 0, 0, 9, 0, 0, 0, 0, 2}, {0, 5, 0, 1, 2, 3, 4, 0, 0}, {0, 3, 0, 0, 0, 0, 1, 6, 0}, {9, 0, 8, 0, 0, 0, 0, 0, 0}, {0, 7, 0, 0, 0, 0, 0, 9, 0}, {0, 0, 0, 0, 0, 0, 2, 0, 5}, {0, 9, 1, 0, 0, 0, 0, 5, 0}, {0, 0, 7, 4, 3, 9, 0, 2, 0}, {4, 0, 0, 0, 0, 7, 0, 0, 0}}, {{0, 0, 1, 9, 0, 0, 0, 0, 3}, {9, 0, 0, 7, 0, 0, 1, 6, 0}, {0, 3, 0, 0, 0, 5, 0, 0, 7}, {0, 5, 0, 0, 0, 0, 0, 0, 9}, {0, 0, 4, 3, 0, 2, 6, 0, 0}, {2, 0, 0, 0, 0, 0, 0, 7, 0}, {6, 0, 0, 1, 0, 0, 0, 3, 0}, {0, 4, 2, 0, 0, 7, 0, 0, 6}, {5, 0, 0, 0, 0, 6, 8, 0, 0}}, {{0, 0, 0, 1, 2, 5, 4, 0, 0}, {0, 0, 8, 4, 0, 0, 0, 0, 0}, {4, 2, 0, 8, 0, 0, 0, 0, 0}, {0, 3, 0, 0, 0, 0, 0, 9, 5}, {0, 6, 0, 9, 0, 2, 0, 1, 0}, {5, 1, 0, 0, 0, 0, 0, 6, 0}, {0, 0, 0, 0, 0, 3, 0, 4, 9}, {0, 0, 0, 0, 0, 7, 2, 0, 0}, {0, 0, 1, 2, 9, 8, 0, 0, 0}}, {{0, 6, 2, 3, 4, 0, 7, 5, 0}, {1, 0, 0, 0, 0, 5, 6, 0, 0}, {5, 7, 0, 0, 0, 0, 0, 4, 0}, {0, 0, 0, 0, 9, 4, 8, 0, 0}, {4, 0, 0, 0, 0, 0, 0, 0, 6}, {0, 0, 5, 8, 3, 0, 0, 0, 0}, {0, 3, 0, 0, 0, 0, 0, 9, 1}, {0, 0, 6, 4, 0, 0, 0, 0, 7}, {0, 5, 9, 0, 8, 3, 2, 6, 0}}, {{3, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 5, 0, 0, 9, 0, 0, 0}, {2, 0, 0, 5, 0, 4, 0, 0, 0}, {0, 2, 0, 0, 0, 0, 7, 0, 0}, {1, 6, 0, 0, 0, 0, 0, 5, 8}, {7, 0, 4, 3, 1, 0, 6, 0, 0}, {0, 0, 0, 8, 9, 0, 1, 0, 0}, {0, 0, 0, 0, 6, 7, 0, 8, 0}, {0, 0, 0, 0, 0, 5, 4, 3, 7}}, {{6, 3, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 5, 0, 0, 0, 0, 8}, {0, 0, 5, 6, 7, 4, 0, 0, 0}, {0, 0, 0, 0, 2, 0, 0, 0, 0}, {0, 0, 3, 4, 0, 1, 0, 2, 0}, {0, 0, 0, 0, 0, 0, 3, 4, 5}, {0, 0, 0, 0, 0, 7, 0, 0, 4}, {0, 8, 0, 3, 0, 0, 9, 0, 2}, {9, 4, 7, 1, 0, 0, 0, 8, 0}}, {{0, 0, 0, 0, 2, 0, 0, 4, 0}, {0, 0, 8, 0, 3, 5, 0, 0, 0}, {0, 0, 0, 0, 7, 0, 6, 0, 2}, {0, 3, 1, 0, 4, 6, 9, 7, 0}, {2, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 5, 0, 1, 2, 0, 3}, {0, 4, 9, 0, 0, 0, 7, 3, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 0}, {8, 0, 0, 0, 0, 4, 0, 0, 0}}, {{3, 6, 1, 0, 2, 5, 9, 0, 0}, {0, 8, 0, 9, 6, 0, 0, 1, 0}, {4, 0, 0, 0, 0, 0, 0, 5, 7}, {0, 0, 8, 0, 0, 0, 4, 7, 1}, {0, 0, 0, 6, 0, 3, 0, 0, 0}, {2, 5, 9, 0, 0, 0, 8, 0, 0}, {7, 4, 0, 0, 0, 0, 0, 0, 5}, {0, 2, 0, 0, 1, 8, 0, 6, 0}, {0, 0, 5, 4, 7, 0, 3, 2, 9}}, {{0, 5, 0, 8, 0, 7, 0, 2, 0}, {6, 0, 0, 0, 1, 0, 0, 9, 0}, {7, 0, 2, 5, 4, 0, 0, 0, 6}, {0, 7, 0, 0, 2, 0, 3, 0, 1}, {5, 0, 4, 0, 0, 0, 9, 0, 8}, {1, 0, 3, 0, 8, 0, 0, 7, 0}, {9, 0, 0, 0, 7, 6, 2, 0, 5}, {0, 6, 0, 0, 9, 0, 0, 0, 3}, {0, 8, 0, 1, 0, 3, 0, 4, 0}}, {{0, 8, 0, 0, 0, 5, 0, 0, 0}, {0, 0, 0, 0, 0, 3, 4, 5, 7}, {0, 0, 0, 0, 7, 0, 8, 0, 9}, {0, 6, 0, 4, 0, 0, 9, 0, 3}, {0, 0, 7, 0, 1, 0, 5, 0, 0}, {4, 0, 8, 0, 0, 7, 0, 2, 0}, {9, 0, 1, 0, 2, 0, 0, 0, 0}, {8, 4, 2, 3, 0, 0, 0, 0, 0}, {0, 0, 0, 1, 0, 0, 0, 8, 0}}, {{0, 0, 3, 5, 0, 2, 9, 0, 0}, {0, 0, 0, 0, 4, 0, 0, 0, 0}, {1, 0, 6, 0, 0, 0, 3, 0, 5}, {9, 0, 0, 2, 5, 1, 0, 0, 8}, {0, 7, 0, 4, 0, 8, 0, 3, 0}, {8, 0, 0, 7, 6, 3, 0, 0, 1}, {3, 0, 8, 0, 0, 0, 1, 0, 4}, {0, 0, 0, 0, 2, 0, 0, 0, 0}, {0, 0, 5, 1, 0, 4, 8, 0, 0}}, {{0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 9, 8, 0, 5, 1, 0, 0}, {0, 5, 1, 9, 0, 7, 4, 2, 0}, {2, 9, 0, 4, 0, 1, 0, 6, 5}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {1, 4, 0, 5, 0, 8, 0, 9, 3}, {0, 2, 6, 7, 0, 9, 5, 8, 0}, {0, 0, 5, 1, 0, 3, 6, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}}, {{0, 2, 0, 0, 3, 0, 0, 9, 0}, {0, 0, 0, 9, 0, 7, 0, 0, 0}, {9, 0, 0, 2, 0, 8, 0, 0, 5}, {0, 0, 4, 8, 0, 6, 5, 0, 0}, {6, 0, 7, 0, 0, 0, 2, 0, 8}, {0, 0, 3, 1, 0, 2, 9, 0, 0}, {8, 0, 0, 6, 0, 5, 0, 0, 7}, {0, 0, 0, 3, 0, 9, 0, 0, 0}, {0, 3, 0, 0, 2, 0, 0, 5, 0}}, {{0, 0, 5, 0, 0, 0, 0, 0, 6}, {0, 7, 0, 0, 0, 9, 0, 2, 0}, {0, 0, 0, 5, 0, 0, 1, 0, 7}, {8, 0, 4, 1, 5, 0, 0, 0, 0}, {0, 0, 0, 8, 0, 3, 0, 0, 0}, {0, 0, 0, 0, 9, 2, 8, 0, 5}, {9, 0, 7, 0, 0, 6, 0, 0, 0}, {0, 3, 0, 4, 0, 0, 0, 1, 0}, {2, 0, 0, 0, 0, 0, 6, 0, 0}}, {{0, 4, 0, 0, 0, 0, 0, 5, 0}, {0, 0, 1, 9, 4, 3, 6, 0, 0}, {0, 0, 9, 0, 0, 0, 3, 0, 0}, {6, 0, 0, 0, 5, 0, 0, 0, 2}, {1, 0, 3, 0, 0, 0, 5, 0, 6}, {8, 0, 0, 0, 2, 0, 0, 0, 7}, {0, 0, 5, 0, 0, 0, 2, 0, 0}, {0, 0, 2, 4, 3, 6, 7, 0, 0}, {0, 3, 0, 0, 0, 0, 0, 4, 0}}, {{0, 0, 4, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 3, 0, 0, 0, 2}, {3, 9, 0, 7, 0, 0, 0, 8, 0}, {4, 0, 0, 0, 0, 9, 0, 0, 1}, {2, 0, 9, 8, 0, 1, 3, 0, 7}, {6, 0, 0, 2, 0, 0, 0, 0, 8}, {0, 1, 0, 0, 0, 8, 0, 5, 3}, {9, 0, 0, 0, 4, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 8, 0, 0}}, {{3, 6, 0, 0, 2, 0, 0, 8, 9}, {0, 0, 0, 3, 6, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {8, 0, 3, 0, 0, 0, 6, 0, 2}, {4, 0, 0, 6, 0, 3, 0, 0, 7}, {6, 0, 7, 0, 0, 0, 1, 0, 8}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 4, 1, 8, 0, 0, 0}, {9, 7, 0, 0, 3, 0, 0, 1, 4}}, {{5, 0, 0, 4, 0, 0, 0, 6, 0}, {0, 0, 9, 0, 0, 0, 8, 0, 0}, {6, 4, 0, 0, 2, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 1, 0, 0, 8}, {2, 0, 8, 0, 0, 0, 5, 0, 1}, {7, 0, 0, 5, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 9, 0, 0, 8, 4}, {0, 0, 3, 0, 0, 0, 6, 0, 0}, {0, 6, 0, 0, 0, 3, 0, 0, 2}}, {{0, 0, 7, 2, 5, 6, 4, 0, 0}, {4, 0, 0, 0, 0, 0, 0, 0, 5}, {0, 1, 0, 0, 3, 0, 0, 6, 0}, {0, 0, 0, 5, 0, 8, 0, 0, 0}, {0, 0, 8, 0, 6, 0, 2, 0, 0}, {0, 0, 0, 1, 0, 7, 0, 0, 0}, {0, 3, 0, 0, 7, 0, 0, 9, 0}, {2, 0, 0, 0, 0, 0, 0, 0, 4}, {0, 0, 6, 3, 1, 2, 7, 0, 0}}, {{0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 7, 9, 0, 5, 0, 1, 8, 0}, {8, 0, 0, 0, 0, 0, 0, 0, 7}, {0, 0, 7, 3, 0, 6, 8, 0, 0}, {4, 5, 0, 7, 0, 8, 0, 9, 6}, {0, 0, 3, 5, 0, 2, 7, 0, 0}, {7, 0, 0, 0, 0, 0, 0, 0, 5}, {0, 1, 6, 0, 3, 0, 4, 2, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}}, {{0, 3, 0, 0, 0, 0, 0, 8, 0}, {0, 0, 9, 0, 0, 0, 5, 0, 0}, {0, 0, 7, 5, 0, 9, 2, 0, 0}, {7, 0, 0, 1, 0, 5, 0, 0, 8}, {0, 2, 0, 0, 9, 0, 0, 3, 0}, {9, 0, 0, 4, 0, 2, 0, 0, 1}, {0, 0, 4, 2, 0, 7, 1, 0, 0}, {0, 0, 2, 0, 0, 0, 8, 0, 0}, {0, 7, 0, 0, 0, 0, 0, 9, 0}}, {{2, 0, 0, 1, 7, 0, 6, 0, 3}, {0, 5, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 6, 0, 7, 9}, {0, 0, 0, 0, 4, 0, 7, 0, 0}, {0, 0, 0, 8, 0, 1, 0, 0, 0}, {0, 0, 9, 0, 5, 0, 0, 0, 0}, {3, 1, 0, 4, 0, 0, 0, 0, 0}, {0, 0, 5, 0, 0, 0, 0, 6, 0}, {9, 0, 6, 0, 3, 7, 0, 0, 2}}, {{0, 0, 0, 0, 0, 0, 0, 8, 0}, {8, 0, 0, 7, 0, 1, 0, 4, 0}, {0, 4, 0, 0, 2, 0, 0, 3, 0}, {3, 7, 4, 0, 0, 0, 9, 0, 0}, {0, 0, 0, 0, 3, 0, 0, 0, 0}, {0, 0, 5, 0, 0, 0, 3, 2, 1}, {0, 1, 0, 0, 6, 0, 0, 5, 0}, {0, 5, 0, 8, 0, 2, 0, 0, 6}, {0, 8, 0, 0, 0, 0, 0, 0, 0}}, {{0, 0, 0, 0, 0, 0, 0, 8, 5}, {0, 0, 0, 2, 1, 0, 0, 0, 9}, {9, 6, 0, 0, 8, 0, 1, 0, 0}, {5, 0, 0, 8, 0, 0, 0, 1, 6}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {8, 9, 0, 0, 0, 6, 0, 0, 7}, {0, 0, 9, 0, 7, 0, 0, 5, 2}, {3, 0, 0, 0, 5, 4, 0, 0, 0}, {4, 8, 0, 0, 0, 0, 0, 0, 0}}, {{6, 0, 8, 0, 7, 0, 5, 0, 2}, {0, 5, 0, 6, 0, 8, 0, 7, 0}, {0, 0, 2, 0, 0, 0, 3, 0, 0}, {5, 0, 0, 0, 9, 0, 0, 0, 6}, {0, 4, 0, 3, 0, 2, 0, 5, 0}, {8, 0, 0, 0, 5, 0, 0, 0, 3}, {0, 0, 5, 0, 0, 0, 2, 0, 0}, {0, 1, 0, 7, 0, 4, 0, 9, 0}, {4, 0, 9, 0, 6, 0, 7, 0, 1}}, {{0, 5, 0, 0, 1, 0, 0, 4, 0}, {1, 0, 7, 0, 0, 0, 6, 0, 2}, {0, 0, 0, 9, 0, 5, 0, 0, 0}, {2, 0, 8, 0, 3, 0, 5, 0, 1}, {0, 4, 0, 0, 7, 0, 0, 2, 0}, {9, 0, 1, 0, 8, 0, 4, 0, 6}, {0, 0, 0, 4, 0, 1, 0, 0, 0}, {3, 0, 4, 0, 0, 0, 7, 0, 9}, {0, 2, 0, 0, 6, 0, 0, 1, 0}}, {{0, 5, 3, 0, 0, 0, 7, 9, 0}, {0, 0, 9, 7, 5, 3, 4, 0, 0}, {1, 0, 0, 0, 0, 0, 0, 0, 2}, {0, 9, 0, 0, 8, 0, 0, 1, 0}, {0, 0, 0, 9, 0, 7, 0, 0, 0}, {0, 8, 0, 0, 3, 0, 0, 7, 0}, {5, 0, 0, 0, 0, 0, 0, 0, 3}, {0, 0, 7, 6, 4, 1, 2, 0, 0}, {0, 6, 1, 0, 0, 0, 9, 4, 0}}, {{0, 0, 6, 0, 8, 0, 3, 0, 0}, {0, 4, 9, 0, 7, 0, 2, 5, 0}, {0, 0, 0, 4, 0, 5, 0, 0, 0}, {6, 0, 0, 3, 1, 7, 0, 0, 4}, {0, 0, 7, 0, 0, 0, 8, 0, 0}, {1, 0, 0, 8, 2, 6, 0, 0, 9}, {0, 0, 0, 7, 0, 2, 0, 0, 0}, {0, 7, 5, 0, 4, 0, 1, 9, 0}, {0, 0, 3, 0, 9, 0, 6, 0, 0}}, {{0, 0, 5, 0, 8, 0, 7, 0, 0}, {7, 0, 0, 2, 0, 4, 0, 0, 5}, {3, 2, 0, 0, 0, 0, 0, 8, 4}, {0, 6, 0, 1, 0, 5, 0, 4, 0}, {0, 0, 8, 0, 0, 0, 5, 0, 0}, {0, 7, 0, 8, 0, 3, 0, 1, 0}, {4, 5, 0, 0, 0, 0, 0, 9, 1}, {6, 0, 0, 5, 0, 8, 0, 0, 7}, {0, 0, 3, 0, 1, 0, 6, 0, 0}}, {{0, 0, 0, 9, 0, 0, 8, 0, 0}, {1, 2, 8, 0, 0, 6, 4, 0, 0}, {0, 7, 0, 8, 0, 0, 0, 6, 0}, {8, 0, 0, 4, 3, 0, 0, 0, 7}, {5, 0, 0, 0, 0, 0, 0, 0, 9}, {6, 0, 0, 0, 7, 9, 0, 0, 8}, {0, 9, 0, 0, 0, 4, 0, 1, 0}, {0, 0, 3, 6, 0, 0, 2, 8, 4}, {0, 0, 1, 0, 0, 7, 0, 0, 0}}, {{0, 0, 0, 0, 8, 0, 0, 0, 0}, {2, 7, 0, 0, 0, 0, 0, 5, 4}, {0, 9, 5, 0, 0, 0, 8, 1, 0}, {0, 0, 9, 8, 0, 6, 4, 0, 0}, {0, 2, 0, 4, 0, 3, 0, 6, 0}, {0, 0, 6, 9, 0, 5, 1, 0, 0}, {0, 1, 7, 0, 0, 0, 6, 2, 0}, {4, 6, 0, 0, 0, 0, 0, 3, 8}, {0, 0, 0, 0, 9, 0, 0, 0, 0}}, {{0, 0, 0, 6, 0, 2, 0, 0, 0}, {4, 0, 0, 0, 5, 0, 0, 0, 1}, {0, 8, 5, 0, 1, 0, 6, 2, 0}, {0, 3, 8, 2, 0, 6, 7, 1, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 9, 4, 0, 7, 3, 5, 0}, {0, 2, 6, 0, 4, 0, 5, 3, 0}, {9, 0, 0, 0, 2, 0, 0, 0, 7}, {0, 0, 0, 8, 0, 9, 0, 0, 0}}, {{0, 0, 0, 9, 0, 0, 0, 0, 2}, {0, 5, 0, 1, 2, 3, 4, 0, 0}, {0, 3, 0, 0, 0, 0, 1, 6, 0}, {9, 0, 8, 0, 0, 0, 0, 0, 0}, {0, 7, 0, 0, 0, 0, 0, 9, 0}, {0, 0, 0, 0, 0, 0, 2, 0, 5}, {0, 9, 1, 0, 0, 0, 0, 5, 0}, {0, 0, 7, 4, 3, 9, 0, 2, 0}, {4, 0, 0, 0, 0, 7, 0, 0, 0}}, {{3, 8, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 4, 0, 0, 7, 8, 5}, {0, 0, 9, 0, 2, 0, 3, 0, 0}, {0, 6, 0, 0, 9, 0, 0, 0, 0}, {8, 0, 0, 3, 0, 2, 0, 0, 9}, {0, 0, 0, 0, 4, 0, 0, 7, 0}, {0, 0, 1, 0, 7, 0, 5, 0, 0}, {4, 9, 5, 0, 0, 6, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 9, 2}}, {{0, 0, 0, 1, 5, 8, 0, 0, 0}, {0, 0, 2, 0, 6, 0, 8, 0, 0}, {0, 3, 0, 0, 0, 0, 0, 4, 0}, {0, 2, 7, 0, 3, 0, 5, 1, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 4, 6, 0, 8, 0, 7, 9, 0}, {0, 5, 0, 0, 0, 0, 0, 8, 0}, {0, 0, 4, 0, 7, 0, 1, 0, 0}, {0, 0, 0, 3, 2, 5, 0, 0, 0}}, {{0, 1, 0, 5, 0, 0, 2, 0, 0}, {9, 0, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 2, 0, 0, 8, 0, 3, 0}, {5, 0, 0, 0, 3, 0, 0, 0, 7}, {0, 0, 8, 0, 0, 0, 5, 0, 0}, {6, 0, 0, 0, 8, 0, 0, 0, 4}, {0, 4, 0, 1, 0, 0, 7, 0, 0}, {0, 0, 0, 7, 0, 0, 0, 0, 6}, {0, 0, 3, 0, 0, 4, 0, 5, 0}}, {{0, 8, 0, 0, 0, 0, 0, 4, 0}, {0, 0, 0, 4, 6, 9, 0, 0, 0}, {4, 0, 0, 0, 0, 0, 0, 0, 7}, {0, 0, 5, 9, 0, 4, 6, 0, 0}, {0, 7, 0, 6, 0, 8, 0, 3, 0}, {0, 0, 8, 5, 0, 2, 1, 0, 0}, {9, 0, 0, 0, 0, 0, 0, 0, 5}, {0, 0, 0, 7, 8, 1, 0, 0, 0}, {0, 6, 0, 0, 0, 0, 0, 1, 0}}, {{9, 0, 4, 2, 0, 0, 0, 0, 7}, {0, 1, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 7, 0, 6, 5, 0, 0}, {0, 0, 0, 8, 0, 0, 0, 9, 0}, {0, 2, 0, 9, 0, 4, 0, 6, 0}, {0, 4, 0, 0, 0, 2, 0, 0, 0}, {0, 0, 1, 6, 0, 7, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 3, 0}, {3, 0, 0, 0, 0, 5, 7, 0, 2}}, {{0, 0, 0, 7, 0, 0, 8, 0, 0}, {0, 0, 6, 0, 0, 0, 0, 3, 1}, {0, 4, 0, 0, 0, 2, 0, 0, 0}, {0, 2, 4, 0, 7, 0, 0, 0, 0}, {0, 1, 0, 0, 3, 0, 0, 8, 0}, {0, 0, 0, 0, 6, 0, 2, 9, 0}, {0, 0, 0, 8, 0, 0, 0, 7, 0}, {8, 6, 0, 0, 0, 0, 5, 0, 0}, {0, 0, 2, 0, 0, 6, 0, 0, 0}}, {{0, 0, 1, 0, 0, 7, 0, 9, 0}, {5, 9, 0, 0, 8, 0, 0, 0, 1}, {0, 3, 0, 0, 0, 0, 0, 8, 0}, {0, 0, 0, 0, 0, 5, 8, 0, 0}, {0, 5, 0, 0, 6, 0, 0, 2, 0}, {0, 0, 4, 1, 0, 0, 0, 0, 0}, {0, 8, 0, 0, 0, 0, 0, 3, 0}, {1, 0, 0, 0, 2, 0, 0, 7, 9}, {0, 2, 0, 7, 0, 0, 4, 0, 0}}, {{0, 0, 0, 0, 0, 3, 0, 1, 7}, {0, 1, 5, 0, 0, 9, 0, 0, 8}, {0, 6, 0, 0, 0, 0, 0, 0, 0}, {1, 0, 0, 0, 0, 7, 0, 0, 0}, {0, 0, 9, 0, 0, 0, 2, 0, 0}, {0, 0, 0, 5, 0, 0, 0, 0, 4}, {0, 0, 0, 0, 0, 0, 0, 2, 0}, {5, 0, 0, 6, 0, 0, 3, 4, 0}, {3, 4, 0, 2, 0, 0, 0, 0, 0}}, {{3, 0, 0, 2, 0, 0, 0, 0, 0}, {0, 0, 0, 1, 0, 7, 0, 0, 0}, {7, 0, 6, 0, 3, 0, 5, 0, 0}, {0, 7, 0, 0, 0, 9, 0, 8, 0}, {9, 0, 0, 0, 2, 0, 0, 0, 4}, {0, 1, 0, 8, 0, 0, 0, 5, 0}, {0, 0, 9, 0, 4, 0, 3, 0, 1}, {0, 0, 0, 7, 0, 2, 0, 0, 0}, {0, 0, 0, 0, 0, 8, 0, 0, 6}}};
        int result = 0;
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < availableRow.length; j++) {
                availableRow[j] = new TreeSet();
                for (int k = 1; k < 10; k++) {
                    availableRow[j].add(k);
                }
            }
            for (int j = 0; j < availableColumn.length; j++) {
                availableColumn[j] = new TreeSet();
                for (int k = 1; k < 10; k++) {
                    availableColumn[j].add(k);
                }
            }
            for (int j = 0; j < availableBox.length; j++) {
                for (int k = 0; k < availableBox[j].length; k++) {
                    availableBox[j][k] = new TreeSet();
                    for (int l = 1; l < 10; l++) {
                        availableBox[j][k].add(l);
                    }
                }
            }
            grid = input[i];
            for (int j = 0; j < grid.length; j++) {
                for (int k = 0; k < grid[j].length; k++) {
                    if (grid[j][k] == 0) {
                        blanks.add(new Integer[]{j, k});
                    } else {
                        availableRow[j].remove(grid[j][k]);
                        availableColumn[k].remove(grid[j][k]);
                        availableBox[j / 3][k / 3].remove(grid[j][k]);
                    }
                }
            }
            result += sudoku();
        }
        System.out.println(result);
    }
}
