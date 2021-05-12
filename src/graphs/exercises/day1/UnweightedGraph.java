package graphs.exercises.day1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class UnweightedGraph<V> extends AbstractGraph<V> {

    public UnweightedGraph(){}

    public UnweightedGraph(V[] vertices, int[][] edges){
        super(vertices, edges);
    }

    public UnweightedGraph(List<V> vertices, List<Edge> edges){
        super(vertices, edges);
    }

    public UnweightedGraph(List<Edge> edges, int numberOfVertices){
        super(edges, numberOfVertices);
    }

    public UnweightedGraph(int[][] edges, int numberOfVertices){
        super(edges, numberOfVertices);
    }

    public boolean isConnected(){
        return bfs(0).getNumberOfFoundVertices() == getSize();
    }

    public boolean createFile(String destination) throws IOException {
        if (getSize() == 0){
            return false;
        }
        File file = new File(destination);

        String text = getSize()+"\n";

        for (int i=0; i<getSize(); i++){
            text += i +" ";
            for (Edge u : neighbors.get(i)){
                text += u.v +" ";
            }
            text+= "\n";
        }
        if (!file.exists()){
            file.createNewFile();
        }else {
            return false;
        }

        try {
            FileWriter fileWriter = new FileWriter(file);

            fileWriter.write(text);
            fileWriter.flush();
            fileWriter.close();

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
