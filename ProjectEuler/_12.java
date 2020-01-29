
public class _12 {

    public static void main(String[] args) {
        long result = 0;
        int divisors = 2;
        double sqrt;
        for (long i = 2; divisors <= 500; i++) {
            result = i * (i + 1) / 2;
            divisors = 2;
            sqrt = Math.sqrt(result);
            if (sqrt == (long) sqrt) {
                divisors++;
            }
            for (long j = 2; j < sqrt; j++) {
                if (result % j == 0) {
                    divisors += 2;
                }
            }
        }
        System.out.println(result);
    }
}
