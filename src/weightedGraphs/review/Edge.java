package weightedGraphs.review;

public class Edge {
    int u, v;

    public Edge(int u, int v){
        this.u = u;
        this.v = v;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "u=" + u +
                ", v=" + v +
                '}';
    }
}
