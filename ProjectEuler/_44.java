
import java.util.*;

public class _44 {

    static boolean isPentagonal(long n) {
        double sqrt = Math.sqrt(1 + 24 * n);
        return sqrt == (long) sqrt && (1 + sqrt) % 6 == 0;
    }

    public static void main(String[] args) {
        Integer[] tmp;
        TreeSet<Integer[]> pairs = new TreeSet(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return o1[2] - o2[2] != 0 ? o1[2] - o2[2] : (o1[0] - o2[0] != 0 ? o1[0] - o2[0] : (o1[1] - o2[1]));
            }
        });
        for (long i = 1; i < 10000; i++) {
            for (long j = 1; j < i; j++) {
                if (isPentagonal((i * (3 * i - 1)) / 2 - (j * (3 * j - 1)) / 2)) {
                    pairs.add(new Integer[]{(int) ((i * (3 * i - 1)) / 2), (int) ((j * (3 * j - 1)) / 2), (int) ((i * (3 * i - 1)) / 2 - (j * (3 * j - 1)) / 2)});
                }
            }
        }
        while (!pairs.isEmpty()) {
            tmp = pairs.pollFirst();
            if (isPentagonal(tmp[0] + tmp[1])) {
                System.out.println(tmp[2]);
                break;
            }
        }
    }
}
