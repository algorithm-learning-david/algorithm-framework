package nsum;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length < 4){
            return res;
        }
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i ++){
            long rest = target - nums[i];
            List<List<Integer>> threeRes = threeSum(nums, i + 1, rest);

            for(List<Integer> tw : threeRes){
                tw.add(nums[i]);
                res.add(tw);
            }

            while(i < nums.length - 1 && nums[i] == nums[i + 1]){
                i ++;
            }
        }
        return res;
    }

    public List<List<Integer>> threeSum(int[] nums, int start, long target) {
        List<List<Integer>> res = new ArrayList<>();
        for(int i = start; i < nums.length; i ++){
            long rest = target - nums[i];
            List<List<Integer>> twoRes = twoSum(nums, i + 1, rest);

            for(List<Integer> tw : twoRes){
                tw.add(nums[i]);
                res.add(tw);
            }

            while(i < nums.length - 1 && nums[i] == nums[i + 1]){
                i ++;
            }
        }
        return res;

    }

    public List<List<Integer>> twoSum(int[] nums, int start, long target){
        List<List<Integer>> res = new ArrayList<>();
        int lo = start, hi = nums.length - 1;
        while(lo < hi){
            int left = nums[lo], right = nums[hi];
            int sum = nums[lo] + nums[hi];
            if(sum < target){
                while(lo < hi && left == nums[lo]){
                    lo ++;
                }
            }else if(sum > target){
                while(lo < hi && right == nums[hi]){
                    hi --;
                }
            }else {
                List<Integer> r = new ArrayList<>();
                r.add(left);
                r.add(right);
                res.add(r);
                while(lo < hi && left == nums[lo]){
                    lo ++;
                }
                while(lo < hi && right == nums[hi]){
                    hi --;
                }
            }
        }
        return res;
    }


    @Test
    public void test4sum(){
        List<List<Integer>> lists = fourSum(new int[]{1000000000, 1000000000, 1000000000, 1000000000}, -294967296);
        System.out.println(lists);
    }
}
