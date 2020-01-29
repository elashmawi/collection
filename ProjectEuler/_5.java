
public class _5 {

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
        long result = 1;
        for (int i = 1; i <= 20; i++) {
            result = (result * i) / gcd(result, i);
        }
        System.out.println(result);
    }
}
