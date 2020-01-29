
import java.util.*;

class CfgNode {

    long[] id;
    int dist;
    int[] pre;

    CfgNode(long[] id, int dist, int[] pre) {
        this.id = id;
        this.dist = dist;
        this.pre = pre;
    }
}

public class BFS {

    static int N = 4;

    static long[] delta(long[] id, int x, int y) {
        long[] delta = new long[id.length];
        for (int i = 0; i < id.length; i++) {
            delta[i] = id[i] ^ (long) Math.pow(2, y);
        }
        delta[x] = id[x] ^ (long) (Math.pow(2, N) - 1);
        //delta[x] = delta[x] ^ (long) (Math.pow(2, N) - Math.pow(2, y) - 1);
        return delta;
    }

    static boolean zero(long[] id) {
        for (int i = 0; i < id.length; i++) {
            if (id[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TreeMap<long[], CfgNode> discovered = new TreeMap<>(new Comparator<long[]>() {
            @Override
            public int compare(long[] o1, long[] o2) {
                for (int i = 0; i < o1.length; i++) {
                    if (o1[i] < o2[i]) {
                        return -1;
                    } else if (o1[i] > o2[i]) {
                        return 1;
                    }
                }
                return 0;
            }
        });
        LinkedList<CfgNode> q = new LinkedList();
        long[] init = new long[N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                double tmp = Math.sqrt(i * i + j * j);
                if (N - 1 <= tmp && tmp < N) {
                    init[i] += Math.pow(2, j);
                }
            }
        }
        CfgNode u = new CfgNode(init, 0, null);
        discovered.put(u.id, u);
        q.add(u);
        while (!q.isEmpty()) {
            System.out.println(q.size());
            u = q.poll();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    long[] delta = delta(u.id, i, j);
                    if (!discovered.containsKey(delta)) {
                        CfgNode v = new CfgNode(delta, u.dist + 1, new int[]{i, j});
                        discovered.put(v.id, v);
                        q.add(v);
                    }
                    if (zero(delta)) {
                        q.clear();
                        i = N;
                        j = N;
                    }
                }
            }
        }
        u = discovered.get(new long[N]);
        StringBuilder strategy = new StringBuilder();
        while (u.pre != null) {
            strategy = new StringBuilder('\n').append(Arrays.toString(u.pre)).append(strategy);
            u = discovered.get(delta(u.id, u.pre[0], u.pre[1]));
        }
        System.out.println(strategy);
    }
}
