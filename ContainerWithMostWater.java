

/**
 * Given n non-negative integers a1, a2, ..., an, where each represents a point
 * at coordinate (i, ai). n vertical lines are drawn such that the two endpoints
 * of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis
 * forms a container, such that the container contains the most water.
 * 
 * Note: You may not slant the container.
 */

public class ContainerWithMostWater {
	public int maxArea(int[] height) {
		int len = height.length, low = 0, high = len - 1;  // 分别从最左和最右开始
		int maxArea = 0;
		while (low < high) {
			maxArea = Math.max(maxArea, (high - low) * Math.min(height[low], height[high])); // 木桶原理取最短边
			if (height[low] < height[high]) {
				low++;  // 保持短的那条边update
			} else {
				high--;
			}
		}
		return maxArea;
	}
}

//01/08/15
public class Solution {
    public int maxArea(int[] height) {
        int ret = 0;
        int l = height.length;
        int s = 0;
        if(l == 0) return 0;
        l = l-1;
        while(s < l){
            int area = (l-s) * Math.min(height[s], height[l]);
            if(area > ret) ret = area;
            if(height[s] > height[l]) l--;
            else s++;
        }
        return ret;
    }
}

//01/26/15
public class Solution {
    public int maxArea(int[] height) {
        int l = height.length;
        if(l == 0 || l == 1) return 0;
        int s = 0, e = l-1;
        int ret = 0;
        while(s < e){
            int tmp = (e-s) * Math.min(height[s], height[e]);
            ret = Math.max(ret, tmp);
            if(height[s] < height[e]) s++;
            else e--;
        }
        return ret;
    }
}

// 03/21/15
// 双指针往内靠拢，height低的那个指针往内靠拢
public class Solution {
    public int maxArea(int[] height) {
        int l = height.length;
        if(l <= 1) return 0;
        int ret = 0;
        int s = 0, e = l-1;
        while(s < e){
            int h = Math.min(height[s], height[e]);
            ret = Math.max(ret, h*(e-s));
            if(height[s] > height[e]) e--;
            else s++;
        }
        return ret;
    }
}