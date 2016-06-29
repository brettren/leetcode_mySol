

import java.util.ArrayList;

/**
 * Given a matrix of m x n elements (m rows, n columns), return all elements of
 * the matrix in spiral order.
 * 
 * For example, Given the following matrix:
 * 
 * [ 
 *   [ 1, 2, 3 ],
 *   [ 4, 5, 6 ],
 *   [ 7, 8, 9 ]
 * ]
 * 
 * You should return [1,2,3,6,9,8,7,4,5].
 */

public class SpiralMatrix {
	public ArrayList<Integer> spiralOrder(int[][] matrix) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (matrix.length == 0)
			return list;
		int beginRow = 0, endRow = matrix.length - 1;  // row
		int beginCol = 0, endCol = matrix[0].length - 1;  // col
		while (true) {
			// 横着读取第一行（最外层上边）
			for (int i = beginCol; i <= endCol; i++) {
				list.add(matrix[beginRow][i]);
			}
			if (++beginRow > endRow)  // 向下一个row
				break;
			// 从第二行开始读最外层右边
			for (int i = beginRow; i <= endRow; i++) {
				list.add(matrix[i][endCol]);
			}
			if (beginCol > --endCol)  // 向左一个col
				break;
			// 最外层下边从右(倒数第二右)到左 输入
			for (int i = endCol; i >= beginCol; i--) {
				list.add(matrix[endRow][i]);
			}
			if (beginRow > --endRow)  // 向上一个row
				break;
			for (int i = endRow; i >= beginRow; i--) {
				list.add(matrix[i][beginCol]);
			}
			if (++beginCol > endCol)  // 向右一个col
				break;
		}
		return list;
	}
}


// 03/09/15
// 维持四个边界的idx，读完一条边就移除这条边，四条边不断往内部收缩，如果相对的两条边过了重合界限说明就读完了
public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ret = new ArrayList<Integer>();
        int w = matrix.length;
        if(w == 0) return ret;
        int l = matrix[0].length;
        int left = 0, right = l-1;
        int upper = 0, bottom = w-1;
        while(true){   
            for(int i = left; i <= right; i++){
                ret.add(matrix[upper][i]);
            }
            upper++;
            if(upper > bottom) break;  // 要注意一旦超出界限就break循环
            
            for(int i = upper; i <= bottom; i++){
                ret.add(matrix[i][right]);
            }
            right--;
            if(left > right) break;
            
            for(int i = right; i >= left; i--){
                ret.add(matrix[bottom][i]);
            }
            bottom--;
            if(upper > bottom) break;
            
            for(int i = bottom; i >= upper; i--){
                ret.add(matrix[i][left]);
            }
            left++;
            if(left > right) break;
        }
        return ret;
    }
}