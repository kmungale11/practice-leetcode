package leetcode.javaAdvanced.completableFutureWThreadPoolAndFactory;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

class CustomThreadFactory implements ThreadFactory {

    private Thread thread;
    private String name;
    private final AtomicInteger threadInteger = new AtomicInteger();
    public CustomThreadFactory(String name) {
        this.name = name;
    }
    @Override
    public Thread newThread(Runnable r) {
        thread = new Thread(r, name + "-thread-" + threadInteger.incrementAndGet());
        thread.setDaemon(false);
        return thread;
    }
}

public class CompletableFutureDemoWThreadPoolAndFactory {

    private final ExecutorService service;

    public CompletableFutureDemoWThreadPoolAndFactory() {
        ThreadFactory factory = new CustomThreadFactory("Custom");
        service = new ThreadPoolExecutor(2, 2, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(5), factory);
    }

    ///  Each completableFuture.supplyAsync runs in separate thread if available
    public CompletableFuture<String> getFirstName() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
                System.out.println("Waiting for 2s in firstName: with current thread - " + Thread.currentThread().getName() );
                return "Kaustubh";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, service);
    }

    public CompletableFuture<String> getLastName() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("Waiting for 4s in lastName - " + Thread.currentThread().getName() );
                return "Mungale";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, service);
    }

    public static void main(String[] args) throws InterruptedException {

        CompletableFutureDemoWThreadPoolAndFactory demo = new CompletableFutureDemoWThreadPoolAndFactory();
        CompletableFuture<String> firstName = demo.getFirstName();
        CompletableFuture<String> lastName = demo.getLastName();

        firstName.thenCombine(lastName, (fname, lname) -> fname + " " + lname)
                .exceptionally(err -> {
                    System.out.println("Error: " + err);
                    return err.getMessage();
                })
                .thenAccept(System.out::println);

        // Wait long enough for all async tasks to complete
        Thread.sleep(7000);
        demo.service.shutdown();
    }
}
