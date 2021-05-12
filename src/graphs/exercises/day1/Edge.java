package graphs.exercises.day1;

public class Edge {

    int u, v;

    public Edge(int u, int v){
        this.u = u;
        this.v = v;
    }

    @Override
    public boolean equals(Object o) {
        return u == ((Edge) o).u && v == ((Edge) o).v;
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
        return u;
    }
}
