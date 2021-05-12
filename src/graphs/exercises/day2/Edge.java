package graphs.exercises.day2;

public class Edge {
    int u, v;

    public Edge(int u, int v){
        this.u = u;
        this.v = v;
    }

    @Override
    public boolean equals(Object o) {
        return ((Edge) o).u == u && ((Edge) o).v == v;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "u=" + u +
                ", v=" + v +
                '}';
    }

    @Override
    public int hashCode() {
        int result = u;
        result = 31 * result + v;
        return result;
    }
}
