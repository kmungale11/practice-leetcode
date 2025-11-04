package leetcode.javaAdvanced.threadScheduler;

import java.time.LocalDateTime;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadSchedulerFixedRate {

    public static void main(String[] args) {

        ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(1);

        ScheduledFuture<?> scheduledFuture = scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    LocalDateTime time = LocalDateTime.now();
                    System.out.println("Printing at fixed rate: " + time);
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, 2, 2, TimeUnit.SECONDS);


        ScheduledFuture<?> schedulerOnce = scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                scheduledFuture.cancel(true);
                scheduler.shutdown();
            }
        }, 10, TimeUnit.SECONDS);

    }
}
