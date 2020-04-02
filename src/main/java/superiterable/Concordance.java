package superiterable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Concordance {
    public static void main(String[] args) {
        final Pattern WORD_BOUNDARY = Pattern.compile("\\W+");

        List<String> ls;

//        final Comparator<Map.Entry<String, Long>> descendingByValue =
//                (e1, e2) -> e2.getValue().compareTo(e1.getValue());
//        final Comparator<Map.Entry<String, Long>> ascendingByValue = Map.Entry.comparingByValue();
//        final Comparator<Map.Entry<String, Long>> descendingByValue = ascendingByValue.reversed();
        final Comparator<Map.Entry<String, Long>> descendingByValue =
                Map.Entry.<String, Long>comparingByValue().reversed();

        try (Stream<String> input = Files.lines(Paths.get("PrideAndPrejudice.txt"))) {
            input
                    .flatMap(s -> WORD_BOUNDARY.splitAsStream(s))
                    .filter(s -> !s.isEmpty())
                    .map(s -> s.toLowerCase())
                    .collect(Collectors.groupingBy(Function.identity()/*s -> s*/,
                            Collectors.counting()))
                    .entrySet().stream()
                    .sorted(descendingByValue)
                    .limit(200)
                    .map(e -> String.format("%20s : %4d", e.getKey(), e.getValue()))
                    .forEach(s -> System.out.println(s));
        } catch (IOException ioe) {
            System.out.println("IO problem");
        }
    }
}
