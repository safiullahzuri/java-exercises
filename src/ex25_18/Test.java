package ex25_18;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("Enter source file: ");
        String source = input.nextLine();

        File sourceFile = new File(source);

        System.out.println("Enter target file: ");
        String target = input.nextLine();

        File targetFile = new File(target);

        Scanner sourceReader = new Scanner(source);
        StringBuilder sbText = new StringBuilder();

        while (sourceReader.hasNext()){
            sbText.append(sourceReader.nextLine());
        }

        int[] counts = HuffmanCode.getFrequencyCount(sbText.toString());
        HuffmanCode.Tree tree = HuffmanCode.getHuffmanTree(counts);
        String[] codes = HuffmanCode.getCode(tree.root);

        StringBuilder sbEncoded = new StringBuilder();
        for (int i=0; i<sbText.length(); i++){
            char c = sbText.charAt(i);
            sbEncoded.append(codes[(int)c]);
        }

        try(
            BitOutputStream bitOutput = new BitOutputStream(targetFile);
            ){
            bitOutput.writeObject(codes);
            bitOutput.writeInt(sbEncoded.length());
            bitOutput.writeBit(sbEncoded.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
