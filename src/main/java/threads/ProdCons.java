package threads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.IntStream;

public class ProdCons {
    public static void delay() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {

        }
    }
    public static void putInQueue(BlockingQueue<int[]> q, int[] x) {
        try {
            q.put(x);
        } catch (InterruptedException e) {

        }
    }
    public static void main(String[] args) {
        final BlockingQueue<int[]> queue = new ArrayBlockingQueue<>(10);
        new Thread(() -> {
            // Producer
            System.out.println("Producer starting...");
//            IntStream.iterate(0, x -> x + 1)
//                    .limit(1000)
//                    .mapToObj(x -> new int[]{x, x})
//                    .map(x -> {
//                        if (x[0] == 500) return new int[]{x[0], -1};
//                        else return x;
//                    })
//                    .map(x -> {
//                        if (x[0] < 100) delay();
//                        return x;
//                    })
//                    .forEach(x -> putInQueue(queue, x));
            for (int idx = 0; idx < 1000; idx++) {
                try {
                    int[] data = new int[2];
                    data[0] = idx;
                    if (idx < 100) Thread.sleep(1);
                    data[1] = idx;
                    if (idx == 500) data[1] = -1;
                    queue.put(data);
                    data = null;
                } catch (InterruptedException ioe) {

                }
            }
            System.out.println("Producer finished...");
        }).start();
        new Thread(() -> {
            // Consumer
            System.out.println("Consumer starting...");
            for (int idx = 0; idx < 1000; idx++) {
                try {
                    int [] data = queue.take();
                    if (data[0] != data[1] || data[0] != idx) {
                        System.err.println("***** PROBLEM AT INDEX " + idx);
                    }
                    if (idx > 900) Thread.sleep(1);
                } catch (InterruptedException ie) {

                }
            }
            System.out.println("Consumer finished...");
        }).start();
        System.out.println("Producer and consumer launched...");
    }
}
