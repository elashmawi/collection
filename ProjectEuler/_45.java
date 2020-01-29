
public class _45 {

    public static void main(String[] args) {
        long tmp;
        double sqrt1, sqrt2;
        for (long i = 286; i < 100000; i++) {
            tmp = i * i + i;
            sqrt1 = Math.sqrt(1 + 12 * tmp);
            sqrt2 = Math.sqrt(4 + 16 * tmp);
            if (sqrt1 == (int) sqrt1 && sqrt2 == (int) sqrt2 && (1 + sqrt1) % 6 == 0 && (2 + sqrt2) % 8 == 0) {
                System.out.println(tmp / 2);
                break;
            }
        }
    }
}
