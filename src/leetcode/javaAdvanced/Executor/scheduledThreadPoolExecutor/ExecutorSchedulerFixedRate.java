package leetcode.javaAdvanced.Executor.scheduledThreadPoolExecutor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class Beep implements Runnable {

    @Override
    public void run() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss");
        LocalDateTime time = LocalDateTime.now();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Printing time" + formatter.format(time));
    }
}

public class ExecutorSchedulerFixedRate {

    public static void main(String[] args) {
        ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(1);

        ScheduledFuture<?> beepHandler = scheduler.scheduleAtFixedRate(new Beep(),2,2, TimeUnit.SECONDS);

        scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                beepHandler.cancel(true);
                scheduler.shutdown();
            }
        }, 10, TimeUnit.SECONDS);
    }
}
