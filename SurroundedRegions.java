

import java.util.LinkedList;
import java.util.Queue;

/** 
 * Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
 *
 * A region is captured by flipping all 'O's into 'X's in that surrounded region .
 *
 * For example,
 *
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * After running your function, the board should be:
 *
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 */
//	任何一个O如果它没有被X包围，那么它一定和最外面的边界的某个O是连通的。
//	反过来，也就是可以从最外面那层所有的O开始用广度搜索所有没有被包围的O。
	//先扫描四条边 然后 碰到O 就先改成'I' 再bfs扫描上下左右(防止bfs因为没改O重复扫描)  就是visited
	//入queue 然后  然后把本O的上下左右查一遍 碰到O就先改成'I' 再bfs扫描上下左右
    //然后第二次 再扫描MATRIX 把 O变成 x 把I变成O
	

public class SurroundedRegions {
	public void solve(char[][] board) {
		int row = board.length;
		if (row == 0)
			return;
		int col = board[0].length;
		Queue<Integer> qx = new LinkedList<Integer>(); // x坐标
		Queue<Integer> qy = new LinkedList<Integer>(); // y坐标

		for (int i = 0; i < row; i++) {  // col 0
			if(board[i][0] == 'O'){
				qx.add(i);
				qy.add(0);
			}
		}
		for (int i = 0; i < row; i++) {  // col col-1
			if(board[i][col-1] == 'O'){
				qx.add(i);
				qy.add(col-1);
			}
		}
		for (int j = 0; j < col; j++) {  // row 0
			if(board[0][j] == 'O'){
				qx.add(0);
				qy.add(j);
			}
		}
		for (int j = 0; j < col; j++) {  // row row-1
			if(board[row-1][j] == 'O'){
				qx.add(row-1);
				qy.add(j);
			}
		}

		while (!qx.isEmpty()) {
			//从最外面那层所有的O开始用广度搜索所有没有被包围的O
			int x = qx.remove();
			int y = qy.remove();
			board[x][y] = '@';  // 等于标记为visited
			if (x > 0 && board[x - 1][y] == 'O') {  // left
				qx.add(x - 1);
				qy.add(y);
			}
			if (x < row - 1 && board[x + 1][y] == 'O') { // right
				qx.add(x + 1);
				qy.add(y);
			}
			if (y > 0 && board[x][y - 1] == 'O') {  // upper
				qx.add(x);
				qy.add(y - 1);
			}
			if (y < col - 1 && board[x][y + 1] == 'O') { // down 
				qx.add(x);
				qy.add(y + 1);
			}
		}
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) { 
				if (board[i][j] == '@') {  // 表示是不属于 X surrounded 的O，继续保持O
					board[i][j] = 'O';  
				} else {  // 表示原来就是X的，已经那些X surrounded 的O改成X
					board[i][j] = 'X';
				}
			}
		}
	}
}


// 01/08/15
public class Solution {
    public void solve(char[][] board) {
        int w = board.length;
        if(w == 0) return;
        int l = board[0].length;
        Queue<Integer> qx = new LinkedList<Integer>();
        Queue<Integer> qy = new LinkedList<Integer>();
        for(int i = 0; i < l; i++){
            if(board[0][i] == 'O'){
                // board[0][i] = 'I';
                qx.add(0);
                qy.add(i);
            }
            if(board[w-1][i] == 'O'){
                // board[w-1][i] = 'I';
                qx.add(w-1);
                qy.add(i);
            }
        }
        for(int i = 0; i < w; i++){
            if(board[i][0] == 'O'){
                // board[i][0] = 'I';
                qx.add(i);
                qy.add(0);
            }
            if(board[i][l-1] == 'O'){
                // board[i][l-1] = 'I';
                qx.add(i);
                qy.add(l-1);
            }
        }
        
        while(!qx.isEmpty()){
            int x = qx.poll();
            int y = qy.poll();
            board[x][y] = 'I';
            if(x > 0 && board[x-1][y] == 'O'){
                //board[x-1][y] = 'I';
                qx.add(x-1);
                qy.add(y);
            }
            if(x < w-1 && board[x+1][y] == 'O'){
                // board[x+1][y] = 'I';
                qx.add(x+1);
                qy.add(y);
            }
            if(y > 0 && board[x][y-1] == 'O'){
                // board[x][y-1] = 'I';
                qx.add(x);
                qy.add(y-1);
            }
            if(y < l-1 && board[x][y+1] == 'O'){
                // board[x][y+1] = 'I';
                qx.add(x);
                qy.add(y+1);
            }
        }
        
        for(int i = 0; i < w; i++){
            for(int j = 0; j < l; j++){
                if(board[i][j] == 'I'){
                    board[i][j] = 'O';
                }
                else if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
            }
        }
    }
}

// 03/03/15
public class Solution {
    public void solve(char[][] board) {
        int w = board.length;
        if(w == 0) return;
        int l = board[0].length;
        Queue<Integer> qx = new LinkedList<>();
        Queue<Integer> qy = new LinkedList<>();
        for(int i = 0; i < l; i++){
            if(board[0][i] == 'O'){
                qx.add(0);
                qy.add(i);
            }
            if(board[w-1][i] == 'O'){
                qx.add(w-1);
                qy.add(i);
            }
        }
        for(int i = 1; i < w-1; i++){
            if(board[i][0] == 'O'){
                qx.add(i);
                qy.add(0);
            }
            if(board[i][l-1] == 'O'){
                qx.add(i);
                qy.add(l-1);
            }
        }
        while(!qx.isEmpty()){
            int x = qx.remove();
            int y = qy.remove();
            board[x][y] = 'I';
            if(x > 0 && board[x-1][y] == 'O'){
                qx.add(x-1);
                qy.add(y);
            }
            if(x < w-1 && board[x+1][y] == 'O'){
                qx.add(x+1);
                qy.add(y);
            }
            if(y > 0 && board[x][y-1] == 'O'){
                qx.add(x);
                qy.add(y-1);
            }
            if(y < l-1 && board[x][y+1] == 'O'){
                qx.add(x);
                qy.add(y+1);
            }
        }
        for(int i = 0; i < w; i++){
            for(int j = 0; j < l; j++){
                if(board[i][j] == 'I'){  // 不是被X包围的slot
                    board[i][j] = 'O';
                }
                else if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
            }
        }
    }
}