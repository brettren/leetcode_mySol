// There are a row of n houses, each house can be painted with one of the three colors: 
// red, blue or green. The cost of painting each house with a certain color is different. 
// You have to paint all the houses such that no two adjacent houses have the same color.

// The cost of painting each house with a certain color is represented by a n x 3 cost matrix. 
// For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the 
// cost of painting house 1 with color green, and so on… Find the minimum cost to paint all houses.

// Note:

// All costs are positive integers.


// 如果当前需要paint为red，那么look back上一个paint blue和green的costs
public class Solution {
    public int minCost(int[][] costs) {
    	int l = costs.length;
    	int[][] ret = new int[l][3];
    	for(int i = 0; i <= 2; i++){
    		ret[0][i] = costs[0][i];
    	}
    	for(int i = 1; i <= l; i++){
    		ret[i][0] = costs[i][0] + Math.min(ret[i-1][1], ret[i-1][2]); 
	    	ret[i][1] = costs[i][1] + Math.min(ret[i-1][0], ret[i-1][2]);  
	    	ret[i][2] = costs[i][2] + Math.min(ret[i-1][1], ret[i-1][2]);  
    	}
    	int min = Math.min(ret[l-1][0], ret[l-1][1]);
    	min = Math.min(min, ret[l-1][2]);
    	return min;
    }
}



