
import java.util.Arrays;

public class _52 {

    public static void main(String[] args) {
        int result = 0;
        boolean done = false;
        char[] tmp1, tmp2;
        while (!done) {
            result++;
            tmp1 = Integer.toString(result).toCharArray();
            Arrays.sort(tmp1);
            done = true;
            for (int i = 2; i <= 6; i++) {
                tmp2 = Integer.toString(i * result).toCharArray();
                Arrays.sort(tmp2);
                if (!Arrays.equals(tmp1, tmp2)) {
                    done = false;
                    break;
                }
            }
        }
        System.out.println(result);
    }
}
