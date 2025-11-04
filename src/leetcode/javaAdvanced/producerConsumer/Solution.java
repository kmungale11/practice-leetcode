package leetcode.javaAdvanced.producerConsumer;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
    private final int MAX = 5;
    private Queue<Integer> queue = new ArrayDeque<>();
    private final Object object = new Object();

    public void add(int val) {
        synchronized (object) {
            while (queue.size() == MAX) {
                try {
                    System.out.println("Queue full. Wait ....");
                    object.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("value: " + val + " added");
            queue.add(val);
            object.notifyAll();
        }
    }

    public int remove() {
        synchronized (object) {
            while(queue.isEmpty()) {
                try {
                    System.out.println("Queue is Empty. Wait ....");
                    object.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            int result = queue.poll();
            System.out.println("value: " + result + " removed");
            object.notifyAll();
            return result;
        }
    }

    public static void main(String[] args) throws Exception {
        Solution s = new Solution();
        Thread t1 = new Thread(() -> {

            for(int i=0; i < 10; i++) {
                try {
                    Thread.sleep(3000);
                    s.remove();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "thread-1");

        Thread t2 = new Thread(() -> {

            for(int i=0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                    s.add(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "thread-1");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

    }
}
