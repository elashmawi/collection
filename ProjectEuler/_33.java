
public class _33 {

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
        int numerator = 1, denominator = 1;
        for (int a = 1; a < 10; a++) {
            for (int c = 1; c < 10; c++) {
                int b = (10 * a * c) / (a + 9 * c);
                if ((double) (10 * a * c) / (a + 9 * c) == b && b < Math.min(10, 10 * c - 9 * a)) {
                    System.out.println(a + "" + b + "/" + c + "" + a);
                    numerator *= b;
                    denominator *= c;
                }
            }
        }
        for (int a = 1; a < 10; a++) {
            for (int b = 1; b < 10; b++) {
                int d = (10 * a * b) / (9 * a + b);
                if ((double) (10 * a * b) / (9 * a + b) == d && d > Math.max(0, 10 * a - 9 * b) && d < 10) {
                    System.out.println(a + "" + b + "/" + b + "" + d);
                    numerator *= a;
                    denominator *= d;
                }
            }
        }
        System.out.println(denominator / gcd(numerator, denominator));
    }
}
