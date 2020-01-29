
public class _3 {

    public static void main(String[] args) {
        long n = 600851475143L;
        int result = Integer.MIN_VALUE;;
        for (int i = 2; i <= Math.sqrt(n) && n > 1; i++) {
            if (n % i == 0) {
                result = Math.max(result, i);
                while (n % i == 0) {
                    n /= i;
                }
            }
        }
        System.out.println(result);
    }
}
