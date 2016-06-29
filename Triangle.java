

import java.util.ArrayList;

/**
 * Given a triangle, find the minimum path sum from top to bottom. 
 * 
 * Each step you may move to adjacent numbers on the row below. 
 * 
 * For example, given the following triangle 
 * [   
 *     [2], 
 *    [3,4], 
 *   [6,5,7], 
 *  [4,1,8,3] 
 * ]
 * 
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 * 
 * Note: 
 * 
 * Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 */

// bottom up

// 如果可以更改triangle内容，可以直接在每个level记录bottom up累计的max sum
public class Triangle {
	public int minimumTotal(List<List<Integer>> triangle) {
		int n = triangle.size() - 1;  // 一共几个level
		int[] path = new int[triangle.size()];
		for (int i = 0; i < triangle.get(n).size(); i++) {
			path[i] = triangle.get(n).get(i); // 从bottom开始  [4,1,8,3] 
		}
		for (int level = triangle.size() - 2; level >= 0; level--) {  // 从倒数第二层开始
			for (int j = 0; j < triangle.get(level).size(); j++) {  // 根据下面一层triangle.get(i + 1)来得到这一层
				path[j] = triangle.get(level).get(j)
						+ Math.min(path[j], path[j + 1]);  // 从下面层选min
			}
		}
		return path[0];
	}
}

// 01/28/15
public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int l = triangle.size();
        if(l == 0) return 0;
        int[] num = new int[l];
        for(int i = 0; i < l; i++){
            num[i] = triangle.get(l-1).get(i);  // 先放入最下面一层的数据
        }
        for(int i = l-2; i >= 0; i--){
            for(int j = 0; j <= i;j++){
                num[j] = Math.min(num[j], num[j+1])+triangle.get(i).get(j);  //在往上递推
            }
        }
        return num[0];
    }
}

// 03/01/15
public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int l = triangle.size();
        if(l == 0) return 0;
        int[] ret = new int[l];
        for(int i = 0; i < l; i++){
            ret[i] = triangle.get(l-1).get(i);
        }
        l--;
        while(l > 0){
            for(int i = 0; i < l; i++){
                ret[i] = Math.min(ret[i], ret[i+1]) + triangle.get(l-1).get(i);
            }
            l--;
        }
        return ret[0];
    }
}

// 03/15/15
// 创建一个array，把最下面那层level的值给array初始化，然后比较下面那层相接的两个值，取min再加上当前层的值
// 最后ret[0]就是root的最终min
public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int l = triangle.size();
        if(l == 0) return 0;
        int[] ret = new int[l];
        for(int j = 0; j < l; j++){
            ret[j] = triangle.get(l-1).get(j);
        }
        for(int i = l-2; i >= 0; i--){
            for(int j = 0; j <= i; j++){
                ret[j] = Math.min(ret[j], ret[j+1]) + triangle.get(i).get(j);
            }
        }
        return ret[0];
    }
}