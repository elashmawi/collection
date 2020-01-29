
public class _206 {

    public static void main(String[] args) {
        long i = 1010101010;
        boolean done = false;
        while (!done) {
            String s = Long.toString(i * i);
            done = true;
            for (int j = 0; j < 9 && done; j++) {
                done &= s.charAt(2 * j) == j + 49;
            }
            i += 10;
        }
        System.out.println(i - 10);
    }
}
