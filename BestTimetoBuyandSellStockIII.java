

/**
 * Say you have an array for which the ith element is the price of a given stock
 * on day i.
 * 
 * Design an algorithm to find the maximum profit. You may complete at most two
 * transactions.
 * 
 * Note: You may not engage in multiple transactions at the same time (ie, you
 * must sell the stock before you buy again).
 */
//建2个数组 forward[i]是[0,i]区间的最大利润(和第一题一样更新min price 然后看 prices[i]-min price 赚多少)
//backword[i]是[i,n]区间最大利润(从n--下来 更新max price 最高卖价 然后看 max price- prices[i]赚多少)
//for(int i=0;i<prices.length;i++){ //max=Math.max(max,forward[i]+backward[i]);}
// //这样不管i在那里 都是 0~i 交易一次 i~n 交易一次 不会重复。

public class BestTimetoBuyandSellStockIII {
	public int maxProfit(int[] prices) {
		int length = prices.length;
		if (length == 0) return 0;
		int profit = 0;
		int lowest = prices[0];
		int[] left = new int[length];
		int[] right = new int[length];

		
		for (int i = 1; i < length; i++) {
			if (prices[i] < lowest) {
				lowest = prices[i];  // 更新lowest
			} else if (prices[i] - lowest > profit) {  // 从左往右
				profit = prices[i] - lowest;  // 当前price减去左边找到的lowest
			}
			left[i] = profit;  // 分别表示[0,1],[0,2],[0,3]... 各区间内的max profit
		}

		profit = 0;
		int topest = prices[length - 1];
		for (int j = length - 2; j >= 0; j--) {  // 从右往左
			if (prices[j] > topest) {
				topest = prices[j];
			} else if (topest - prices[j] > profit) {
				profit = topest - prices[j];  // 右边找到的topest减去当前price
			}
			right[j] = profit;  // 分别表示[n-1,n],[n-2,n],[n-3,n]... 各区间内的max profit
		}

		profit = 0;
		for (int k = 0; k < length; k++) {
			int p = left[k] + right[k];
			if (p > profit) {
				profit = p; // 找到left profit和right profit之和的max
			}
		}
		return profit;
	}
}

// 02/07/15
public class Solution {
    public int maxProfit(int[] prices) {
        int l = prices.length;
        if(l == 0) return 0;
        int ret = 0;
        int[] forward = new int[l];
        int[] backward = new int[l];
        int min = prices[0];
        for(int i = 1; i < l; i++){
            forward[i] = Math.max(forward[i-1], prices[i]-min); // 表示[0,i]的max profit
            min = Math.min(min, prices[i]);
        }
        int max = prices[l-1];
        for(int i = l-2; i > 0; i--){
            backward[i] = Math.max(backward[i+1], max-prices[i]); // 表示[i,l-1]的max profit
            max = Math.max(max, prices[i]);
        }
        for(int i = 0; i < l; i++){
            ret = Math.max(ret, forward[i]+backward[i]);
        }
        return ret;
    }
}

// 03/12/15
// DP方法，维持两个max，一个是global，表示买入卖出后当前的max profit
// 一个是local max，表示在某个点买入的情况下的剩余max 净值

// 每一个row的遍历，都是为了确定在多一次买入卖出的机会下，合适的买入卖出点，得到最大profit
public class Solution {
    public int maxProfit(int[] prices) {
        int l = prices.length;
        if(l < 2) return 0;
        int[][] ret = new int[3][l];
        for(int i = 1; i < 3; i++){
            int max = -prices[0];  // 每次都先买入price[0]
            for(int j = 1; j < l; j++){
                ret[i][j] = Math.max(ret[i][j-1], max+prices[j]);  // 如果在当前价格卖出，比较收益
                max = Math.max(max, ret[i-1][j-1]-prices[j]); // 这个local max维持的是买入的情况下max profit
            }
        }
        return ret[2][l-1];
    }
}