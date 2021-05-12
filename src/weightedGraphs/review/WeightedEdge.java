package weightedGraphs.review;

public class WeightedEdge extends Edge{

    double weight;

    public WeightedEdge(int u, int v, double weight) {
        super(u, v);
        this.weight = weight;
    }
}
