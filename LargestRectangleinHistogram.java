import java.util.Arrays;
import java.util.Stack;

/**
 * Given n non-negative integers representing the histogram's bar height where
 * the width of each bar is 1, find the area of largest rectangle in the
 * histogram.
 * 
 * Above is a histogram where width of each bar is 1, given height =
 * [2,1,5,6,2,3].
 * 
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 * 
 * For example, Given height = [2,1,5,6,2,3], return 10.
 */
//比方说有一个直方图 height = [2,1,5,6,2,3],里面每个数组就是 直方图里每个数字的高度
//然后要在直方图里求最大矩形的高度  比方说在 215623里 最大的矩形就是56 2个柱子组成的  2×5=10
//用 window sliding的做法来做 类似maximum sub array
//右边一直向右遍历 然后每遍历一次看看左边的情况，用一个栈辅助(栈里面存的是index) 栈里面要维护严格递增 
//eg:比方说 数组是1432  下标是0123

// O(n)
public class LargestRectangleinHistogram {
	public int largestRectangleArea(int[] height) {
		Stack<Integer> stack = new Stack<Integer>();  //栈里面存的是index
		int i = 0;
		int maxArea = 0;
		int[] h = new int[height.length + 1];  // 要多留个位置，最后一位是0，不然就while到底退出了
		h = Arrays.copyOf(height, height.length + 1);
		while (i < h.length) {
			if (stack.isEmpty() || h[stack.peek()] <= h[i]) {  // 如果往右height是递增的，就push  栈里面index对应height要维护严格递增
				stack.push(i++);  // 只有这里才i++  ！！！！
			} else {  // h[stack.peek()] > h[i]
				int t = stack.pop();
				maxArea = Math.max( maxArea, h[t] * (stack.isEmpty() ? i : i - (stack.peek() + 1)) );  
				                               // 如果是empty，说明一直都是递减，前面的都比h[t]高
			}
		}
		return maxArea;
	}
}


// 03/11/15
// 用stack来维持index, stack里的height一定是递增的，如果下一个还是递增就push，否则就pop上一个height
// 计算宽度时，如果是empty说明前面的height都比当前的高，直接取i，否则就peek上一个height比当前小的idx，length = i-peek()-1
// 当前面的area都计算完了，再把这个height的idx放入stack

// 当出现height下降的情况，就说明高的那个不可能cover后面的rectangular了，所以计算当前最大area
public class Solution {
    public int largestRectangleArea(int[] height) {
        int l = height.length;
        if(l == 0) return 0;
        Stack<Integer> st = new Stack<Integer>();
        int max = 0;
        int[] h = new int[l+1];
        
        for(int i = 0; i < l; i++){
            h[i] = height[i];
        }
        
        for(int i = 0; i <= l; ){
            if(st.isEmpty() || h[i] >= h[st.peek()]){
                st.push(i++);
            }
            else{
                int tmp = st.pop();
                int length = st.isEmpty() ? i : i-st.peek()-1;  // empty说明前面的都是descending order
                max = Math.max(max, h[tmp] * length);
            }
        }
        return max;
    }
}

//
public class Solution {
  public int largest(int[] array) {
    // write your solution here
    int l = array.length;
    if(l == 0) return 0;
    Stack<Integer> st = new Stack<>();
    int[] arr = new int[l+1];
    arr = Arrays.copyOf(array, l+1);
    int ret = 0;
    for(int i = 0; i <= l; ){
      if(st.isEmpty() || arr[st.peek()] <= arr[i]){
        st.push(i++);
      }
      else{
        int index = st.pop();
        int h = arr[index];
        int left = st.isEmpty() ? 0 : st.peek()+1;
        ret = Math.max(ret, h*(i-left));
      }
    }
    return ret;
  }
}