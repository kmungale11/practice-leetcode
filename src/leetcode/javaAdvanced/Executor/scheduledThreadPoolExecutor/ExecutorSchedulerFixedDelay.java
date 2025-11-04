package leetcode.javaAdvanced.Executor.scheduledThreadPoolExecutor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class Task implements Runnable {

    @Override
    public void run() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH-mm-ss");
        LocalDateTime time = LocalDateTime.now();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(formatter.format(time));
    }
}

public class ExecutorSchedulerFixedDelay {

    public static void main(String[] args) {

        ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(1);

        ScheduledFuture<?> scheduledFuture = scheduler.scheduleWithFixedDelay(new Task(), 2,3, TimeUnit.SECONDS);

        scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                scheduledFuture.cancel(true);
                scheduler.shutdown();
            }
        }, 10, TimeUnit.SECONDS);



    }
}
