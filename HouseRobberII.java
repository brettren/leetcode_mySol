// After robbing those houses on that street, the thief has found himself a new 
// place for his thievery so that he will not get too much attention. This time, 
// all houses at this place are arranged in a circle. That means the first house 
// is the neighbor of the last one. Meanwhile, the security system for these houses 
// remain the same as for those in the previous street.

// Given a list of non-negative integers representing the amount of money of each house, 
// determine the maximum amount of money you can rob tonight without alerting the police.


// 因为是个circle，所以两边DP， 第一次是从0到l-2, 第二次从1到l-1
public class Solution {
    public int rob(int[] nums) {
        int max1 = 0, max2 = 0;
        int l = nums.length;
        if(l == 0) return 0;
        if(l == 1) return nums[0];
        if(l == 2) return Math.max(nums[0], nums[1]);
        int[] ret1 = new int[l];
        int[] ret2 = new int[l];

        ret1[0] = nums[0];
        ret1[1] = nums[1] > ret1[0] ? nums[1] : ret1[0];
        for (int i = 2; i < l-1; i++) {
        	ret1[i] = Math.max(ret1[i-1], ret1[i-2]+nums[i]);
        }
        ret2[1] = nums[1];
        ret2[2] = nums[2] > ret2[1] ? nums[2] : ret2[1];
        for (int i = 3; i < l; i++) {
        	ret2[i] = Math.max(ret2[i-1], ret2[i-2]+nums[i]);
        }
        return Math.max(ret1[l-2], ret2[l-1]);
    }
}