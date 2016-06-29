1 2 3
4 5 6
7 8 9
只有中间没有其他键的两个键才能相连，比如1可以连 2 4 5 6 8 但不能连 3 7 9
但是如果中间键被使用了，那就可以连，比如5已经被使用了，那1就可以连9
每个键只能用一次，给定一个长度L，求问有多少unique path with length L


// If we use the symmetry, we can only start from 1, 2 and 5 then multiply the results of 1 and 2 by 4

public class Solution {
	public int numberOfPatterns(int m, int n) {
	    boolean[][] keyboard = new boolean[3][3];
	    int ret=0;
	    for (int p=m;p<=n;p++){
	        for (int i=0;i<2;i++){
	            for (int j=0;j<2;j++){
	                if (j == 0 && i == 1) continue;  // just skip [1][0]
	                keyboard[i][j] = true;
	                helper(keyboard,p-1,i,j);
	                keyboard[i][j] = false;
	                ret += (i == 1 && j == 1)? res:4*res;
	                res=0;
	            }
	        }
	    }
	    return ret;
	}
	private void helper(boolean[][] keyboard,int left, int x, int y){
	    if (left == 0){
	        res++;
	        return;
	    }
	    for (int i=0;i<3;i++){
	        for (int j=0;j<3;j++){
	            if (keyboard[i][j] 
	                ||  (x==i && Math.abs(y-j)>1) && !keyboard[x][1]  // vertical
	                ||  (y==j && Math.abs(x-i)>1) && !keyboard[1][y]  // horizontal
	                ||  (x+y == i+j) && Math.abs(x-i) >1 && !keyboard[1][1] // diagnal
	                ||  (x-y == i-j) && Math.abs(x-i) >1 && !keyboard[1][1]
	                ||  (x == i && y == j)) {  // same
	                continue;
	            }
	            else{
	                keyboard[i][j] = true;
	                helper(keyboard,left-1,i,j);
	                keyboard[i][j] = false;
	            }
	        }
	    }
	}
}