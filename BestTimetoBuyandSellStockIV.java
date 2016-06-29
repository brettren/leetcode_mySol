// Say you have an array for which the ith element is the price of a given stock on day i.

// Design an algorithm to find the maximum profit. You may complete at most k transactions.

// 每天只能sell 或者 buy; sell后只能buy，不能连续sell或者buy

// DP 的optimal就是比较是否包括第i点
public class Solution {
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if (k >= len) return quickSolve(prices);

        int[][] t = new int[k + 1][len];  // 最多考虑k次transaction
        for (int i = 1; i <= k; i++) {
            int tmpMax =  -prices[0];  // 因为假定起始资金为0，先出手buy price[0]，这时候是负资产
            for (int j = 1; j < len; j++) {
                // 表示在j之前所有买入后所剩资金的max，加上prices[j]表示在j点sell
                t[i][j] = Math.max(t[i][j - 1], prices[j] + tmpMax);  // 加入在这个j点sell，总收入是price[j] + tmpMax
                // 减prices[j]表示需要在这个点buy，tmpMax表示买入后所剩资金max
                tmpMax =  Math.max(tmpMax, t[i - 1][j - 1] - prices[j]); 
            }//tmpMax means the maximum profit of just doing at most i-1 transactions, using at most first j-1 prices, and buying the stock at price[j] - this is used for the next loop.
            // 因为一天只能sell或buy，所以这里是i-1个交易，再buy当天的股票，所剩余额
        }
        return t[k][len - 1];
    }


    private int quickSolve(int[] prices) {
        int len = prices.length, profit = 0;
        for (int i = 1; i < len; i++)
            // as long as there is a price gap, we gain a profit.
            if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
        return profit;
    }
}