// Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
// An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
// You may assume all four edges of the grid are all surrounded by water.

// Example 1:

// 11110
// 11010
// 11000
// 00000
// Answer: 1

// Example 2:

// 11000
// 11000
// 00100
// 00011
// Answer: 3

// 用DFS更简洁方便，这里是用涂料填充的类比，当发现'1'就不断扩展，每次search完一块，就等于发现了一个island, ret++

public class Solution {

	public static int w;
    public static int l;

    public int numIslands(char[][] grid) {
    	w = grid.length;
        if(w == 0) return 0;
        l = grid[0].length;
        int ret = 0;
        for(int i = 0; i < w; i++){
        	for (int j = 0; j < l; j++) {
        		if(grid[i][j] == '1'){
        			search(grid, i, j);
        			ret++;
        		}
        	}
        }
        return ret;
    }

    public void search(char[][] grid, int i, int j){

    	if(i < 0 || i >= w) return;
    	if(j < 0 || j >= l) return;

    	if(grid[i][j] == '1'){
    		grid[i][j] = '2';
    		search(grid, i+1, j);
    		search(grid, i-1, j);
    		search(grid, i, j+1);
    		search(grid, i, j-1);
    	}
    }
}












