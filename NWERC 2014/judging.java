import java.util.*;

public class judging {

    public static void main(String[] args) {
	Scanner scn = new Scanner(System.in);
	int n = scn.nextInt();
	scn.nextLine();
	String[] domJudge = new String[n];
	for (int i = 0; i < n; i++) {
	    domJudge[i] = scn.nextLine();
	}
	String[] kattis = new String[n];
	for (int i = 0; i < n; i++) {
	    kattis[i] = scn.nextLine();
	}
	Arrays.sort(domJudge);
	Arrays.sort(kattis);
	int result = 0, i = 0, j = 0;
	while (i < n && j < n) {
	    if (domJudge[i].compareTo(kattis[j]) == 0) {
		result++;
		i++;
		j++;
	    } else if (domJudge[i].compareTo(kattis[j]) > 0) {
		j++;
	    } else {
		i++;
	    }
	}
	System.out.println(result);
    }
}
