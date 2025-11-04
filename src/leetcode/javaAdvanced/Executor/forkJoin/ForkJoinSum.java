package leetcode.javaAdvanced.Executor.forkJoin;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class ForkJoinSumArray extends RecursiveTask<Long> {  /// Extend the RecursiveTask
    private static final int THRESHOLD = 1000; // threshold for direct computation
    private int[] array;
    private int start;
    private int end;

    public ForkJoinSumArray(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    /*
    Base Case: If the segment of the array is within the THRESHOLD, the sum is calculated iteratively.
    Recursive Case: If the segment is larger than the THRESHOLD, it's split into two halves.
    leftTask.fork(): This line submits the left subtask to the ForkJoinPool to be executed asynchronously, potentially by another thread.
    rightTask.compute(): This line computes the right subtask directly in the current thread. Alternatively, rightTask.fork() could also be used, followed by rightTask.join().
    leftTask.join(): This line waits for the leftTask to complete and retrieves its result.
    The results from the left and right subtasks are then combined.
     */
    @Override
    protected Long compute() {
        if(end - start < THRESHOLD) {
            long sum = 0;
            for(int i=start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        } else {

            int mid =(start + end) / 2;
            ForkJoinSumArray leftTask = new ForkJoinSumArray(array, start, mid);
            ForkJoinSumArray rightTask = new ForkJoinSumArray(array, mid, end);

            leftTask.fork(); /// This is executed by separate thread in pool
            rightTask.fork(); /// This is also executed by separate thread in pool

            Long leftResult = leftTask.join();
            Long rightResult = rightTask.join();
            return leftResult + rightResult;
        }
    }
}

public class ForkJoinSum {
    public static void main(String[] args) {
        int[] data = new int[100000];
        for (int i = 0; i < data.length; i++) {
            data[i] = i + 1;
        }

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinSumArray forkJoinSumArray = new ForkJoinSumArray(data, 0, data.length);
        long sum = pool.invoke(forkJoinSumArray); /// Necessary to invoke the forkPool join

        System.out.println(sum);
    }
}


