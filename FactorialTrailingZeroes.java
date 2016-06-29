// Given an integer n, return the number of trailing zeroes in n!.

// Note: Your solution should be in logarithmic time complexity.

// 03/15/15
// 先是n/5, 然后 n/25, n/125 ...
public class Solution {
    public int trailingZeroes(int n) {
        int ret = 0;
        long base = 5;  // avoid overflow  可能n = Integer.MAX_VALUE, 这样就会无限循环
        while(n >= base){
            ret += n/base;
            base *= 5;
        }
        return ret;
    }
}