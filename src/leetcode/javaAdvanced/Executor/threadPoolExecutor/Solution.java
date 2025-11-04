package leetcode.javaAdvanced.Executor.threadPoolExecutor;


import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

class Test implements ThreadFactory {
    private final String prefix;
    AtomicInteger integer = new AtomicInteger(0);
    public Test(String prefix){this.prefix = prefix;}

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r,prefix + "-" + integer.incrementAndGet());
        thread.setDaemon(false);
        return thread;
    }
}

class TestGroup implements ThreadFactory {
    private final String prefix;
    AtomicInteger integer = new AtomicInteger(0);

    ThreadGroup group = new ThreadGroup("custom-group2");
    public TestGroup(String prefix){this.prefix = prefix;}
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(group, r, prefix + "-" + integer.incrementAndGet());
        return thread;
    }
}

class Demo {
    public ExecutorService service = new ThreadPoolExecutor(4,10,1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(5), new TestGroup("custom"));

    public CompletableFuture<String> test() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(4000);
                System.out.println("Running in custom thread group: " + Thread.currentThread().getThreadGroup().getName());
                String s = "Kaustubh";
                return s;
            } catch (InterruptedException ex) {
                return "";
            }
        }, service);
    }
}

public class Solution {

    public static void main(String[] args) {
        Demo d = new Demo();
        d.test().thenApply(result -> {
           System.out.println(result);
           return result;
        });

        d.service.shutdown();
    }
}
