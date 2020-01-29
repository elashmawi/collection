
public class _10 {

    public static void main(String[] args) {
        boolean[] sieve = new boolean[2000001];
        for (int i = 2; i < Math.sqrt(sieve.length + 1); i++) {
            if (!sieve[i]) {
                for (int j = i * i; j < sieve.length; j += i) {
                    sieve[j] = true;
                }
            }
        }
        long result = 0;
        for (int i = 2; i < sieve.length; i++) {
            if (!sieve[i]) {
                result += i;
            }
        }
        System.out.println(result);
    }
}
