// Write a program to solve a Sudoku puzzle by filling the empty cells.

// Empty cells are indicated by the character '.'.


public class SudokuSolver {
	public void solveSudoku(char[][] board) {  
	    if(board == null || board.length != 9 || board[0].length !=9)  
	        return;  
	    helper(board,0,0);  
	}  

	private boolean helper(char[][] board, int i, int j) {  
	    if(j>=9) return helper(board,i+1,0);  // 换到下一个row
	    if(i==9) return true;  
	    if(board[i][j]=='.') {  
	        for(int k=1;k<=9;k++) {  
	            board[i][j] = (char)(k+'0');  // 注意数字转ASCII
	            if(isValid(board,i,j)) {  
	                if(helper(board,i,j+1))  
	                    return true;  
	            }  
	            board[i][j] = '.';  // 如果这次loop不成功，改回'.'
	        }  
	    }  
	    else {  // board原有的数字，跳过
	        return helper(board,i,j+1);   
	    }  
	    return false;  
	}  

	private boolean isValid(char[][] board, int i, int j) {  
	    for(int k=0;k<9;k++) {  
	        if(k!=j && board[i][k]==board[i][j])  // 检查row
	            return false;  
	    }  
	    for(int k=0;k<9;k++) {  
	        if(k!=i && board[k][j]==board[i][j])  // 检查col
	            return false;  
	    }          
	    int boxRowOffset = (i / 3)*3;
		int boxColOffset = (j / 3)*3;  // 找到该点所属的小九宫格起始坐标
		for (int k = 0; k < 3; ++k) // box
		    for (int m = 0; m < 3; ++m)
				if ((boxRowOffset+k!=i || boxColOffset+m!=j) && board[i][j] == board[boxRowOffset+k][boxColOffset+m])
			    	return false;
	    return true;  
	}  
}


// 03/09/15
// 使用递归，从(0,0)开始递归程序，每个格子如果不是'.'就跳到下一格；如果是'.'就从1试到9，同时检查
// 检查row，检查col，还有sub square，不需要map来检查，只需要看其他的格子有没有和当前格子重复的值
// 找到sub square的起始点 i/3*3
public class Solution {
    public void solveSudoku(char[][] board) {
        if(board == null) return;
        helper(board, 0, 0);
    }
    
    public boolean helper(char[][]board, int i, int j){
        if(i == 9) return true;  // 注意当换到第9行就说明已经超出界限了
        if(j == 9) return helper(board, i+1, 0);
        if(board[i][j] != '.') return helper(board, i, j+1);
        for(int k = 1; k <= 9; k++){
            board[i][j] = (char)(k +'0');
            if(check(board, i, j)){
                if(helper(board, i, j+1)){
                    return true;
                }
            }
        }
        board[i][j] = '.';
        return false;
    }
    
    public boolean check(char[][]board, int i, int j){
        for(int k = 0; k < 9; k++){
            if(i != k && board[i][j] == board[k][j]) return false;
        }
        for(int k = 0; k < 9; k++){
            if(j != k && board[i][j] == board[i][k]) return false;
        }
        int r = i/3 * 3;
        int c = j/3 * 3;
        for(int k = 0; k < 3; k++){
            for(int m = 0; m < 3; m++){ 
               if((r+k != i || c+m != j) && board[i][j] == board[r+k][c+m])  // 这里的或括号一定要加
                    return false;
            }
        }
        return true;
    }
}