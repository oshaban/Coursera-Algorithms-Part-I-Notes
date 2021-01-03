package problems.getminmax;

import java.util.Arrays;
import java.util.Random;

/**
 * Finds the min and max of an array using Divide and Conquer
 */
public class MinMax {

    public static void main(String[] args) {

        // Create random array
        Random random = new Random();
        int[] nums = new int[10];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(100) - 50;
        }

        // Find min and max
        Pair pair = getMinMax(nums, 0, nums.length - 1);

        System.out.println("The array is: " + Arrays.toString(nums));
        System.out.println("The max is: " + pair.max);
        System.out.println("The min is: " + pair.min);

    }

    public static Pair getMinMax(int[] nums, int low, int high) {

        // Base case:
        // Array of size 1; the single element is both the min and max
        if (low == high) {
            return new Pair(nums[low], nums[high]);
        }

        // Split the array in half, find min and max of both halves, then merge
        int mid = low + (high - low) / 2;

        Pair firstPair = getMinMax(nums, low, mid);
        Pair secondPair = getMinMax(nums, mid + 1, high);

        int min = Math.min(firstPair.min, secondPair.min);
        int max = Math.max(firstPair.max, secondPair.max);

        return new Pair(min, max);
    }

    public static class Pair {
        int min;
        int max;

        public Pair(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }

}