/**
 * Follow up for N-Queens problem.
 * 
 * Now, instead outputting board configurations, return the total number of distinct solutions.
 *
 */

public class NQueensII {
	public int totalNQueens(int n) {
		if (n == 0)
			return 0;
		boolean[][] sol = new boolean[n][n];
		boolean[] cols = new boolean[n];
		int[] row = new int[n];
		int[] count = new int[1];
		findSolutions(n, 0, sol, row, cols, count);
		return count[0];
	}

	private void findSolutions(int n, int start, boolean[][] sol, int[] row,
			boolean[] cols, int[] count) {
		if (start == n) {
			count[0]++;  // 多一个sol
		} else {
			for (int i = 0; i < n; i++) {
				if (cols[i])
					continue;
				boolean ok = true;
				for (int j = 0; j < start; j++) {
					if (Math.abs(start - j) == Math.abs(i - row[j])) {
						ok = false;
						break;
					}
				}
				if (ok) {
					cols[i] = true;
					sol[start][i] = true;
					row[start] = i;

					findSolutions(n, start + 1, sol, row, cols, count);

					row[start] = 0;
					sol[start][i] = false;
					cols[i] = false;
				}
			}
		}
	}
}


// 01/05/15
public class Solution {
    public int totalNQueens(int n) {
        boolean[] col = new boolean[n];
        int[] rowpos = new int[n];
        int[] ret = new int[1];
        helper(col, rowpos, ret, n, 0);
        return ret[0];
    }
    
    private void helper(boolean[] col, int[] rowpos, int[] ret, int n, int level){
        if(level >= n){
            ret[0]++;
            return;
        }
        for(int i = 0; i < n; i++){
            if(col[i]) continue;
            boolean exist = true;
            for(int r = 0; r < level; r++){
                if(Math.abs(rowpos[r]-i) == level - r){
                    exist = false;
                    break;
                }
            }
            if(exist){
                col[i] = true;
                rowpos[level] = i;
                helper(col, rowpos, ret, n, level+1);
                col[i] = false;
                rowpos[level] = 0;
            }
        }
    }
}

// 01/24/15
public class Solution {
    public int totalNQueens(int n) {
        if(n == 0) return 0;
        int[] ret = new int[1];
        boolean[] col = new boolean[n];
        int[] row = new int[n];
        helper(ret, col, row, n, 0);
        return ret[0];
    }
    
    private void helper(int[] ret, boolean[] col, int[] row, int n, int idx){
        if(idx == n) {
            ret[0]++;
            return;
        }
        for(int i = 0; i < n; i++){
            if(col[i] == true) continue;
            boolean valid = true;
            for(int j = 0; j < idx; j++){
                int x = Math.abs(row[j] - i);
                int y = idx - j;
                if(x == y) {
                    valid = false; // 一旦发现不符的就说明这个位置不能放，break
                    break;
                }
            }
            if(valid == false) continue;
            col[i] = true;
            row[idx] = i;
            helper(ret, col, row, n, idx+1);
            // 不要忘记递归后重置
            col[i] = false;
            row[idx] = -1;
        }
    }
}