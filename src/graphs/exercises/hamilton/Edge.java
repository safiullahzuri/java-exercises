package graphs.exercises.hamilton;

public class Edge {
    public int u; // Starting vertex of the edge
    public int v; // Ending vertex of the edge

    /** Construct an edge for (u, v) */
    public Edge(int u, int v) {
        this.u = u;
        this.v = v;
    }
}