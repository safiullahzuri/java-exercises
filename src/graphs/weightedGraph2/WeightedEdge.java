package graphs.weightedGraph2;

import graphs.learning2.Edge;

public class WeightedEdge extends Edge implements Comparable<WeightedEdge> {

    public double weight;

    public WeightedEdge(int u, int v, double weight) {
        super(u, v);
        this.weight = weight;
    }



    @Override
    public int compareTo(WeightedEdge weightedEdge) {
        if (this.weight > weightedEdge.weight){
            return 1;
        }else if (this.weight == weightedEdge.weight){
            return 0;
        }
        return -1;
    }

    @Override
    public String toString() {
        return "WeightedEdge{" +
                "weight=" + weight +
                ", u=" + u +
                ", v=" + v +
                '}';
    }
}
