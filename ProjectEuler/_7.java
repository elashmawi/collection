
public class _7 {

    public static void main(String[] args) {
        boolean[] sieve = new boolean[(int) Math.pow(2, 17)];
        for (int i = 2; i < Math.sqrt(sieve.length + 1); i++) {
            if (!sieve[i]) {
                for (int j = i * i; j < sieve.length; j += i) {
                    sieve[j] = true;
                }
            }
        }
        int i = 2, counter = 0;
        while (i < sieve.length && counter < 10001) {
            if (!sieve[i]) {
                counter++;
            }
            i++;
        }
        System.out.println(i - 1);
    }
}
