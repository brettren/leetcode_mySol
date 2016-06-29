// Given a sorted integer array without duplicates, return the summary of its ranges.

// For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].


// 检查是否连续，同时要保持一个local的start数值
// 如果不连续就要检查是只需要插入一个数，还是一个range
// 最后要检查最后一个值，插入最后一个range/single
public class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> ret = new ArrayList<>();
        int l = nums.length;
        if(l == 0) return ret;
        int s = nums[0];
        for (int i = 1; i < l; i++) {
        	if(nums[i] == nums[i-1] + 1) continue;
        	if(nums[i-1] == s){
        		StringBuffer sb = new StringBuffer();
        		sb.append(String.valueOf(s));
        		ret.add(sb.toString());
        		s = nums[i];
        	}
        	else{
        		StringBuffer sb = new StringBuffer();
        		sb.append(String.valueOf(s) + "->" + String.valueOf(nums[i-1]));
	    		ret.add(sb.toString());
	    		s = nums[i];
        	}
        }
        if(nums[l-1] == s){
    		StringBuffer sb = new StringBuffer();
    		sb.append(String.valueOf(s));
    		ret.add(sb.toString());
    	}
    	else{
    		StringBuffer sb = new StringBuffer();
    		sb.append(String.valueOf(s) + "->" + String.valueOf(nums[l-1]));
    		ret.add(sb.toString());
    	}
        return ret;
    }
}