import java.math.BigInteger;
import java.util.*;

public class digicomp2 {

    static void solve(int i, BigInteger n, int[][] swtch) {
	if (i != -1) {
	    BigInteger two = new BigInteger("2");
	    int nMod2 = n.mod(two).intValue();
	    BigInteger nLeft = n.divide(two);
	    BigInteger nRight = n.divide(two);
	    if (nMod2 == 1) {
		if (swtch[i][0] == 0) {
		    nLeft = nLeft.add(BigInteger.ONE);
		} else {
		    nRight = nRight.add(BigInteger.ONE);
		}
	    }
	    swtch[i][0] = (swtch[i][0] + nMod2) % 2;
	    solve(swtch[i][1], nLeft, swtch);
	    solve(swtch[i][2], nRight, swtch);
	}
    }

    public static void main(String[] args) {
	Scanner scn = new Scanner(System.in);
	BigInteger n = new BigInteger(scn.next());
	int m = scn.nextInt();
	int[][] swtch = new int[m][3];
	for (int i = 0; i < m; i++) {
	    char c = scn.next().charAt(0);
	    swtch[i][0] = c == 'L' ? 0 : 1;
	    swtch[i][1] = scn.nextInt() - 1;
	    swtch[i][2] = scn.nextInt() - 1;
	}
	solve(0, n, swtch);
	StringBuilder result = new StringBuilder();
	for (int i = 0; i < m; i++) {
	    result.append((swtch[i][0] == 0) ? 'L' : 'R');
	}
	System.out.println(result);
    }
}
