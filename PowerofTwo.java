
// Given an integer, write a function to determine if it is a power of two.

// 1000 & 0111 = 0
// 1100 & 1011 = 1000
public class Solution {
    public boolean isPowerOfTwo(int n) {
        if(n == 0) return false;
        long t = (long)n;
        t = t & (t-1);
        return t == 0;
    }
}