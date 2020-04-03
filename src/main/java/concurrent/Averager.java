package concurrent;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

class Average {
    private double sum;
    private long count;

    public Average(double sum, long count) {
        this.sum = sum;
        this.count = count;
    }

    public Average include(double d) {
        return new Average(sum + d, count + 1);
    }

    public Average merge(Average other) {
        return new Average(this.sum + other.sum, this.count + other.count);
    }

    public Optional<Double> get() {
        if (count > 0) return Optional.of(sum / count);
        else return Optional.empty();
    }
}

public class Averager {
    public static void main(String[] args) {
        final long COUNT = 1_000_000_000;
        long start = System.nanoTime();
        ThreadLocalRandom.current().doubles(COUNT, -Math.PI, +Math.PI)
                .parallel()
                .boxed()
//                .mapToObj(d -> d)
                .map(d -> Math.sin(d))
                .reduce(new Average(0, 0),
                        (average, data) -> average.include(data),
                        (av1, av2) -> av1.merge(av2))
                .get() // gets the actual
                .ifPresent(av -> System.out.println("Average is " + av));

        long time = System.nanoTime() - start;
        System.out.printf("Time was %7.2f seconds to process %d items. Rate is %d per microsecond\n",
                (time / 1_000_000_000.0), COUNT, (COUNT * 1000 / time));
    }
}
