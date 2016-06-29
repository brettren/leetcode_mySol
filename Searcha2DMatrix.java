

/** 
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 *
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 * For example,
 *
 * Consider the following matrix:
 *
 * [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * Given target = 3, return true.
 */

public class Searcha2DMatrix {
	public boolean searchMatrix(int[][] matrix, int target) {
		int length = matrix.length;
		if (length == 0)
			return false;
		int width = matrix[0].length;
		int low = 0;
		int high = width * length - 1;  // 二维转一维
		while (low <= high) {
			int mid = (low + high) / 2;
			int x = mid / width;
			int y = mid % width;
			if (matrix[x][y] == target)
				return true;
			else if (matrix[x][y] > target) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return false;
	}
}

// 01/07/15
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int w = matrix.length;
        if(w == 0) return false;
        int l = matrix[0].length;
        int s = 0;
        int e = w*l-1;
        while(s <= e){
            int mid = (s+e)/2;
            int i = mid/l;
            int j = mid%l;
            if(matrix[i][j] < target){
                s = mid+1;
            }
            else if(matrix[i][j] > target){
                e = mid-1;
            }
            else{
                return true;
            }
        }
        return false;
    }
}


// 03/21/15
// 这里就是用二分法，把二维数组当做一维数组，因为是ascending order的一维数组
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int w = matrix.length;
        if(w == 0) return false;
        int l = matrix[0].length;
        int s = 0;
        int e = w*l-1;
        while(s <= e){
            int mid = (s+e)/2;
            int row = mid/l;
            int col = mid%l;
            if(matrix[row][col] == target) return true;
            else if(matrix[row][col] > target) e = mid-1;
            else s = mid+1;
        }
        return false;
    }
}