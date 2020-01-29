
public class _18_67 {

    public static void main(String[] args) {
        int[][] triangle = {
            {3, 0, 0, 0},
            {7, 4, 0, 0},
            {2, 4, 6, 0},
            {8, 5, 9, 3}
        };
        Vertex[] g = new Vertex[(triangle.length * (triangle.length + 1)) / 2];
        Vertex s = new Vertex(0);
        for (int i = 0; i < g.length; i++) {
            g[i] = new Vertex(i + 1);
        }
        s.list.add(new Edge(g[0], -triangle[0][0]));
        for (int i = 0; i < triangle.length - 1; i++) {
            for (int j = 0; j <= i; j++) {
                int tmp1 = (i * (i + 1)) / 2 + j;
                g[tmp1].list.add(new Edge(g[tmp1 + i + 1], -triangle[i + 1][j]));
                g[tmp1].list.add(new Edge(g[tmp1 + i + 2], -triangle[i + 1][j + 1]));
            }
        }
        Dijkstra.dijkstra(s);
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < triangle.length; i++) {
            result = Math.min(result, g[g.length - i - 1].dist);
        }
        System.out.println(-result);
    }
}
