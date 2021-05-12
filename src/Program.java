import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Program {

    public static void main(String[] args){

        Integer[] a = {1,2,3,45,6,7,8,9};

        Integer[] b = a;

        a[0] = 1222;
        b[0] = 9999;

        System.out.println(a[0]);
        System.out.println(b[0]);

    }

}
