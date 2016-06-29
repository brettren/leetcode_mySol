// Find the contiguous subarray within an array (containing at least one number) which has the largest product.

// For example, given the array [2,3,-2,4],
// the contiguous subarray [2,3] has the largest product = 6.

// 这道题跟Maximum Subarray模型上和思路上都比较类似，还是用一维动态规划中的“局部最优和全局最优法”。
// 这里的区别是维护一个局部最优不足以求得后面的全局最优，这是由于乘法的性质不像加法那样，
// 累加结果只要是正的一定是递增，乘法中有可能现在看起来小的一个负数，后面跟另一个负数相乘就会得到最大的乘积。

// 我们只需要在维护一个局部最大的同时，在维护一个局部最小，这样如果下一个元素遇到负数时，
// 就有可能与这个最小相乘得到当前最大的乘积和，这也是利用乘法的性质得到的。

// 其实子数组乘积最大值的可能性为：累乘的最大值碰到了一个正数；或者，累乘的最小值（负数），碰到了一个负数。
// 所以每次要保存累乘的最大（正数）和最小值（负数）。同时还有一个选择起点的逻辑，如果之前的最大和最小值同当前元素相乘之后，
// 没有当前元素大（或小）那么当前元素就可作为新的起点。
public class Solution {
    public int maxProduct(int[] A) {
        if(A==null||A.length==0) {  
          return 0;  
        }  
        int maxProduct = A[0];  
        int max_temp   = A[0];  
        int min_temp   = A[0];  
          
        for(int i=1;i<A.length;i++) {  
            int a = A[i]*max_temp;  
            int b = A[i]*min_temp;  
            max_temp = Math.max(Math.max(a,b), A[i]);  // 如果A[i]*max_temp < A[i],那就放弃前面的max，直接取A[i]为最新的max
            min_temp = Math.min(Math.min(a,b), A[i]);  
            maxProduct = Math.max(maxProduct, max_temp);  
        }  
        return maxProduct;         
    }
}

// 01/16/15
public class Solution {
    public int maxProduct(int[] A) {
        int l = A.length;
        if(l == 0) return 0;
        int max = A[0];
        int min = A[0]; // 维持两个local， min和max
        int ret = A[0];
        for(int i = 1; i < l; i++){
            // 因为不知道A[i]是正还是负数所以都要先尝试乘一下，然后再和原来的max和min比较
            int a = max * A[i];
            int b = min * A[i];
            max = Math.max(Math.max(a,b), A[i]); // 两个选择，要么乘以A[i]，要么以A[i]当做新的起点
            min = Math.min(Math.min(a,b), A[i]);
            ret = Math.max(max, ret);
        }
        return ret;
    }
}