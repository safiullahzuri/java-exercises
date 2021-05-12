package internationalization;

import java.text.DateFormatSymbols;
import java.util.stream.Stream;

public class Ex1 {

    public static void main(String[] args) {
        DateFormatSymbols symbols = new DateFormatSymbols();

        String[] monthnames = symbols.getMonths();

        Stream.of(monthnames).forEach(System.out::println);

        String[] weekDays = symbols.getWeekdays();
        Stream.of(weekDays).forEach(System.out::println);
    }

}
