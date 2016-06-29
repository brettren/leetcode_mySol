// There are a row of n houses, each house can be painted with one of the k colors. 
// The cost of painting each house with a certain color is different. 
// You have to paint all the houses such that no two adjacent houses have the same color.

// The cost of painting each house with a certain color is represented by a n x k cost matrix. 
// For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost 
// of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

// Note:
// All costs are positive integers.

// Follow up:
// Could you solve it in O(nk) runtime?


// if we want to paint nth house color 0, we need to compare 
// all the costs till n-1th house which is not painted color 0
// if the smallest till n-1th is the same color with color 0, we need to find the second smallest color
public class Solution {
    public int minCostII(int[][] costs) {
    	if(costs == null || costs.length == 0) return 0;
    	int n = costs.length;
    	int k = costs[0].length;
    	int min1 = -1, min2 = -1;  // keep two variable to record the prev 1st and 2nd smallest costs
    	for(int i = 0; i < n; i++){
    		int last1 = min1, last2 = min2;
    		min1 = -1; min2 = -1;

    		for(int j = 0; j < k; j++){
    			if(j != last1){  // only if diffrent color compared to smallest prev costs
    				costs[i][j] += last1 < 0 ? 0 : cost[i][last1];
    			}
    			else{   // if same with the prev smallest, only can choose the second smallest
    				costs[i][j] += last2 < 0 ? 0 : cost[i][last2];
    			}

    			// at the same time, keep recording the 1st and 2nd smallest costs till this house
    			if(min1 == -1 || costs[i][j] < costs[i][min1]){
	    			min2 = min1;
	    			min1 = i;
	    		}
	    		else if(min2 == -1 || costs[i][j] < costs[i][min2]){
	    			min2 = i;
	    		}
    		}
    	}
    	return costs[n-1][min1];
    }
}