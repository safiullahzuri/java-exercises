package hashing.exercises.ex1;

public class E27_01 {

    public static void main(String[] args) {
//        LinearProbingMap<Integer, Integer> map = new LinearProbingMap<>(32);
//
//        map.put(32, 1);
//        map.put(64, 2);
//        map.put(96, 3);
//        map.put(128, 4);
//        map.put(160, 5);
//        map.put(192, 6);
//        map.put(224, 7);
//        map.put(256, 8);
//
//        System.out.println(map.get(160));
//        System.out.println(map.get2(160));
//        printTable(map.table);
        int j = 1;
        System.out.println(Math.pow(j++, 2));
        System.out.println("j is "+j);

    }

    public static void printTable(MyMap.Entry<Integer, Integer>[] table){
        for (int i=0; i<table.length; i++){
            System.out.println(i+": "+table[i]);
        }
    }

}
