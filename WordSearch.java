

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 * 
 * The word can be constructed from letters of sequentially adjacent cell, where
 * "adjacent" cells are those horizontally or vertically neighboring. The same
 * letter cell may not be used more than once.
 * 
 * For example, Given board =
 * 
 * [ ["ABCE"], 
 *   ["SFCS"], 
 *   ["ADEE"] 
 * ] 
 * 
 * word = "ABCCED", -> returns true, 
 * word = "SEE", -> returns true, 
 * word = "ABCB", -> returns false.
 */
//recursive 遍历数组 碰到对的char了递归上下左右 看看是不是对的char
//DFS经典题 每个词是一个最深
public class WordSearch {
	public boolean exist(char[][] board, String word) {
		int height = board.length;
		int width = board[0].length;
		boolean[][] visited = new boolean[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (search(board, visited, i, j, word, 0)) {  // 遍历每个块，尝试找到word的首字母开始的path
					return true;
				}
			}
		}
		return false;
	}

	private boolean search(char[][] board, boolean[][] visited, int x, int y,
			String word, int index) {
		if (word.charAt(index) != board[x][y]) {  // 先判断是不是下一个char
			return false; 
		}
		if (index == word.length() - 1) {
			return true;  // 说明已经找到word的最后一个了，搜索完成
		}

		int height = board.length;
		int width = board[0].length;
		visited[x][y] = true;  // true表示先assume是path的一部分

		if (x > 0 && !visited[x - 1][y]   // 防止把之前标记为path一块的重复被探索
				&& search(board, visited, x - 1, y, word, index + 1)) {  // up
			return true;
		}

		if (x < height - 1 && !visited[x + 1][y]
				&& search(board, visited, x + 1, y, word, index + 1)) { // down
			return true;
		}

		if (y > 0 && !visited[x][y - 1]
				&& search(board, visited, x, y - 1, word, index + 1)) { // left
			return true;
		}

		if (y < width - 1 && !visited[x][y + 1]
				&& search(board, visited, x, y + 1, word, index + 1)) { // right
			return true;
		}

		visited[x][y] = false;  // 如果上面四个方向都没找到，那这块就不再是path一部分

		return false;
	}
}



// 01/04/15
public class Solution {
    public boolean exist(char[][] board, String word) {
        int w = board.length;
        if(w == 0) return false;
        int l = board[0].length;
        if(l == 0) return false;
        
        boolean[][] visited = new boolean[w][l];
        for(int i = 0; i < w; i++){
            for(int j = 0; j < l; j++){
                if(helper(visited, board, word, i, j, 0)) return true;
            }
        }
        return false;
    }
    
    private boolean helper(boolean[][] visited, char[][] board, String word, int i, int j, int idx){
        if(word.charAt(idx) != board[i][j]) return false;
        int length = word.length();
        if(idx == length - 1) return true;
        int w = board.length;
        int l = board[0].length;
        visited[i][j] = true;
        if(i > 0 && !visited[i-1][j] && helper(visited, board, word, i-1, j, idx+1)) return true;
        if(i < w-1 && !visited[i+1][j] && helper(visited, board, word, i+1, j, idx+1)) return true;
        if(j > 0 && !visited[i][j-1] && helper(visited, board, word, i, j-1, idx+1)) return true;
        if(j < l-1 && !visited[i][j+1] && helper(visited, board, word, i, j+1, idx+1)) return true;
        visited[i][j] = false;
        return false;
    }
}

// 03/08/15
// 尝试每一个格子当做word的开头进行搜索，DFS，每次都上下左右四个方向搜索，并且用visited来记录path
public class Solution {
    public boolean exist(char[][] board, String word) {
        int w = board.length;
        if(w == 0) return false;
        int l = board[0].length;
        int lw = word.length();
        if(lw == 0) return false;
        boolean[][] visited = new boolean[w][l];
        for(int i = 0; i < w; i++){
            for(int j = 0; j < l; j++){
                if(search(board, word, visited, i, j, 0)){
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean search(char[][] board, String word, boolean[][] visited, int i, int j, int idx){
        if(visited[i][j]) return false;
        if(board[i][j] != word.charAt(idx)) return false; // 先检查对应的char是否是word里的char
        if(idx == word.length()-1) return true; // 当最后一位也match时，说明已经找到，直接返回true
        visited[i][j] = true;
        int w = board.length;
        int l = board[0].length;
        if(i > 0 && search(board, word, visited, i-1, j, idx+1)){
            return true;
        }
        if(i < w-1 && search(board, word, visited, i+1, j, idx+1)){
            return true;
        }
        if(j > 0 && search(board, word, visited, i, j-1, idx+1)){
            return true;
        }
        if(j < l-1 && search(board, word, visited, i, j+1, idx+1)){
            return true;
        }
        visited[i][j] = false;
        return false;
    }
}