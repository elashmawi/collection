
public class _100 {

    public static void main(String[] args) {
        long result = 85, n = 120, tmp;
        while (n <= 1000000000000L) {
            tmp = result;
            result = 3 * result + 2 * n - 2;
            n = 4 * tmp + 3 * n - 3;
        }
        System.out.println(result);
    }
}
