package hashing.learning;

public class Test {

    public static void main(String[] args) {
//        MyMap<String, Integer> map = new MyHashMap<>();
//
//        map.put("Smith", 30);
//        map.put("safiullah", 245);
//        map.put("zuri", 123);
//
//        System.out.println("Entries in map: "+map);
//
//        System.out.println("The age for safiullah is: "+map.get("safiullah"));
//
//        System.out.println("map contains zuri? "+map.containsKey("zuri"));


//        MySet<String> set = new MyHashSet<>();
//        set.add("belal agha");
//        set.add("safiullah");
//        set.add("zuri");
//
//        System.out.println("Elements in set: "+set);
//        System.out.println("Number of elements: "+set.size());
//        System.out.println("zuri in set? "+set.contains("zuri"));
//
//        set.remove("zuri");
//        System.out.println("zuri in set? "+set.contains("zuri"));

        System.out.println(3 & 127);


    }

    private static int hash(int hashCode){
        return supplementalHash(hashCode) & (int)(Math.pow(2, 20));
    }

    private static int supplementalHash(int h){
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }


}
