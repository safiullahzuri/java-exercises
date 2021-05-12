package weightedGraphs.review;

public class VertexCost implements Comparable<VertexCost>{

    public int vertex;
    public double cost;

    public int getVertex() {
        return vertex;
    }

    public void setVertex(int vertex) {
        this.vertex = vertex;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public VertexCost(int vertex, double cost) {
        this.vertex = vertex;
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VertexCost that = (VertexCost) o;

        if (vertex != that.vertex) return false;
        return Double.compare(that.cost, cost) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = vertex;
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public int compareTo(VertexCost o) {
        if (this.cost > o.cost){
            return 1;
        }else if (this.cost == o.cost) return 0;
        else return -1;
    }
}
