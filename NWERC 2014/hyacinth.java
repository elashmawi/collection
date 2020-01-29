import java.util.*;

public class hyacinth {

    public static void main(String[] args) {
	Scanner scn = new Scanner(System.in);
	int n = scn.nextInt();
	LinkedList<Integer>[] adjacency = new LinkedList[n];
	for (int i = 0; i < n; i++) {
	    adjacency[i] = new LinkedList<Integer>();
	}
	for (int i = 0; i < n - 1; i++) {
	    int a = scn.nextInt() - 1;
	    int b = scn.nextInt() - 1;
	    adjacency[a].add(b);
	    adjacency[b].add(a);
	}
	if (n != 2) {
	    int freq = 2;
	    int[][] frequency = new int[n][2];
	    LinkedList<Integer> q = new LinkedList<Integer>();
	    boolean[] visited = new boolean[n];
	    visited[0] = true;
	    q.add(0);
	    frequency[0][1] = 1;
	    while (!q.isEmpty()) {
		int t = q.poll();
		frequency[t][0] = freq;
		for (int u : adjacency[t]) {
		    if (!visited[u]) {
			visited[u] = true;
			q.add(u);
			frequency[u][1] = freq;
		    }
		}
		if (t == 0 && adjacency[0].size() > 1) {
		    frequency[adjacency[0].peek()][1] = frequency[0][1];
		}
		freq++;
	    }
	    for (int i = 0; i < n; i++) {
		System.out.println(frequency[i][0] + " " + frequency[i][1]);
	    }
	} else {
	    System.out.println(1 + " " + 2);
	    System.out.println(2 + " " + 1);
	}
    }
}
