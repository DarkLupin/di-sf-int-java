package threads;

public class Stopper {
    volatile static boolean stop = false;
    public static void main(String[] args) throws Throwable {

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + " starting");
            while (!stop)
                ;
            System.out.println(Thread.currentThread().getName() + " ending");
        }).start();
        System.out.println("Second thread launched");
        Thread.sleep(1000);
        System.out.println("Main thread about to set stop flag");
        stop = true;
        System.out.println("Main thread exiting.");
    }
}
