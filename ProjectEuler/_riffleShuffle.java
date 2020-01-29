
import java.util.Arrays;

public class _riffleShuffle {

    public static void main(String[] args) {
        //for (int n = 2; n <= 256; n+=2) {
            int n = 6;
            int[] deck = new int[n], tmp = new int[n];
            for (int i = 0; i < n; i++) {
                deck[i] = i;
                tmp[i] = i;
            }
            int[] tmp2 = new int[n];
            int counter = 0;
            while (!Arrays.equals(deck, tmp2)) {
                System.out.println(Arrays.toString(tmp));
                for (int i = 0; i < n; i += 2) {
                    tmp2[i] = tmp[i / 2];
                }
                for (int i = 1; i < n; i += 2) {
                    tmp2[i] = tmp[(n + i - 1) / 2];
                }
                counter++;
                tmp = Arrays.copyOf(tmp2, n);
            }
            //System.out.println(counter);
            /*if (counter == 8) {
                System.out.println(n);
            }
        }*/
    }
}
