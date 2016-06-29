// Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num 
// calculate the number of 1's in their binary representation and return them as an array.

// An easy recurrence for this problem is f[i] = f[i / 2] + i % 2.
public class Solution {
    public int[] countBits(int num) {
	    int[] f = new int[num + 1];
	    for (int i=1; i<=num; i++) f[i] = f[i >> 1] + (i & 1);
	    return f;
	}
}