// Given a range [m, n] where 0 <= m <= n <= 2147483647, 
// return the bitwise AND of all numbers in this range, inclusive.

// For example, given the range [5, 7], you should return 4.

// 0101 & 0110 & 0111 = 0100 

// 5: 0101	0010  0001
// 7: 0111	0011  0001
// 0001 << 2 = 0100

// 消掉m和n右边不同的部分，直到相等，再left shift

public class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        int p = 0;
        while(m != n){
            m >>= 1;
            n >>= 1;
            p += 1;
    	}
    	return m << p;
    }
}