

/**
 * Say you have an array for which the ith element is the price of a given stock
 * on day i.
 * 
 * Design an algorithm to find the maximum profit. You may complete as many
 * transactions as you like (ie, buy one and sell one share of the stock
 * multiple times). However, you may not engage in multiple transactions at the
 * same time (ie, you must sell the stock before you buy again).
 */
// 类似greedy algo
// 抓住每次rising的机会
public class BestTimetoBuyandSellStockII {
	public int maxProfit(int[] prices) {
		int profit = 0;
		for (int i = 1; i < prices.length; i++) {
			int d = prices[i] - prices[i - 1]; 
			if (d > 0) { // 只要是rising的情况都累加
				profit += d;
			}
		}
		return profit;
	}
}

// 01/24/15
public class Solution {
    public int maxProfit(int[] prices) {
        int l = prices.length;
        if(l == 0 || l == 1) return 0;
        int ret = 0;
        for(int i = 1; i < l; i++){
            if(prices[i-1] < prices[i]){
                ret += prices[i] - prices[i-1];
            }
        }
        return ret;
    }
}