package Ex21;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class Ex21_14 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a URL:");
        String url = input.nextLine();
        crawler(url);
    }

    public static void crawler(String url){
        Queue<String> listOfPendingURLs = new LinkedList<>();
        Set<String> setOfTraversedURLs = new HashSet<>();

        listOfPendingURLs.add(url);
        while (!listOfPendingURLs.isEmpty() && setOfTraversedURLs.size() <= 100){
            String urlString = listOfPendingURLs.poll();
            setOfTraversedURLs.add(urlString);

            System.out.println("Crawl "+urlString);

            for (String s: getSubURLs(urlString)){
                if (!setOfTraversedURLs.contains(s)){
                    listOfPendingURLs.add(s);
                }
            }
        }
    }

    public static Set<String> getSubURLs(String urlString){
        Set<String> set = new HashSet<>();
        try {
            URL url = new URL(urlString);
            Scanner input = new Scanner(url.openStream());
            int current = 0;
            while (input.hasNext()){
                String line = input.nextLine();
                current = line.indexOf("http:", current);

                while (current > 0){
                    int endIndex = line.indexOf("\"", current);
                    if (endIndex > 0){
                        set.add(line.substring(current, endIndex));
                        current = line.indexOf("http:", endIndex);
                    }else{
                        current = -1;
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error: "+e.toString());
        }
        return set;
    }
}
