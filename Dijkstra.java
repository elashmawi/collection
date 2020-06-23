
import java.util.*;

class Vertex {

    int id, dist = Integer.MAX_VALUE;
    ArrayDeque<Edge> list = new ArrayDeque();
    Vertex pre;

    Vertex(int id) {
        this.id = id;
    }
}

class Edge {

    Vertex post;
    int weight;

    Edge(Vertex post, int weight) {
        this.post = post;
        this.weight = weight;
    }
}

public class Dijkstra {

    static void dijkstra(Vertex s) {
        TreeSet<Vertex> q = new TreeSet(new Comparator<Vertex>() {
            @Override
            public int compare(Vertex o1, Vertex o2) {
                int tmp = o1.dist - o2.dist;
                return tmp != 0 ? tmp : o1.id - o2.id;
            }
        });
        s.dist = 0;
        q.add(s);
        while (!q.isEmpty()) {
            Vertex u = q.pollFirst();
            for (Edge e : u.list) {
                int alt = u.dist + e.weight;
                if (alt < e.post.dist) {
                    q.remove(e.post);
                    e.post.dist = alt;
                    e.post.pre = u;
                    q.add(e.post);
                }
            }
        }
    }
}
