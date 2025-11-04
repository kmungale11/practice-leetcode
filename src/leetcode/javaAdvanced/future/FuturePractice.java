package leetcode.javaAdvanced.future;

import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class FuturePractice {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /// Runs without any return
        CompletableFuture<Void> runAsyncResult = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println("Kaustubh from runAsync block");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        runAsyncResult.get();

        ///  Supply async will return completable future
        CompletableFuture<String> firstName =  CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
                return "Kaustubh";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).thenCompose(result -> CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
                return result + " Mungale Combine";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }));

        CompletableFuture<String> lastName =  CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
                return "Mungale lastName Future";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        CompletableFuture<String> fullName = firstName.thenCombine(lastName, (res1, res2) -> res1 + " " + res2);

        CompletableFuture<Void> all = CompletableFuture.allOf(firstName, lastName);

        String fname = firstName.get();
        System.out.println(fname);

        String name = fullName.get();
        System.out.println(name);

        all.get();
        System.out.println(firstName.get());
        System.out.println(lastName.get());
    }
}
