package aggregate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class DirectorySizeStream {

    public static void main(String[] args) {
        System.out.print("Enter a directory or a file: ");
        Scanner input = new Scanner(System.in);

        String directory = input.nextLine();

        System.out.println(getSize(new File(directory))+" bytes");
    }

    public static long getSize(File file){
        if (file.isFile()){
            return file.length();
        }else {
            try {
                return Files.list(file.toPath()).parallel().mapToLong(e->getSize(e.toFile())).sum();
            } catch (IOException e) {
                e.printStackTrace();
                return 0;
            }
        }
    }

}
