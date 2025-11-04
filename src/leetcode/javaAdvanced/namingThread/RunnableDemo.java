package leetcode.javaAdvanced.namingThread;

class Demo implements Runnable {
    String name;

    public Demo(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}


public class RunnableDemo {

    public static void main(String[] args) throws InterruptedException {
        Runnable demoRun = new Demo("Thread-123");
        Thread t1 = new Thread(demoRun);

        t1.start();
        t1.join();
    }
}
