package quicksort;

import java.util.Arrays;

/**
 * Quick Sort implementation
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] nums = {5, 4, 3, 2, 1, 1};
        new QuickSort().quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void quickSort(int[] nums) {
        sort(nums, 0, nums.length - 1);
    }

    public void sort(int[] nums, int low, int high) {
        if (low >= high) return;

        int p = partition(nums, low, high);
        sort(nums, low, p - 1);
        sort(nums, p + 1, high);
    }

    public int partition(int[] nums, int low, int high) {

        int i = low;
        int j = high + 1;

        while (i < j) {

            while (nums[++i] < nums[low]) {
                if (i == high) break;
            }

            while (nums[--j] > nums[low]) {
                if (i == low) break;
            }

            if (i >= j) break;

            swap(nums, i, j);
        }

        swap(nums, low, j);
        return j;
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
