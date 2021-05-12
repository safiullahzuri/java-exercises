package hashing.exercises.ex3;

import org.omg.PortableInterceptor.INACTIVE;

public class Ex27_03 {

    public static void main(String[] args) {

        MyMap<String, Integer> map = new MyHashMap<>();
        map.put("smith", 30);
        map.put("anderson", 31);
        map.put("lewis", 29);
        map.put("cook", 29);

        map.put("mark", 65);
        map.put("mark", 21);
        map.put("william", 21);

        System.out.println("Entries in the map: "+map.toString());

        System.out.println("The age of Lewis is "+map.get("mark"));

        System.out.println("mark: "+map.getAll("mark"));

    }

}
