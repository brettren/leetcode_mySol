// Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. 
// The algorithm should run in linear time and in O(1) space.

// 数组中至多可能会有2个出现次数超过 ⌊ n/3 ⌋ 的众数, 也可能不存在，或者只有一个数满足
// 用n1,n2来记录可能的两个sol，count1和count2来加减vote
public class Solution {
    public List<Integer> majorityElement(int[] nums) {
    	List<Integer> ret = new ArrayList<>();
    	int l = nums.length;
    	if(l == 0) return ret;

        int n1 = 0, n2 = 0;
        int count1 = 0, count2 = 0;
        for (int i = 0; i < l; i++) {
        	if(nums[i] == n1){
        		count1++;
        	}
        	else if(nums[i] == n2){
        		count2++;
        	}
        	else if(count1 == 0){
        		n1 = nums[i];
        		count1 = 1;
        	}
        	else if(count2 == 0){
        		n2 = nums[i];
        		count2 = 1;
        	}
        	else{
        		count1--;
        		count2--;
        	}
        }

        //满足要求的众数可能不存在，所以要有验证
        count1 = count2 = 0;
        for (int i = 0; i < l; i++) {
        	if(nums[i] == n1) count1++;
        	else if(nums[i] == n2) count2++;
        }

        if(count1 > l/3) ret.add(n1);
        if(count2 > l/3) ret.add(n2);
        return ret;
    }
}