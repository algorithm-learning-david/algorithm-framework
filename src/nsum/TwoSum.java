package nsum;

import org.junit.Test;

import java.util.*;

public class TwoSum {
    public int[] twoSum_1(int[] nums, int target) {
        if(nums.length == 0){
            return new int[0];
        }
        //<nums[i],i>
        Map<Integer,Integer> m = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            int r = target - nums[i];
            if(m.containsKey(r)){
                return new int[]{m.get(r),i};
            }else{
                m.put(nums[i],i);
            }

        }
        return new int[0];
    }


    public int[] twoSum(int[] nums, int target){
        if(nums.length == 0){
            return new int[0];
        }
        Arrays.sort(nums);
        int slow = 0, fast = nums.length - 1;
        while(slow < fast){
            int sum = nums[slow] + nums[fast];
            if(sum < target){
                slow ++;
            }else if(sum > target){
                fast --;
            }else {
                return new int[]{nums[slow],nums[fast]};
            }
        }
        return new int[0];
    }

    /**
     * 返回所有的满足条件的集合，且元素不能重复
     */
    public List<List<Integer>> twoSum_generic(int[] nums, int target){
        if(nums.length == 0){
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        int slow = 0, fast = nums.length - 1;
        List<List<Integer>> res = new ArrayList<>();
        while(slow < fast){
            int sum = nums[slow] + nums[fast];
            //记录索引slow和fast最初的对应位置
            int left = nums[slow], right = nums[fast];
            if(sum < target){
                while (slow < fast && left == nums[slow]){
                    slow ++;
                }
            }else if(sum > target){
                while (slow < fast && right == nums[fast]){
                    fast --;
                }
            }else {
                List<Integer> ele = new ArrayList<>();
                ele.add(nums[slow]);
                ele.add(nums[fast]);
                res.add(ele);
                while (slow < fast && left == nums[slow]){
                    slow ++;
                }
                while (slow < fast && right == nums[fast]){
                    fast --;
                }
            }
        }
        return res;
    }

    public List<List<Integer>> twoSum_target(int[] nums, int start, int target){
        if(nums.length == 0){
            return new ArrayList<>();
        }
        int slow = start, fast = nums.length - 1;
        List<List<Integer>> res = new ArrayList<>();
        while(slow < fast){
            int sum = nums[slow] + nums[fast];
            //记录索引slow和fast最初的对应位置
            int left = nums[slow], right = nums[fast];
            if(sum < target){
                while (slow < fast && left == nums[slow]){
                    slow ++;
                }
            }else if(sum > target){
                while (slow < fast && right == nums[fast]){
                    fast --;
                }
            }else {
                List<Integer> ele = new ArrayList<>();
                ele.add(left);
                ele.add(right);
                res.add(ele);
                while (slow < fast && left == nums[slow]){
                    slow ++;
                }
                while (slow < fast && right == nums[fast]){
                    fast --;
                }
            }
        }
        return res;
    }

    @Test
    public void testTwoSum(){
        List<List<Integer>> lists = twoSum_generic(new int[]{2, 2, 7, 1, 1, 3, 3, 11, 15, 6, 6, 2, 7, 7, 8, 8}, 9);
        System.out.println(lists);
    }
}
