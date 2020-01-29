
public class _14 {

    static long collatz(long n) {
        return n % 2 == 0 ? n / 2 : 3 * n + 1;
    }

    public static void main(String[] args) {
        int result = 0, maxChain = Integer.MIN_VALUE, counter;
        long tmp;
        for (int i = 1; i < 1000000; i++) {
            tmp = i;
            counter = 1;
            while (tmp != 1) {
                tmp = collatz(tmp);
                counter++;
            }
            if (counter > maxChain) {
                maxChain = counter;
                result = i;
            }
        }
        System.out.println(result);
    }
}
