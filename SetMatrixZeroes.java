

/**
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0.
 * Do it in place.
 * 
 * Follow up: 
 * Did you use extra space? 
 * A straight forward solution using O(mn) space is probably a bad idea. 
 * A simple improvement uses O(m + n) space, but still not the best solution. 
 * Could you devise a constant space solution?
 */
//这题和CC150上1_7类似 但是cc 150的解法是用了 rows 和columns2个数组来储存0的坐标，比如matrix[i][j]=0  columns[i]++ rows[j]++,
//然后再次遍历这个matrix,当if(columns[i]!=0||rows[j]!=0)这个matrix[i][j]=0， leetcode上要求不需求辅助空间  
//那么我们就把matrix的第0行和第0列当做columns[] rows[] 来存0 (注意 如果原有x行0列是0 那么x行还是要是0 反之亦然)
//请注意当matrix只有1行1列的情况

// 就需要巧妙地利用原来矩阵的空间，这里利用第一行和第一列保存额外信息：
// 1 先判断号第一行和第一列是否需要全部置零
// 2 有任何一个该行或者该列的元素为零那么这个第一行或者第一列的元素必然是零，就保存这个零，
// 最后用来判断整个矩阵的这一行或者这一列是否需要置零。
// 3 最后再根据前面判断，决定是否把这个第一行和第一列置零。

public class SetMatrixZeroes {
	public void setZeroes(int[][] matrix) {
		int rowlen = matrix.length;
		int collen = matrix[0].length;
        if (rowlen == 0 || collen == 0) return;  
        boolean row_flag = false, col_flag = false;  
  
        for (int i = 0; i < rowlen && !col_flag; i++)  
            if (matrix[i][0]==0) col_flag = true;   // col[0]要置0
        for (int i = 0; i < collen && !row_flag; i++)  
            if (matrix[0][i]==0) row_flag = true;   // row[0]要置0
  
        for (int i = 1; i < rowlen; i++)  
            for (int j = 1; j < collen; j++)  
                if (matrix[i][j]==0) matrix[i][0] = matrix[0][j] = 0;  
  
        for (int i = 1; i < rowlen; i++)  // 遍历除了row 0其他的rows
            if (matrix[i][0]==0)   
                for (int j = 1; j < collen; j++)  
                    matrix[i][j] = 0;  
        for (int j = 1; j < collen; j++)  // 遍历除了col 0其他的cols
            if (matrix[0][j]==0)  
                for (int i = 1; i < rowlen; i++)  
                    matrix[i][j] = 0;  
  
        if (row_flag)  
            for (int i = 0; i < collen; i++)  
                matrix[0][i] = 0;  
        if (col_flag)  
            for (int i = 0; i < rowlen; i++)  
                matrix[i][0] = 0; 
    } 
}




// |------------- n
// |    
// |
// m    

// update 01/02/15
public class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        if(m == 0) return;
        int n = matrix[0].length;
        if(n == 0) return;
        
        boolean row_0_flag = false;
        boolean col_0_flag = false;
        
        for(int i = 0; i < m; i++){
            if(matrix[i][0] == 0) 
                col_0_flag = true;
        }
        
        for(int i = 0; i < n; i++){
            if(matrix[0][i] == 0) 
                row_0_flag = true;
        }
        
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(matrix[i][j] == 0)
                    matrix[0][j] = matrix[i][0] = 0;
            }
        }
        
        for(int i = 1; i < m; i++){  // 这里要小心是从1开始，不要更改第一个col
            if(matrix[i][0] == 0){
                for(int j = 1; j < n; j++) // for one row
                    matrix[i][j] = 0;
            }
        }
        
        for(int j = 1; j < n; j++){  // 这里要小心是从1开始，不要更改第一个row
            if(matrix[0][j] == 0){
                for(int i = 1; i < m; i++) // for one col
                    matrix[i][j] = 0;
            }
        }
        
        if(row_0_flag){
            for(int j = 0; j < n; j++){  // for row 0
                matrix[0][j] = 0;
            }
        }
        
        if(col_0_flag){
            for(int i = 0; i < m; i++){  // for col 0
                matrix[i][0] = 0;
            }
        }
    }
}

// 03/21/15
// 先设置flag，看row0和col0是否有0
// 然后对剩下的sub matrix[1][1]遍历，如果是0就把对应的row和col标记为0
// 根据row0和col0来把matrix[1][1] 的rows和cols全部改为0
// 最后看两个flag把 row 0和col 0 全部标记为0
public class Solution {
    public void setZeroes(int[][] matrix) {
        int w = matrix.length;
        int l = matrix[0].length;
        boolean row0 = false;
        boolean col0 = false;
        for(int i = 0; i < l; i++){
            if(matrix[0][i] == 0){
                row0 = true;
                break;
            }
        }
        for(int i = 0; i < w; i++){
            if(matrix[i][0] == 0){
                col0 = true;
                break;
            }
        }
        for(int i = 1; i < w; i++){
            for(int j = 1; j < l; j++){
                if(matrix[i][j] == 0){
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        for(int i = 1; i < l; i++){
            if(matrix[0][i] == 0){
                for(int j = 1; j < w; j++){
                    matrix[j][i] = 0;
                }   
            }
        }   
        for(int i = 1; i < w; i++){
            if(matrix[i][0] == 0){
                for(int j = 1; j < l; j++){
                    matrix[i][j] = 0;
                }   
            }
        }          
        if(row0){
            for(int i = 0; i < l; i++){
                matrix[0][i] = 0;
            }
        }   
        if(col0){
            for(int i = 0; i < w; i++){
                matrix[i][0] = 0;
            }
        }    
    }
}













