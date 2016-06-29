/**
 * Implement int sqrt(int x).
 * 
 * Compute and return the square root of x.
 */
//求x的平方根
//两分法。原因就是：要求x的平方根，在假定x为正整数的情况下，那么它的结果一定在0到x之间，而二分查找法一定不会漏掉。
public class Sqrtx {
	public int sqrt(int x) {
        if (x == 0 || x == 1) return x;
        long low = 1;
        long high = x;
        long mid = 0;
        while (low <= high) {
            mid = (low + high) / 2;
            if (mid * mid <= x && (mid + 1) * (mid + 1) > x) {  // 找到临界点
            	break;
            }
            if (mid * mid > x) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return new Long(mid).intValue();
    }
}

// 03/12/15
// 要注意high + low overflow的可能，所以用long来表示
// 用divide search来搜索
public class Solution {
    public int sqrt(int x) {
        if(x == 0 || x == 1) return x;
        long low = 1;
        long high = x;  
        long mid = 0;
        while(low <= high){
            mid = (high + low)/2;
            if(mid * mid <= x && (mid+1)*(mid+1) > x) break; 
            if(mid * mid > x) high = mid - 1;
            else low = mid + 1;
        }
        return new Long(mid).intValue();
    }
}