import java.util.Arrays;

public class ThreeSumClosest {
    public int threeSumClosest(int[] nums,
                               int target) {
        int result = target >= 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == target) {
                    return target;
                }
                if (sum > target) {
                    right--;
                } else {
                    left++;
                }
                if (Math.abs(sum - target) < Math.abs(result - target)) {
                    result = sum;
                }
            }
        }
        return result;
    }
}
