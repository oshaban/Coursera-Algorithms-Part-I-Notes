package dutchflagproblem;

import java.util.Arrays;
import org.junit.Assert;

/**
 * The Dutch Flag Problem using 3-way partitioning
 */
public class DutchFlagProblem {

    public static void main(String[] args) {
        int[] nums = new int[] {2,0,2,1,1,0};
        partition(nums);
        System.out.println(Arrays.toString(nums));
        Assert.assertArrayEquals(nums, new int[] {0,0,1,1,2,2});
    }

    // Dijkstra's 3 way partition
    public static void partition(int[] nums) {
        int i = 0;
        int left = 0;
        int right = nums.length - 1;

        while (i <= right) {
            if (nums[i] == 0) swap(nums, left++, i++);
            else if (nums[i] == 1) i++;
            else if (nums[i] == 2) swap(nums, i, right--);
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
