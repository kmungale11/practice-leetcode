package leetcode.sort.basic;

import java.util.Arrays;

public class MergeSort {

    public void sort(int[] arr) {
        if(arr == null || arr.length <=1 ) return;
        divide(0, arr.length-1, arr);
    }

    private void divide(int low, int high, int[] arr) {
        if (low <  high) {
            int mid = (low + high) / 2;
            divide(low, mid, arr);
            divide(mid+1, high, arr);
            merge(low, mid, high, arr);
        }
    }

    private void merge(int low, int mid, int high, int[] arr) {
        int len1 = mid - low + 1;
        int len2 = high - mid;

        int[] arr1 = new int[len1];
        int[] arr2 = new int[len2];

        for(int i=0; i < len1; i++) {
            arr1[i] = arr[i + low];
        }

        for(int j=0; j < len2; j++) {
            arr2[j] = arr[mid + j+1];
        }

        int i=0,j=0;
        int k = low;

        while(i < len1 && j < len2) {
            if(arr1[i] <= arr2[j]) {
                arr[k] = arr1[i];
                i++;
            } else {
                arr[k] = arr2[j];
                j++;
            }
            k++;
        }

        while (i < len1) {
            arr[k] = arr1[i];
            k++;
            i++;
        }

        while (j < len2) {
            arr[k] = arr2[j];
            k++;
            j++;
        }
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] arr = new int[]{5,2,6,3,4};
        int[] arr1 = new int[]{8,7,3,5,4};
        mergeSort.sort(arr1);
        System.out.println(Arrays.toString(arr1));
    }
}
