// Given an array of n integers nums and a target, find the number of index triplets i, j, k 
// with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

// For example, given nums = [-2, 0, 1, 3], and target = 2.

// Return 2. Because there are two triplets which sums are less than 2:

// [-2, 0, 1]
// [-2, 0, 3]
// Follow up:
// Could you solve it in O(n^2) runtime?

// 如果sum >= target 则 j--.
// 比较关键的地方是, 如果sum < target 那麽 j - i 都是解: [j,k], [j,k-1], [j,k-2]...
public class Solution{
	public int threeSumSmaller(int[] nums, int target) {
		if(nums == null || nums.length < 3) return 0;
		Arrays.sort(nums);
		int l = nums.length;
		int i = 0;
		int ret = 0;
		while(i < l - 2){
			int j = i+1, k = l-1;
			while(j < k){
				if(nums[i]+nums[j]+nums[k] >= target){
					k--;
				}
				else{
					ret += k - j;
					j++;
				}
			}
			i++;
		}
		return ret;
	}
}
