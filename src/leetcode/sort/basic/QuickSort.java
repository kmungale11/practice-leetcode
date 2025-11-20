package leetcode.sort.basic;

public class QuickSort {

    public void sort(int[] arr) {
        if(arr == null || arr.length <= 1) return;
        quickSort(0, arr.length-1, arr);
    }

    private void quickSort(int low, int high, int[] arr) {

        if (low < high) {
            int index = partition(low, high, arr);
            quickSort(low, index-1, arr);
            quickSort(index+1, high, arr);
        }
    }

    private int partition(int low, int high, int[] arr) {
        int pivot = high;
        int j=low;
        for(int i=low; i < pivot; i++) {
            if(arr[i] < arr[pivot]) {
                int temp =  arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
                j++;
            }
        }

        int temp = arr[pivot];
        arr[pivot] = arr[j];
        arr[j] = temp;
        return j;
    }

    public static void main(String[] args) {
        QuickSort sort = new QuickSort();
        int[] arr = new int[]{5,2,1,3,4};
        sort.sort(arr);
        System.out.println("sorted");
    }
}
