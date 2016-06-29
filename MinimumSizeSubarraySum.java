// Given an array of n positive integers and a positive integer s, 
// find the minimal length of a subarray of which the sum ≥ s. 
// If there isn't one, return 0 instead.

// For example, given the array [2,3,1,2,4,3] and s = 7,
// the subarray [4,3] has the minimal length under the problem constraint.

// 这里用到了sliding window的思路，j不断往后延伸，当发现local >= s时，开始从i缩小window
// 每次缩小window都更新ret
// 最后检查是否存在window，不存在就返回0
public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
    	int l = nums.length;
    	if(l == 0) return 0;
        int ret = Integer.MAX_VALUE;
        int i = 0, j = 0;
        int local = 0;
        while(j < l){
        	local += nums[j];
        	if(local >= s){
        		while(local - nums[i] >= s){
        			local -= nums[i];
        			i++;
        		}
        		ret = Math.min(ret, j-i+1);
        	}
        	j++;
        }
        return ret == Integer.MAX_VALUE ? 0 : ret;
    }
}