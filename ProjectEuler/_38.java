
import java.util.*;

public class _38 {

    public static void main(String[] args) {
        int result = 918273645;
        String product;
        char[] tmp;
        for (int i = 10; i < 49383; i++) {
            product = "";
            for (int j = 1; product.length() < 9; j++) {
                product += j * i;
            }
            if (product.length() == 9) {
                tmp = product.toCharArray();
                Arrays.sort(tmp);
                if ("123456789".equals(new String(tmp))) {
                    result = Math.max(result, Integer.parseInt(product));
                }
            }
        }
        System.out.println(result);
    }
}
