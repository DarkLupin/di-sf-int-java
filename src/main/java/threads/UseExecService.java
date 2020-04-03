package threads;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

class MyCallableJob implements Callable<String> {
    private static int nextId = 0;
    private int myId = nextId++;

    @Override
    public String call() throws Exception {
        System.out.println("Job " + myId + " Starting in thread " + Thread.currentThread().getName());
        Thread.sleep((int)(Math.random() * 2000) + 1000);
        System.out.println("Job " + myId + " Ending in thread " + Thread.currentThread().getName());
        return "Job " + myId + " returned value";
    }
}

public class UseExecService {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2);
        List<Future<String>> handles = new ArrayList<>();
        for (int idx = 0; idx < 10; idx++) {
            handles.add(es.submit(new MyCallableJob()));
        }
        es.shutdown();
        System.out.println("All jobs submitted...");
        while (handles.size() > 0) {
            Iterator<Future<String>> iter = handles.iterator();
            while (iter.hasNext()) {
                Future<String> handle = iter.next();
                if (handle.isDone()) {
                    try {
                        String result = handle.get();
                        System.out.println("A job completed and returned " + result);
                    } catch (InterruptedException e) {
                        System.out.println("foreground thread received interrupt!???");
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        System.out.println("Job threw exception...");
                        e.printStackTrace();
                    }
                    iter.remove();
                }
            }
        }
        System.out.println("All jobs completed...");
    }
}
