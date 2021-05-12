package graphs.weightedGraph2;

public class CompareModels {

    public static void main(String[] args) {
        NineTailModel nineTailModel = new NineTailModel();
        WeightedNineTailModel weightedNineTailModel=  new WeightedNineTailModel();

        //System.out.println(nineTailModel.tree.getSearchOrder());
        //System.out.println("weighted nine tail model");

        //System.out.println(weightedNineTailModel.tree.getSearchOrder());
        System.out.println("first parent: ");
        System.out.println(nineTailModel.tree.getParent(80));
        System.out.println("Second parent: ");
        System.out.println(weightedNineTailModel.tree.getParent(80));
    }
}
