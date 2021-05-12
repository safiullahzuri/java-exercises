package aggregate.exercises;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Ex10 {

    public static void main(String[] args) throws IOException {
        String filename = "D://ex1.txt";

//        Files.lines(new File(filename).toPath()).parallel().map(line ->
//                Stream.of(line.split("[\\s+\\p{Punct}]"))).reduce((e1, e2) -> Stream.concat(e1, e2)).get()
//                .filter(e -> e.length() > 0).collect(
//                Collectors.groupingBy(String::toLowerCase, TreeMap::new,
//                        Collectors.counting()))
//                .forEach((k, v) -> System.out.println(k + " " + v));

        Files.lines(new File(filename).toPath()).parallel().map(line -> Stream.of(line.split("[\\s+\\p{Punct}]"))).reduce((e1, e2) -> Stream.concat(e1, e2)).get()
                .filter(e ->e.length()>0).collect(Collectors.groupingBy(String::toLowerCase, TreeMap::new, Collectors.counting()))
                .forEach((k, v)-> System.out.println(k+" "+v));

        int[] numbers = {2,2,5,3,23,4,32,423,4,23};
        IntSummaryStatistics summaryStatistics =  IntStream.of(numbers).summaryStatistics();
        System.out.println("average: "+summaryStatistics.getAverage());
        System.out.println("max: "+summaryStatistics.getMax());

    }
    private static long findVowelLetters(String s){
        return Stream.of(toCharacterArray(s.toCharArray())).filter(ch -> isVowel(ch)).count();
    }

    private static boolean isVowel(char a){
        char[] vowels = {'A', 'E', 'I', 'O', 'U'};
        return Stream.of(toCharacterArray(vowels)).anyMatch(e -> e==Character.toUpperCase(a));
    }


    public static int sumDigits(int number){
        return Stream.of(toCharacterArray((number+"").toCharArray())).mapToInt(ch -> ch-'0').reduce(0, (cs, nn)-> cs+nn);
    }

    public static long specifiedCharacter(String s, char c){
        return Stream.of(toCharacterArray(s.toCharArray())).filter(ch -> ch ==c).count();
    }

    public static int getDecimalNumber(String binary){
        //return Stream.of(toCharacterArray(binary.toCharArray())).mapToInt(ch -> (int)(ch - '0')).reduce(0, (e1, e2) -> 2*e1 + e2);
        //return Stream.of(toCharacterArray(binary.toCharArray())).mapToInt(ch -> (int)(ch - '0')).reduce(0, (collectedSum, newNumber) -> 2*collectedSum+newNumber);
        return Stream.of(toCharacterArray(binary.toCharArray())).mapToInt(ch -> (int)(ch - '0')).reduce(0, (collectedSum, newNumber) -> 2*collectedSum+newNumber);
    }

    public static int getDecNum(String hex){
        return Stream.of(toCharacterArray(hex.toCharArray())).mapToInt(ch -> getHexNum(ch)).reduce(0, (collectedSum, newNumber) -> collectedSum*16 + newNumber);
    }

    public static long countLetters(String s){
        return Stream.of(toCharacterArray(s.toCharArray())).filter(ch -> Character.isLetter(ch)).count();
    }


    public static int getHexNum(char a){
        if (Character.isDigit(a)){
            return Integer.parseInt(a+"");
        }else{
            switch (a){
                case 'A':
                    return 10;
                case 'B':
                    return 11;
                case 'C':
                    return 12;
                case 'D':
                    return 13;
                case 'E':
                    return 14;
                case 'F':
                    return 15;
                default:
                    return 0;
            }
        }
    }




    public static Character[] toCharacterArray(char[] list){
        Character[] result = new Character[list.length];
        for (int i=0; i<result.length; i++){
            result[i] = list[i];
        }
        return result;
    }
}
