

/**
 * Say you have an array for which the ith element is the price of a given stock
 * on day i.
 * 
 * If you were only permitted to complete at most one transaction (ie, buy one
 * and sell one share of the stock), design an algorithm to find the maximum
 * profit.
 */
public class BestTimetoBuyandSellStock {
	public int maxProfit(int[] prices) {
		int lowest = 0;
		int maxProfit = 0;
		if (prices.length == 0) return 0;
		lowest = prices[0];
		for (int i = 0; i < prices.length; i++) {
			if (lowest > prices[i]) {
				lowest = prices[i];  // 不断更新lowest
			}
			maxProfit = Math.max(maxProfit, prices[i] - lowest);
		}
		return maxProfit;
	}
}

public class Solution {
    public int maxProfit(int[] prices) {
        int l = prices.length;
        if(l == 0 || l == 1) return 0;
        int leftlow = prices[0];
        int ret = 0;  // 注意如果是descending的order，说明没有解，那就返回0
        for(int i = 1; i < l; i++){
            int tmp = prices[i] - leftlow;
            if(tmp > 0){
                ret = Math.max(ret, tmp);
            }
            leftlow = Math.min(leftlow, prices[i]);
        }
        return ret;
    }
}