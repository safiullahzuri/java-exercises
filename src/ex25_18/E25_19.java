package ex25_18;

import java.io.*;
import java.util.Scanner;

public class E25_19 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter source file: ");
        String source = input.nextLine();

        File sourceFile = new File(source);

        System.out.println("Enter target file: ");
        String target = input.nextLine();

        File targetFile = new File(target);

        String[] codes = new String[0];
        int length = -1;

        StringBuilder sb = new StringBuilder();

        try(ObjectInputStream objInput = new ObjectInputStream(new BufferedInputStream(new FileInputStream(sourceFile)))) {
            codes = (String[]) objInput.readObject();

            length = objInput.readInt();

            while (true){
                sb.append(getBits(objInput.readByte()));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        sb.delete(length, sb.length());

        StringBuilder sbText = new StringBuilder();
        StringBuilder sbCharCode = new StringBuilder();
        for (int i = 0; i < sb.length(); i++) {
            sbCharCode.append(sb.charAt(i) + "");
            for (int j = 0; j < codes.length; j++) {
                if (sbCharCode.toString().equals(codes[j])) {
                    sbText.append((char)j);
                    sbCharCode = new StringBuilder();
                }
            }
        }

        try (
                PrintWriter pwOutput = new PrintWriter(targetFile);
        ) {
            pwOutput.write(sbText.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static String getBits(int value) {
        value = value % 256;
        String binaryInteger = "";
        int i = 0;

        int temp = value >> i;
        for (int j=0; j<8; j++){
            binaryInteger = (temp & 1) + binaryInteger;
            i++;
            temp = value >> i;
        }
        return binaryInteger;
    }
}
