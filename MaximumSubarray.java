/**
 * Find the contiguous subarray within an array (containing at least one number)
 * which has the largest sum.
 * 
 * For example, given the array [−2,1,−3,4,−1,2,1,−5,4], the contiguous subarray
 * [4,−1,2,1] has the largest sum = 6.
 * 
 * click to show more practice.
 * 
 * More practice: If you have figured out the O(n) solution, try coding another
 * solution using the divide and conquer approach, which is more subtle.
 */

public class MaximumSubarray {
	public int maxSubArray(int[] A) {
		int max = Integer.MIN_VALUE;
		int sum = 0;
		for (int i = 0; i < A.length; i++) {
			sum += A[i];
			if (sum > max) {
				max = sum;
			}
			if (sum < 0) {
				sum = 0;
			}
		}
		return max;
	}
}

// 01/05/15

// 这里要注意ret可能为负值的情况，所以要取最大的负值
public class Solution {
    public int maxSubArray(int[] A) {
        int l = A.length;
        if(l == 0) return 0;
        int ret = Integer.MIN_VALUE;
        int tmp = 0;
        for(int i = 0; i < l; i++){
            tmp += A[i];
            ret = Math.max(ret, tmp); // 注意是更新ret在前面
            if(tmp < 0) tmp = 0;  // 一旦是负值就清零
        }
        return ret;
    }
}

// 03/22/15
// 这里local先加上A[i]，再和ret比较，local的意义在于一段连续的sequence并且保证sum >= 0
public class Solution {
    public int maxSubArray(int[] A) {
        int l = A.length;
        int ret = A[0];
        int max = (A[0]>=0)?A[0]:0;  // max 要保证是非负数，一旦负值就清零
        for(int i = 1; i < l; i++){
            max = max+A[i];  // 先加上当前的值
            ret = Math.max(ret, max);  // 可能ret也是负值，所以要先更新ret
            if(max < 0) max = 0;
        }
        return ret;
    }
}