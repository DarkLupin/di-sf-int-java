package threads;

class MyJob implements Runnable {
    int i = 0;
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " starting");
        for (; i < 1000; i++) {
            System.out.println(Thread.currentThread().getName() + " i is " + i);
        }
        System.out.println(Thread.currentThread().getName() + " ending");
    }
}

public class RunnableOne {
    public static void main(String[] args) {
        Runnable myJob = new MyJob();
        Thread t1 = new Thread(myJob);
        Thread t2 = new Thread(myJob);
//        t1.setDaemon(true);
//        t2.setDaemon(true);
        t1.start();
        t2.start();
        System.out.println(Thread.currentThread().getName() + " other jobs launched, main exiting...");
    }
}
