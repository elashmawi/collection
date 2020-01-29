
public class _2 {

    public static void main(String[] args) {
        int result = 0, fib1 = 1, fib2 = 2, tmp;
        while (fib2 < 4000000) {
            if (fib2 % 2 == 0) {
                result += fib2;
            }
            tmp = fib2;
            fib2 += fib1;
            fib1 = tmp;
        }
        System.out.println(result);
    }
}
