package graphs.exercises.day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ex10 {

    public static void main(String[] args) {
        System.out.println("Enter a file name: ");
        Scanner input = new Scanner(System.in);

        String fileName = input.nextLine();

        System.out.println("Enter two vertices: ");

        int v1 = input.nextInt();
        int v2 = input.nextInt();

        try (Scanner fileReader = new Scanner(new File(fileName))) {
            int numberOfVertices = fileReader.nextInt();
            List<Edge> edges = new ArrayList<>();

            while (fileReader.hasNext()){
                String line = fileReader.nextLine();
                String[] numbers = line.split(" ");

                for (int i=1; i<numbers.length; i++){
                    edges.add(new Edge(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[i])));
                }
            }
            Graph<Integer> graph = new UnweightedGraph<>(edges, numberOfVertices);
            //find shortest path
            //System.out.println(graph.shortestPath(v1, v2));
            System.out.println(graph.shortestPath2(v1, v2));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
