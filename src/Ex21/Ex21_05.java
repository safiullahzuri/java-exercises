package Ex21;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Ex21_05 {

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);

        String javaFile = input.nextLine();
        String htmlFile = input.nextLine();

        File file = new File(javaFile);

        File file2 = new File(htmlFile);
        if (!file2.exists()){
            file2.createNewFile();
        }

        Scanner fileReader = new Scanner(file);
        PrintWriter printWriter = new PrintWriter(file2);
        while (fileReader.hasNextLine()){
            String line = fileReader.nextLine();
            printWriter.println("<div>");
            printWriter.println(line);
            printWriter.println("\n");
            printWriter.println("</div>");
            printWriter.flush();
        }
        fileReader.close();
        printWriter.close();
    }

}
