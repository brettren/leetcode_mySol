

import java.util.Arrays;

/**
 * Implement next permutation, which rearranges numbers into the
 * lexicographically next greater permutation of numbers.
 * 
 * If such arrangement is not possible, it must rearrange it as the lowest
 * possible order (ie, sorted in ascending order).
 * 
 * The replacement must be in-place, do not allocate extra memory.
 * 
 * Here are some examples. 
 * 
 * Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * 1,2,3 -> 1,3,2
 * 3,2,1 -> 1,2,3
 * 1,1,5 -> 1,5,1
 */

public class NextPermutation {
	public void nextPermutation(int[] num) {
		int i1 = 0;
		int i2 = 0;
		int i = num.length - 1;
		int j = 0;
		// 从后往前，如果一直是递增，那么不可能存在swap两个digit后比原值大的情况，比如54，swap后45
		while (i > 0 && num[i - 1] >= num[i]) { // 1. 从后往前找falling edge，下降沿。eg “547532”， 4是下降沿。
			i--;
		}
		if (i == 0) {
			Arrays.sort(num); // 特殊情况是一开始index遍历到头都是递减的，那就直接reverse全array就行了
			return;
		} else {  // num[i - 1] < num[i]
			i1 = i - 1;
		}
		for (j = num.length - 1; j > i; j--) {
			if (num[j] > num[i1]) {  //从后往前遍历，找到第一个比num[i1]的内容大的num[j]
				break;
			}
		}
		i2 = j;  
		int temp = num[i1];
		num[i1] = num[i2];
		num[i2] = temp;
		Arrays.sort(num, i1 + 1, num.length);  // i1后面的数都按ascending order sort
	}
}

// 03/13/15
// 从后往前找，发现第一个falling点tmp，然后再从后往前找，找到第一个比falling点大的index，swap这两个数
// 然后对tmp后面的排序
// 4 6 5 3 2 (4 和 5 swap)
// 5 6 4 3 2 (6 4 3 2 排序)
// 5 2 3 4 6
public class Solution {
    public void nextPermutation(int[] num) {
        int l = num.length;
        if(l == 0) return;
        int i = l-1;
        for(; i > 0; i--){
            if(num[i-1] < num[i]) break;
        }
        if(i == 0){  // 如果没找到下降沿，说明没有下一个更大的，返回最小值
            Arrays.sort(num); 
            return;
        }
        int i1 = i-1;
        i = l-1;
        for(; i > 0; i--){
            if(num[i] > num[i1]) break;
        }
        int i2 = i;
        int t = num[i1];
        num[i1] = num[i2];
        num[i2] = t;
        Arrays.sort(num, i1+1, l);
    }
}

// 01/29/15
public class Solution {
    public void nextPermutation(int[] num) {
        int l = num.length;
        int i = l-2;
        for(; i >= 0; i--){
            if(num[i] < num[i+1]){
                break;
            }
        }
        if(i < 0){
            Arrays.sort(num);
            return;
        }
        int j = l-1;
        for(; j >= 0; j--){
            if(num[j] > num[i]){
                int tmp = num[j];
                num[j] = num[i];
                num[i] = tmp;
                break;
            }
        }
        Arrays.sort(num, i+1, l);
    }
}