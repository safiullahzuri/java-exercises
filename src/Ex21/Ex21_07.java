package Ex21;

import java.util.*;

public class Ex21_07 {

    public static void main(String[] args) {
        String text = "Good morning. Have good a good class. Have a good visit. Have fun!";
        Map<String, Integer> map = new TreeMap<>();

        String[] words = text.split("[\\s+\\p{P}]");
        for (int i=0; i< words.length; i++){
            String key = words[i].toLowerCase();

            if (key.length() > 0){
                if (!map.containsKey(key)){
                    map.put(key, 1);
                }else {
                    int value = map.get(key);
                    value++;
                    map.put(key, value);
                }
            }
        }
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(map.entrySet());
        Collections.sort(entryList, (entry1, entry2)->{
            return entry2.getValue().compareTo(entry1.getValue());
        });

        map.forEach((k,v) -> {
            System.out.println(k+"\t"+v);
        });
        System.out.println();
        entryList.forEach(entry ->{
            System.out.println(entry.getKey()+"\t"+entry.getValue());
        });
    }
}
