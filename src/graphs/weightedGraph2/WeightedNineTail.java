package graphs.weightedGraph2;

import java.util.List;
import java.util.Scanner;

public class WeightedNineTail {

    public static void main(String[] args) {
        System.out.println("Enter an initial nine coins' Hs and Ts: ");
        Scanner input = new Scanner(System.in);

        String s = input.nextLine();
        char[] initialNode = s.toCharArray();

        WeightedNineTailModel model = new WeightedNineTailModel();
        List<Integer> path = model.getShortestPath(NineTailModel.getIndex(initialNode));

        System.out.println("The steps to flip the coins are: ");
        for (int i = 0; i < path.size(); i++) {
            NineTailModel.printNode(NineTailModel.getNode(path.get(i)));
        }
    }
}
