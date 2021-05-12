package aggregate;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Practice {

    public static void main(String[] args) {
        int[] values = {3, 4, 1, 5, 20, 1, 3,3 ,4, 6};

        System.out.println("The values are "+
                IntStream.of(values).mapToObj(e-> e+"").reduce((e1, e2)->e1+", "+e2).get());
    }
}
