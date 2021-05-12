package graphs.exercises.day3;

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
    public int hashCode() {
        int result = u;
        result = 31 * result + v;
        return result;
    }
}
