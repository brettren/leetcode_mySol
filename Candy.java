
/**
 * There are N children standing in a line. Each child is assigned a rating
 * value. You are giving candies to these children subjected to the following
 * requirements:
 * 
 * Each child must have at least one candy. Children with a higher rating get
 * more candies than their neighbors.
 * 
 * What is the minimum candies you must give?
 * 
 */
//那么比方说  345 5的小孩拿3个糖 4的小孩拿2个糖  1的小孩拿1个糖 
//有一点要注意，同样rating挨着的两个小孩的糖可以不一样的
//比如说rating是35553 糖是12121  rating 是34555 糖是 12311

//先把答案数组先全部填充成1.然后就是两头扫瞄，第一次从左到右，第二次从右到左，
//这样递增和递减的关系就刚好相反。每一个递增都加一，递减就不变。 
// 比方说 rating是 3455542 左到右第一次扫描后 1231111
//第二次扫描时候 因为 ratings[i-1]>ratings[i]&&count[i-1]<=count[i]
//所以         1231321
public class Candy {
	public int candy(int[] ratings) {
		int[] candy = new int[ratings.length];
		candy[0] = 1;
		// 从左边开始
		for (int i = 1; i < ratings.length; i++) {
			candy[i] = ratings[i] > ratings[i - 1] ? candy[i - 1] + 1 : 1; //每一个递增都加一，递减就只给一个
		}  // 表现递增的现象
		int totalCandy = candy[ratings.length - 1];
		// 从右边开始
		for (int i = ratings.length - 2; i >= 0; i--) {
			candy[i] = ( ratings[i] > ratings[i + 1] && candy[i + 1] + 1 > candy[i] ) ? candy[i + 1] + 1 : candy[i]; 
			// 要求最少candy满足条件
			totalCandy += candy[i];
		}
		return totalCandy;
	}
}

// 01/12/15
public class Solution {
    public int candy(int[] ratings) {
        int l = ratings.length;
        if(l == 0) return 0;
        int[] ret = new int[l];
        ret[0] = 1;
        for(int i = 1; i < l; i++){
            if(ratings[i] > ratings[i-1]){
                ret[i] = ret[i-1]+1;
            }
            else{
                ret[i] = 1;
            }
        }
        int sum = ret[l-1];
        for(int i = l-2; i >= 0; i--){
            if(ratings[i] > ratings[i+1] && ret[i] < ret[i+1]+1){
                ret[i] = ret[i+1]+1;
            }
            sum += ret[i];
        }
        return sum;
    }
}

// 03/08/15
// 先是从左往右，满足每个人对左边的要求; 然后从右往左，要求满足对右边的要求,同时累及candy总数
public class Solution {
    public int candy(int[] ratings) {
        int l = ratings.length;
        if(l <= 1) return l;
        int[] ret = new int[l];
        ret[0] = 1;
        for(int i = 1; i < l; i++){
            if(ratings[i] > ratings[i-1]){
                ret[i] = ret[i-1]+1;
            }
            else{
                ret[i] = 1;
            }
        }
        int sum = ret[l-1];
        for(int i = l-2; i >= 0; i--){
            if(ratings[i] > ratings[i+1]){
                ret[i] = Math.max(ret[i], ret[i+1]+1);
            }
            sum += ret[i];
        }
        return sum;
    }
}