
public class _63 {

    public static void main(String[] args) {
        int result = 0;
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j <= ((int) (j * Math.log10(i))) + 1; j++) {
                result++;
            }
        }
        System.out.println(result);
    }
}
