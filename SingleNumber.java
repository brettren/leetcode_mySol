

/**
 * Given an array of integers, every element appears twice except for one. Find
 * that single one.
 * 
 * Note: Your algorithm should have a linear runtime complexity. Could you
 * implement it without using extra memory?
 */

public class SingleNumber {
	public int singleNumber(int[] A) {
		int ret = A[0];
		for (int i = 1; i < A.length; i++) {
			ret ^= A[i]; // xor是两个相同的相消，A^A = 0, 最后剩下的就是唯一的那个
		}
		return ret;
	}
}

// 01/04/15
public class Solution {
    public int singleNumber(int[] A) {
        int l = A.length;
        if(l == 0) return 0;
        int ret = 0;
        for(int i = 0; i < l; i++){
            ret ^= A[i];
        }
        return ret;
    }
}