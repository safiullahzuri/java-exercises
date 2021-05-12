package graphs.learning2;

import java.util.List;
import java.util.Scanner;

public class NineTail {

    public static void main(String[] args) {
        System.out.println("Enter the initial nine coins Hs and Ts");
        Scanner input = new Scanner(System.in);

        String s = input.nextLine();

        char[] initialNode = s.toCharArray();
        NineTailModel nineTailModel = new NineTailModel();
        List<Integer> path = nineTailModel.getShortestPath(NineTailModel.getIndex(initialNode));

        System.out.println("The steps to flip the coins are ");

        for (int i=0; i<path.size(); i++){
            NineTailModel.printNodes(NineTailModel.getNode(path.get(i).intValue()));
        }

    }

}
