
import java.util.*;

public class _662 {

    public static void main(String[] args) {
        int x = 10, y = 10, modulus = 1000000007, l = Math.max(x, y);
        long[][] solution = new long[l + 1][l + 1];
        solution[0][0] = 1;
        TreeSet<Integer> fibonacci = new TreeSet(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        int f1 = 1, f2 = 1;
        while (f2 <= Math.round(l*Math.sqrt(2))) {
            fibonacci.add(f2);
            int tmp = f2;
            f2 += f1;
            f1 = tmp;
        }
        ArrayDeque<Integer[]> tmp = new ArrayDeque();
        for (int i = 0; i <= l; i++) {
            for (int j = 0; j <= l; j++) {
                int candidate = (int) Math.round(Math.sqrt(i * i + j * j));
                if (fibonacci.contains(candidate) && candidate * candidate == i * i + j * j) {
                    tmp.add(new Integer[]{i, j});
                }
            }
        }
        int[][] fibPoints = new int[tmp.size()][2];
        int c = 0;
        for (Integer[] i : tmp) {
            fibPoints[c][0] = i[0];
            fibPoints[c][1] = i[1];
            c++;
        }
        for (int i = 0; i <= l; i++) {
            for (int j = i; j <= l; j++) {
                for (int[] k : fibPoints) {
                    //check for double counting when k[0] - k[1] != 0 not needed, as this point never has an integer length 
                    if (k[0] <= i && k[1] <= j) {
                        solution[i][j] = (solution[i][j] + solution[i - k[0]][j - k[1]]) % modulus;
                    }
                }
                if (j <= l && i <= l) {
                    solution[j][i] = solution[i][j];
                }
            }
        }
        System.out.println(solution[x][y]);
    }
}
