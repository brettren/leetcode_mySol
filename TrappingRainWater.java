/**
 * Given n non-negative integers representing an elevation map where the width
 * of each bar is 1, compute how much water it is able to trap after raining.
 * 
 * For example,
 * 
 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 * 
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In
 * this case, 6 units of rain water (blue section) are being trapped. Thanks
 * Marcos for contributing this image!
 * 
 */

//因为每个column都是宽度1 那么面积=面积+(当前水面高度-当前column高度)*1 traverse一边即可
//所以关键是求当前i水面高度 ,i这个column的水面高度 是由离他最近的左右节点中最高的2个的短板决定的(看题目的图就知道了)
//所以建立2个数组 一个是从左到右 1~i时候的左边最高高度
//一个是从右向左从 length-2 到i的右边最高高度
//Math.min(left[i],right[i])就是当前水面高度

public class TrappingRainWater {
	public int trap(int A[]) {
		int n = A.length;
		if (n <= 2)
			return 0;

		int[] lmh = new int[n]; // 记录每个点左边的高度 left height
		lmh[0] = 0;
		int maxh = A[0];
		for (int i = 1; i < n; ++i) {
			lmh[i] = maxh;  // 左边的max height
			if (maxh < A[i])
				maxh = A[i]; // update max height on left
		}
		int trapped = 0;
		maxh = A[n - 1];
		for (int i = n - 2; i > 0; --i) {  // 从右数第二个开始
			int left = lmh[i];
			int right = maxh;
			int container = Math.min(left, right);
			if (container > A[i]) {
				trapped += container - A[i]; // 水面高度 - 底部的高度 就是这个点能够积蓄的水量
			}
			if (maxh < A[i])
				maxh = A[i]; // update max height on right
		}
		return trapped;
	}
}



// 01/11/15
public class Solution {
    public int trap(int[] A) {
        int l = A.length;
        if(l == 0) return 0;
        int ret = 0;
        int[] leftmax = new int[l];
        leftmax[0] = 0;
        int max = A[0];
        for(int i = 1; i < l; i++){
            leftmax[i] = max;
            max = Math.max(max, A[i]);
        }
        int[] rightmax = new int[l];
        rightmax[l-1] = 0;
        max = A[l-1];
        for(int i = l-1; i >= 0; i--){
            rightmax[i] = max;
            max = Math.max(max, A[i]);
            int h = Math.min(leftmax[i], rightmax[i]);
            int trap = (h > A[i]) ? h-A[i] : 0;
            ret += trap;
        }
        return ret;
    }
}

// 03/20/15
// 两个数组记录每个位置左边和右边的max height
// 然后再遍历每个位置(除掉左右两端的中间区域)，得到left和right的min，减去当前位置的height，就是这个位置能够trap的量
// 要先判断这个位置能不能trap，排除不能trap的情况，比如min小于当前height
public class Solution {
    public int trap(int[] A) {
        int l = A.length;
        if(l == 0) return 0;
        int[] leftmax = new int[l];
        leftmax[0] = 0;
        for(int i = 1; i < l; i++){
            leftmax[i] = Math.max(leftmax[i-1], A[i-1]);
        }
        int[] rightmax = new int[l];
        int ret = 0;
        rightmax[l-1] = 0;
        for(int i = l-2; i >= 0; i--){
            rightmax[i] = Math.max(rightmax[i+1], A[i+1]);
            int h = Math.min(leftmax[i], rightmax[i]);
            if(h - A[i] > 0) ret += h-A[i];
        }
        return ret;
    }
}

// 06/02/15
// 维护一个left[] 保持每一位左边的max height，然后第二遍从右边开始，根据每一位右边的max height和left[] 来得到trap
public class Solution {
    public int trap(int A[]) {
        int l = A.length;
        if(l <= 2) return 0;
        int[] left = new int[l];
        int max = A[0];
        for (int i = 1; i < l; i++) {
            left[i] = max;
            max = Math.max(max, A[i]);
        }
        int ret = 0;
        max = A[l-1];
        for(int i = l-2; i > 0; i--){
            int h = Math.min(left[i], max);
            if(h > A[i]) ret += h-A[i];
            max = Math.max(max, A[i]);
        }
        return ret;
    }
}