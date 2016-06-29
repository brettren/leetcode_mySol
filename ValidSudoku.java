

//Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
//
//The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
//A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated. 
//检查一个数独是否valid 可以没有填满
//数独是一个9x9的矩阵里面有9个小块 要求每行都有不重复的1~9 这题可以缺但是不可以重
//每列 也是不重复1~9  这题可以缺但是不可以重
//每个小矩阵里面也是 1~9 可以缺不能重

public class ValidSudoku {
//如果不编程这题怎么做   检查行 检查列 检查每个小矩阵就行了

	public boolean isValidSudoku(char[][] board) {
    	return isValidRow(board)&&isValidColumn(board)&&isValidBox(board);     
 	}
	
	//检查 这个c是不是已经在flag里出现 如果重复出现返回false，相当于hash的作用
	private boolean markFlag(boolean[] flag,char c){
		if(c=='.'){
			return true;
		}
		int index= c-'1';
		if(flag[index]){  //如果之前已经出现过这个数了
			return false;
		}else{
			flag[index]=true; // 相应的数字为true，表示已经出现过了
			return true;
		}
	}
 
   //横着遍历矩阵 每次检查每行充不重复
	private  boolean  isValidRow(char[][] board) {
		for(int i=0;i<9;i++){
			boolean[] flag= new boolean[9];  // 也可以每一行创建一个hashset  
			for(int j=0;j<9;j++){
				if(!markFlag(flag,board[i][j])){
					return false;
				}
			}
		}
		return true;
	}
	 //竖着遍历矩阵 每次检查每列重不重复
	private boolean isValidColumn(char[][] board){
		for(int i=0;i<9;i++){
			boolean[] flag= new boolean[9];
			for(int j=0;j<9;j++){    //改成j i即可
				if(!markFlag(flag,board[j][i])){
					return false;
				}
			}
		}
		return true;
	}
	//遍历矩阵 检查所有小九宫格充不重复
	private boolean isValidBox(char[][] board){
		for(int i=0;i<9;i+=3){
			for(int j=0;j<9;j+=3){  // i*j  有九个小九宫格
				boolean[] flag=new boolean[9];
				for (int m = 0; m<3;m++) {
					for(int n = 0;n<3;n++){
						if(!markFlag(flag, board[i+m][j+n])){
							return false;
						}
					}
					
				}
			}
		}
		return true;
	}
}



// 01/11/15
public class Solution {
    public boolean isValidSudoku(char[][] board) {
        HashSet<Character> set = null;
        for(int i = 0; i < 9; i++){  // each row
            set = new HashSet<Character>();
            for(int j = 0; j < 9; j++){
                if(board[i][j] == '.') continue;
                if(set.contains(board[i][j])) return false;
                set.add(board[i][j]);
            }
        }
        
        for(int i = 0; i < 9; i++){  // each col
            set = new HashSet<Character>();
            for(int j = 0; j < 9; j++){
                if(board[j][i] == '.') continue;
                if(set.contains(board[j][i])) return false;
                set.add(board[j][i]);
            }
        }
        
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                set = new HashSet<Character>();
                for(int m = 0; m < 3; m++){
                    for(int n = 0; n < 3; n++){
                        int row = i*3+m;
                        int col = j*3+n;
                        if(board[row][col] == '.') continue;
                        if(set.contains(board[row][col])) return false;
                        set.add(board[row][col]);
                    }
                }    
            }    
        }
        return true;
    }
}

// 03/15/15
// 检查每个row，检查每个col，检查每个sub square
// 如果是'.'就跳过，否则就找到map对应的位置看是否已经存在
public class Solution {
    public boolean isValidSudoku(char[][] board) {
        for(int i = 0; i < 9; i++){
            boolean[] map = new boolean[9];
            for(int j = 0; j < 9; j++){
                if(board[i][j] == '.') continue;
                int d = board[i][j] - '1';
                if(map[d] == true) return false;
                else map[d] = true;
            }
        }
        for(int i = 0; i < 9; i++){
            boolean[] map = new boolean[9];
            for(int j = 0; j < 9; j++){
                if(board[j][i] == '.') continue;
                int d = board[j][i] - '1';
                if(map[d] == true) return false;
                else map[d] = true;
            }
        }
        for(int i = 0; i < 9; i += 3){
            for(int j = 0; j < 9; j += 3){
                boolean[] map = new boolean[9];
                for(int m = 0; m < 3; m++){
                    for(int n = 0; n < 3; n++){
                        if(board[i+m][j+n] == '.') continue;
                        int d = board[i+m][j+n] - '1';
                        if(map[d] == true) return false;
                        else map[d] = true;
                    }
                }
            }
        }
        return true;
    }
}