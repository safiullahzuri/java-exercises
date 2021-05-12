package graphs.learning2;

public class WeightedEdge extends Edge implements Comparable<WeightedEdge>{

    public double weight;

    public WeightedEdge(int u, int v, double weight) {
        super(u, v);
        this.weight = weight;
    }


    @Override
    public int compareTo(WeightedEdge edge) {
        if (weight > edge.weight){
            return 1;
        }else if (edge.weight > this.weight){
            return -1;
        }
        return 0;
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
