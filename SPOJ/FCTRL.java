
import java.util.*;

class FCTRL {

    public static void main(String[] args) {
	Scanner scn = new Scanner(System.in);
	int t = scn.nextInt();
	for (int i = 0; i < t; i++) {
	    int n = scn.nextInt();
	    int x = 0;
	    int f = 5;
	    while (f <= n) {
		x += n / f;
		f *= 5;
	    }
	    System.out.println(x);
	}
    }
}
