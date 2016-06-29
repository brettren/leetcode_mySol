import java.util.Arrays;

/**
 * Given an array S of n integers, find three integers in S such that the sum is
 * closest to a given number, target. Return the sum of the three integers. You
 * may assume that each input would have exactly one solution.
 * 
 * For example, given array S = {-1 2 1 -4}, and target = 1. The sum that is
 * closest to the target is 2. (-1 + 2 + 1 = 2).
 * 
 */
public class ThreeSumClosest {
	public int threeSumClosest(int[] num, int target) {
		int dis = Integer.MAX_VALUE;
		int ret = 0;
		Arrays.sort(num);
		int length = num.length;
		for (int i = 0; i < length - 2; i++) {
			int j = i + 1;
			int k = length - 1;
			while (j < k) {
				if (j > i + 1 && num[j] == num[j - 1]) {
					j++;
					continue;
				}
				if (k < length - 1 && num[k] == num[k + 1]) {
					k--;
					continue;
				}
				int sum = num[i] + num[j] + num[k];
				int minus = sum - target;
				int d = Math.abs(minus); // 找到差值最小的
				if (d < dis) {
					dis = d;  // 记下到这个loop为止的最小difference
					ret = sum; // 记下这个closet sum
				}
				if (minus == 0)
					return target;  // 如果minus等于0，assume只有一个sol，那就是这个sum了
				if (minus < 0) {
					j++;
				} else {
					k--;
				}
			}
		}
		return ret;
	}
}

// 01/28/15
public class Solution {
    public int threeSumClosest(int[] num, int target) {
        int l = num.length;
        if(l <= 2) return 0;
        Arrays.sort(num);
        int s = 0;
        int m = 1;
        int e = l-1;
        int ret = num[s]+num[m]+num[e];  // 先初始化ret
        while(s < l-2){  // 这里重复的数也没关系
            while(m < e){
                int sum = num[s]+num[m]+num[e];
                if(sum == target) return target;
                else if(sum > target){
                    if(sum-target < Math.abs(ret-target)){
                        ret = sum;
                    }
                    e--;
                }
                else{
                    if(target-sum < Math.abs(ret-target)){
                        ret = sum;
                    }
                    m++;
                }
            }
            s++;
            m = s+1;
            e = l-1;
        }
        return ret;
    }
}

// 03/15/15
// 先sort，用三指针，左边的用来loop，右边两个用来往内部收缩
// 因为要找到closet，所以先初始化ret，这里不用考虑重复的问题，因为答案只有一个，重复了答案也不会变的
public class Solution {
    public int threeSumClosest(int[] num, int target) {
        int l = num.length;
        if(l < 3) return -1;
        Arrays.sort(num);
        int ret = num[0]+num[1]+num[l-1];
        int s = 0, e = l-1;
        while(s < l-2){
            int m = s+1;
            e = l-1;
            while(m < e){
                int sum = num[s]+num[m]+num[e];
                if(sum == target) return target;
                if(Math.abs(sum-target) < Math.abs(ret-target)){
                    ret = sum;
                }
                if(sum > target){
                    e--;
                }
                else{
                    m++;
                }
            }
            s++;
        }
        return ret;
    }
}
