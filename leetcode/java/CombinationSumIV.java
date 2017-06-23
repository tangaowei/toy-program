/**
Given an integer array with all positive numbers and on duplicates, 
find the number of possible combinations that add up to a positive
integer target.
note that the different sequences are counted as different combination
**/
import java.util.HashMap;
public class CombinationSumIV {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        int N = nums.length;
        for(int i = 1; i <= target; ++i) {
            for(int j = 0; j < N; ++j) {
                if(i == nums[j]) {
                    dp[i] += 1;
                } else if(i > nums[j]) {
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        if(args.length < 2) {
            System.out.println("CombinationSumIV x x");
            return;
        }
        int N = args.length;
        int[] nums = new int[N - 1];
        for(int i = 0; i < N - 1; ++i) {
            nums[i] = Integer.parseInt(args[i]);
        }
        int target = Integer.parseInt(args[N - 1]);

        CombinationSumIV obj = new CombinationSumIV();
        int ret = obj.combinationSum4(nums, target);
        System.out.println("target = " + target);
        System.out.println("ret = " + ret);
    }
}
