package Ex22;

import java.io.*;

public class Ex22_12 {

    public static void main(String[] args) {

        File file = new File("file.dat");

        try(RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")){
            long length = file.length();
            randomAccessFile.seek(length - (8*100));
            while (true){
                System.out.println(randomAccessFile.readLong());
            }
        }catch (EOFException | FileNotFoundException e){
            System.out.println("end of file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
