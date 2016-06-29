

/**
 * You are climbing a stair case. It takes n steps to reach to the top.
 * 
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can
 * you climb to the top?
 */

public class ClimbingStairs {
	public int climbStairs(int n) {
		int[] arr = new int[n + 1];
		arr[0] = 1;
		arr[1] = 1;
		for (int i = 2; i <= n; i++) {
			arr[i] = arr[i - 1] + arr[i - 2];  // arr[2] = arr[1] + arr[0]
		}
		return arr[n];
	}
}

// 01/25/15
public class Solution {
    public int climbStairs(int n) {
        if(n == 0) return 1;
        int[] ret = new int[n+1];
        ret[0] = 1;
        ret[1] = 1;
        for(int i = 2; i <= n; i++){
            ret[i] = ret[i-1]+ret[i-2];
        }
        return ret[n];
    }
}