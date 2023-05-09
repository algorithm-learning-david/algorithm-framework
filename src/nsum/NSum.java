package nsum;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NSum {

    public List<List<Integer>> nSumTarget(int[] nums, int n, int start, long target){
        List<List<Integer>> res = new ArrayList<>();
        int length = nums.length;
        if(n < 2 || length < n){
            return res;
        }
        //base case
        if(n ==2){
            int lo = start, hi = length - 1;
            while (lo < hi){
                int left = nums[lo], right = nums[hi];
                long sum = nums[lo] + nums[hi];
                if(sum < target){
                    while (lo < hi && left == nums[lo]){
                        lo ++;
                    }
                }else if(sum > target){
                    while (lo < hi && right == nums[hi]){
                        hi --;
                    }
                }else{
                    List<Integer> r = new ArrayList<>();
                    r.add(left);
                    r.add(right);
                    res.add(r);
                    while (lo < hi && left == nums[lo]){
                        lo ++;
                    }
                    while (lo < hi && right == nums[hi]){
                        hi --;
                    }
                }
            }
        }else{
            for (int i = start; i < length; i++) {
                List<List<Integer>> sub = nSumTarget(nums, n - 1, i + 1, target - nums[i]);
                for (List<Integer> arr : sub) {
                    arr.add(nums[i]);
                    res.add(arr);
                }
                while (i < length - 1 && nums[i] == nums[i + 1]){
                    i ++;
                }
            }
        }
        return res;
    }

    @Test
    public void testNSum(){
        int[] nums_2 = {2, 2, 7, 1, 1, 3, 3, 11, 15, 6, 6, 2, 7, 7, 8, 8};
        Arrays.sort(nums_2);
        List<List<Integer>> twoSum = nSumTarget(nums_2, 2, 0, 9);
        System.out.printf("twoSum: %s%n",twoSum);

        int[] nums_3 = new int[]{-1, 0, 1, 2, -1, -4};
        Arrays.sort(nums_3);
        List<List<Integer>> threeSum = nSumTarget(nums_3, 3, 0, 0);
        System.out.printf("threeSum: %s%n",threeSum);

        int [] nums_4 = new int[]{1,0,-1,0,-2,2};
        Arrays.sort(nums_4);
        List<List<Integer>> fourSum = nSumTarget(nums_4, 4, 0, 0);
        System.out.printf("fourSum: %s%n",fourSum);

        int[] nums_4_1 = new int[]{1000000000, 1000000000, 1000000000, 1000000000};
        Arrays.sort(nums_4_1);
        List<List<Integer>> fourSum_1 = nSumTarget(nums_4_1, 4, 0, -294967296);
        System.out.printf("fourSum_1: %s%n",fourSum_1);
    }
}
