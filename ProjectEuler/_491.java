
public class _491 {

    static long f(int i, int[] partition, int bal, int sum) {
        long result = 0;
        if (i < 10) {
            for (int j = -1; j <= 1; j++) {
                partition[i] = j;
                result += f(i + 1, partition, bal + j, sum + 2 * i * j);
            }
        } else if (sum % 11 == 0 && bal == 0) {
            int duplicates = partition[0];
            for (int j = 1; j < partition.length; j++) {
                if (partition[j] > 0) {
                    duplicates++;
                }
            }
            result = partition[0] < 0 ? 1620 : partition[0] == 0 ? 90 : 1;
            int free = partition[0] + 9;
            for (; duplicates > 0; duplicates--, free -= 2) {
                result *= Math.pow((free * (free - 1)) / 2, 2);
            }
            for (; free > 1; free--) {
                result *= free * free;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(f(0, new int[10], 0, 0));
    }
}
