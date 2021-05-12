package graphs.exercises.day3;


import java.util.List;

public class TestNT {

    public static void main(String[] args) {
        NineTailModel nineTailModel = new NineTailModel();
        List<Integer> path = nineTailModel.getShortestPath(NineTailModel.getIndex("HHHTTTHTH".toCharArray()));


        System.out.println(
                nineTailModel.tree.getSearchOrder());

    }
}
