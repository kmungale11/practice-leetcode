package leetcode.javaAdvanced.completableFutureWithTimeoutAndExceptionally;

import java.util.concurrent.*;

class CompletableWithTimeOutAndExceptionally {

    private ExecutorService service = new ThreadPoolExecutor(2,10, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10));

    public CompletableFuture<String> firstName() {
        try {
            Thread.sleep(1000);
            return CompletableFuture.completedFuture("Kaustubh");
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}

public class Demo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableWithTimeOutAndExceptionally completableDemo = new CompletableWithTimeOutAndExceptionally();
        CompletableFuture<String> test = completableDemo.firstName()
                .orTimeout(100, TimeUnit.MILLISECONDS)
                .thenApply(result -> {return  result;})
                .exceptionally(ex -> {
                    System.out.println("Error reading firstName: " + ex);
                    return null;
                });
        System.out.println(test.get());
    }
}
