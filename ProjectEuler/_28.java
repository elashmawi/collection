
public class _28 {

    public static void main(String[] args) {
        long result = 1;
        int current = 1, step = 2;
        while (step < 1001) {
            for (int i = 0; i < 4; i++) {
                current += step;
                result += current;
            }
            step += 2;
        }
        System.out.println(result);
    }
}
