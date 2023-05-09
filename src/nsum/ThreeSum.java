package nsum;

import org.junit.Test;

import java.util.*;

/**
 * 15.三数之和
 */
public class ThreeSum {

    private TwoSum twoSum = new TwoSum();

    //nums = [-1,0,1,2,-1,-4]
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int target = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int t = target - nums[i];
            List<List<Integer>> twoSumResults = twoSum.twoSum_target(nums, i + 1, t);
            for (List<Integer> twoSumResult : twoSumResults) {
                twoSumResult.add(nums[i]);
                res.add(twoSumResult);
            }
            // 跳过第一个数字重复的情况，否则会出现重复结果
            while (i < nums.length - 1 && nums[i] == nums[i + 1]){
                i ++;
            }
        }
        return res;
    }


    @Test
    public void testThreeSum(){
        List<List<Integer>> lists = threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        System.out.println(lists);
    }
}
