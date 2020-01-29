
public class _72 {

    public static void main(String[] args) {
        long m = 12000, result = 0;
        for (int d = 2; d <= m; d++) {
            for (int n = (int) Math.floor(d / 3d) + 1; n < d / 2d; n++) {
                if (NumberTheory.gcd(n, d) == 1) {
                    result++;
                }
            }
        }
        System.out.println(result);
    }
}
