
public class _71 {

    static long gcd(long a, long b) {
        while (a != b) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return a;
    }

    public static void main(String[] args) {
        long[] max = {2, 5};
        long n;
        for (long d = 9; d <= 1000000; d++) {
            n = (long) Math.ceil((double) 3 * d / 7) - 1;
            if (max[0] * d < n * max[1]) {
                max[0] = n;
                max[1] = d;
            }
        }
        System.out.println(max[0] / gcd(max[0], max[1]));
    }
}
