/**
 * Created by mxg on 2022/1/20
 */
public class BinarySearch {



    //二分查找基本框架
//    int binarySearch(int[] nums, int target) {
//        int left = 0, right = ...;
//
//        while(...) {
//            int mid = left + (right - left) / 2;
//            if (nums[mid] == target) {
//            ...
//            } else if (nums[mid] < target) {
//                left = ...
//            } else if (nums[mid] > target) {
//                right = ...
//            }
//        }
//        return ...;
//    }

    //基本的二分查找
    int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        return -1;
    }

    //查找左边界
    int leftBound(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] == target) {
                right = mid; //收缩右边界
            }
        }
        return left;
    }

    //查找右边界
    int rightBound(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] == target) {
                left = mid + 1; //收缩左边界
            }
        }
        return left - 1;
    }
}
