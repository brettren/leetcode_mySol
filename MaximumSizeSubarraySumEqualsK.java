// Given an array nums and a target value k, find the maximum length of a subarray that sums to k. 
// If there isn't one, return 0 instead.

// Example 1:
// Given nums = [1, -1, 5, -2, 3], k = 3,
// return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)

// Example 2:
// Given nums = [-2, -1, 2, 1], k = 1,
// return 2. (because the subarray [-1, 2] sums to 1 and is the longest)

// Follow Up:
// Can you do it in O(n) time?


//The HashMap stores the sum of all elements before index i as key, and i as value. 
//For each i, check not only the current sum but also (currentSum - previousSum) to see 
// if there is any that equals k, and update max length.
public class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
    	Map<Integer, Integer> map = new HashMap<>();
    	int sum = 0;
    	int max = 0;
    	for(int i = 0; i < nums.length; i++){
    		sum += nums[i];
    		if(sum == k){
    			max = i+1;
    		} 
    		else{
    			int diff = sum-k;
    			if(map.containsKey(diff)){
    				max = Math.max(max, i - map.get(diff));
    			}
    		}
    		if(!map.containsKey(sum)){
    			map.put(sum, i);
    		}
    	}
    	return max;
 	}
 }