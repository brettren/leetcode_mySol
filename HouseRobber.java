// You are a professional robber planning to rob houses along a street. 
// Each house has a certain amount of money stashed, the only constraint 
// stopping you from robbing each of them is that adjacent houses have 
// security system connected and it will automatically contact the police 
// if two adjacent houses were broken into on the same night.

// Given a list of non-negative integers representing the amount of money 
// of each house, determine the maximum amount of money you can rob tonight 
// without alerting the police.

// 一个array houses，不能有两个相邻的house都被抢，每个house不同的money，求max money

// DP, 两个选择，第i家 rob OR not rob, ret[i] = Math.max(ret[i-2] + nums[i], ret[i-1]);

public class Solution {
    public int rob(int[] nums) {
        int l = nums.length;
        if(l == 0) return 0;
        if(l == 1) return nums[0];
        int[] ret = new int[l];
        ret[0] = nums[0];
        ret[1] = Math.max(ret[0], nums[1]);
        for (int i = 2; i < l; i++) {
        	ret[i] = Math.max(ret[i-2] + nums[i], ret[i-1]);
        }
        return ret[l-1];
    }
}