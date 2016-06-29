

/**
 * Given an array of non-negative integers, you are initially positioned at the
 * first index of the array.
 * 
 * Each element in the array represents your maximum jump length at that
 * position.
 * 
 * Determine if you are able to reach the last index.
 * 
 * For example: A = [2,3,1,1,4], return true.
 * 
 * A = [3,2,1,0,4], return false.
 */

public class JumpGame {
	public boolean canJump(int[] A) {
		if (A.length <= 1)
			return true;
		int curMax = 0;
		int max = 0;
		for (int i = 0; i < A.length - 1; i++) {  // 遍历每个点，来更新能够到达的最远index，可以看做是cover range
			if (i > max)
				break;
			curMax = A[i] + i;  // 表示能够到达的最远index
			if (curMax > max) {
				max = curMax;
			}
			if (max >= A.length - 1)
				return true;
		}
		return false;
	}
}


// 03/15/15
// 这里只需要不断更新cover，看最后cover能不能到达末端就可以判断
// 注意如果当前遍历的位置已经大于cover，说明这个位置已经不可能到达，返回false
public class Solution {
    public boolean canJump(int[] A) {
        int l = A.length;
        int max = 0;
        for(int i = 0; i < l; i++){
            if(i > max) break;  // 注意加上这一行 因为max表示能够到达的最远地方
            if(max >= l-1) return true;
            max = Math.max(max, A[i]+i);
        }
        return cover >= l-1;
    }
}