package graphs.weightedGraph2;

import java.util.List;
import java.util.Scanner;

public class NineTail16 {


    public static void main(String[] args) {
        System.out.println("Enter the initial nine coins and Hs and Ts");
        Scanner input = new Scanner(System.in);

        String s = input.nextLine();
        char[] initialNode = s.toCharArray();

        NineTailModel16 model = new NineTailModel16();
        List<Integer> path = model.getShortestPath(NineTailModel16.getIndex(initialNode));

        System.out.println("The steps to flip the coins are: ");
        for (int i=0; i<path.size(); i++){
            NineTailModel16.printNode(NineTailModel16.getNode(path.get(i)));
        }
    }
}
