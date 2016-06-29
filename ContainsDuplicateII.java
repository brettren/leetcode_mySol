// Given an array of integers and an integer k, find out whether there there 
// are two distinct indices i and j in the array such that nums[i] = nums[j] 
// and the difference between i and j is at most k.

// 因为是按序遍历的，所以用hashmap来记录nums[i]和最新的index，每当发现dup的时候都和上一个index 比较diff
public class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int l = nums.length;
        if(l == 0) return false;
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < l; i++){
        	if(map.containsKey(nums[i])){
        		int lastIdx = map.get(nums[i]);
        		if(i - lastIdx <= k) return true;
        		else map.put(nums[i], i);
        	}
        	else{
        		map.put(nums[i], i);
        	}
        }
        return false;
    }
}