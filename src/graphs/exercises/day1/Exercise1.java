package graphs.exercises.day1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exercise1 {

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        String fileName = input.nextLine();

        Scanner fileReader = new Scanner(new File(fileName));

        int numberOfVertices = fileReader.nextInt();

        List<Edge> edges = new ArrayList<>();
        while (fileReader.hasNext()){
            String line = fileReader.nextLine();
            String[] numbers = line.split(" ");
            for (int i=1; i<numbers.length; i++){
                Edge edge = new Edge(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[i]));
                edges.add(edge);
            }
        }
        UnweightedGraph<Integer> graph = new UnweightedGraph<>(edges, numberOfVertices);
        System.out.println("vertices: " + graph.getVertices());
        System.out.println("size: "+graph.getSize());

        System.out.println("Is Connected: "+graph.isConnected());
    }

}
