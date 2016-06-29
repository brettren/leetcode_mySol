/**
 * Given an index k, return the kth row of the Pascal's triangle.
 * 
 * For example, given k = 3, Return [1,3,3,1].
 * 
 * Note:
 * 
 * Could you optimize your algorithm to use only O(k) extra space?
 */

 // * [      
 // *        [1], 
 // *       [1,1], 
 // *      [1,2,1], 
 // *     [1,3,3,1], 
 // *    [1,4,6,4,1] 
 // * ]

import java.util.ArrayList;

public class PascalsTriangleII {
	public ArrayList<Integer> getRow(int rowIndex) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		int[] tmp = new int[rowIndex + 1];  // 作为buffer放上一个level的值
		for (int i = 0; i <= rowIndex; i++) {  // list里有 rowIndex+1 个element
			ret.add(1);  // 先扩容到length为rowIndex + 1
			tmp[i] = 1;
		}
		for (int k = 2; k <= rowIndex; k++) {
			for (int i = 1; i < k; i++) {
				ret.set(i, tmp[i - 1] + tmp[i]);  // 只需要改变中间区域[1, k-1]
			}
			for (int n = 1; n < k; n++) {
				tmp[n] = ret.get(n);
			}
		}
		return ret;
	}
}


// 03/20/15
// 因为只能用O(k) extra mem，所以用数组来表示上一个level的内容
// 每次循环都用上一个level的值int[]得到现在这个level的内容list，然后再把数组int[]更新为现在level的内容
// 如果不用额外的数组来存上一个level，只用一个list，在同一个loop中会覆盖掉上一个level内容
public class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> ret = new ArrayList<>();
        int[] tmp = new int[rowIndex+1];
        for(int i = 0; i <= rowIndex; i++){
            tmp[i] = 1;
            ret.add(1);
        }
        for(int i = 2; i <= rowIndex; i++){
            for(int j = 1; j <= i-1; j++){
                ret.set(j, tmp[j-1]+tmp[j]);
            }
            for(int j = 1; j <= i-1; j++){
                tmp[j] = ret.get(j);
            }
        }
        return ret;
    }
}
