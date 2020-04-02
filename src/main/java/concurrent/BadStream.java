package concurrent;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class BadStream {
    public static void main(String[] args) {
        long [] count = { 0 };

//        IntStream.iterate(0, x -> x + 2)
//                .forEach(x -> System.out.println(x));

        IntStream.generate(() -> ThreadLocalRandom.current().nextInt(0, 100))
                .parallel()
                .limit(100_000_000)
                .peek(x -> count[0]++)
                .forEach(x -> {})
        ;
        System.out.println("Final count is " + count[0]);
    }
}
