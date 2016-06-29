/**
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 * 
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * 
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen 
 * and an empty space respectively.
 * 
 * For example,
 * 
 * There exist two distinct solutions to the 4-queens puzzle:
 * 
 * [
 *  [".Q..",  // Solution 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // Solution 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 *  ]
 */

import java.util.ArrayList;

public class NQueens {
	public ArrayList<String[]> solveNQueens(int n) {
		ArrayList<String[]> ret = new ArrayList<String[]>();
		if (n == 0)
			return ret;
		StringBuffer line = new StringBuffer();
		for (int i = 0; i < n; i++) {
			line.append('.');
		}
		StringBuffer[] sol = new StringBuffer[n];
		for (int i = 0; i < n; i++) {
			sol[i] = new StringBuffer(line.toString());
		}
		// sol，形成n*n由'.'填满的board
		boolean[] cols = new boolean[n];
		int[] row = new int[n];
		findSolutions(n, 0, ret, sol, row, cols);
		return ret;
	}

	private void findSolutions(int n, int start, ArrayList<String[]> ret,
			StringBuffer[] sol, int[] row, boolean[] cols) {
		if (start == n) {  // 超出范围
			String[] newSol = new String[n];
			for (int i = 0; i < n; i++) {
				newSol[i] = sol[i].toString(); // 把sol每一行都copy过来
			}
			ret.add(newSol);
		} else {
			for (int i = 0; i < n; i++) {  // 尝试这一行的每个位置
				if (cols[i])
					continue;
				boolean ok = true;
				for (int j = 0; j < start; j++) {
					if (Math.abs(start - j) == Math.abs(i - row[j])) {  // 检查两个Queen是否在diagonal上 vertical == horizontal
						ok = false;
						break;
					}
				}
				if (ok) {
					cols[i] = true;  // 表示这个col有Queen了
					sol[start].setCharAt(i, 'Q');
					row[start] = i;  // 表示第start行的row，Queen的位置在i

					findSolutions(n, start + 1, ret, sol, row, cols);

					row[start] = 0;
					sol[start].setCharAt(i, '.');
					cols[i] = false;
				}
			}
		}
	}
}


// 01/06/15
public class Solution {
    public List<String[]> solveNQueens(int n) {
        List<String[]> ret = new ArrayList<String[]>();
        if(n == 0) return ret;
        StringBuffer[] sol = new StringBuffer[n];
        StringBuffer tmp = new StringBuffer();
        for(int i = 0; i < n; i++){
            tmp.append('.');
        }
        for(int i = 0; i < n; i++){
            sol[i] = new StringBuffer(tmp.toString());
        }
        boolean[] col = new boolean[n]; 
        int[] rowpos = new int[n];
        helper(ret, sol, col, rowpos, n, 0);
        return ret;
    }
    
    private void helper(List<String[]> ret, StringBuffer[] sol, boolean[] col, int[] rowpos, int n, int level){
        if(level >= n){
            String[] newsol = new String[n];
            for(int i = 0; i < n; i++){
                newsol[i] = sol[i].toString();
            }
            ret.add(newsol);
            return;
        }
        
        for(int i = 0; i < n; i++){
            if(col[i] == true) continue;
            boolean exist = true;
            for(int r = 0; r < level; r++){
                if(Math.abs(rowpos[r] - i) == level - r){
                    exist = false;
                    break;
                }
            }
            if(exist){
                col[i] = true;
                rowpos[level] = i;
                sol[level].setCharAt(i, 'Q');
                helper(ret, sol, col, rowpos, n, level+1);
                col[i] = false;
                rowpos[level] = 0;
                sol[level].setCharAt(i, '.');
            }
        }
    }
}

// 03/14/15
// 建立一个StringBuffer[] 数组，可以在递归程序里不断修改和恢复
// 如果到底了说明sol成立，再把数组里的值搬到新建的String[]，放入ret
// 检查当前col有没有已经存在Q，检查上面的每个Q是否在是对角线上
public class Solution {
    public List<String[]> solveNQueens(int n) {
        List<String[]> ret = new ArrayList<String[]>();
        if(n == 0) return ret;
        StringBuffer[] tmp = new StringBuffer[n];
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < n; i++){
           sb.append('.');
        }
        for(int i = 0; i < n; i++){
            tmp[i] = new StringBuffer(sb);
        }
        boolean[] col = new boolean[n];
        int[] row = new int[n];
        helper(ret, tmp, col, row, n, 0);
        return ret;
    }
    
    private void helper(List<String[]> ret, StringBuffer[] tmp, boolean[] col, int[] row, int n, int idx){
        if(idx >= n){
            String[] newtmp = new String[n];  // 到这里能够确定tmp是个sol，所以要深复制过来，所以是String[]
            for(int i = 0; i < n; i++){
                newtmp[i] = tmp[i].toString();
            }
            ret.add(newtmp);
            return;
        }
        
        for(int i = 0; i < n; i++){
            if(col[i] == true) continue;
            boolean valid = true;
            for(int j = 0; j < idx; j++){
                if(idx - j == Math.abs(i-row[j])){
                    valid = false;
                    break;
                }
            }
            if(valid == false) continue;
            col[i] = true;
            row[idx] = i;
            tmp[idx].setCharAt(i, 'Q');
            helper(ret, tmp, col, row, n, idx+1);
            col[i] = false;
            row[idx] = 0;
            tmp[idx].setCharAt(i, '.');
        }
    }
}