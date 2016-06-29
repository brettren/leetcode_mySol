/**
 * Given an array of integers, every element appears three times except for one.
 * Find that single one.
 * 
 * Note:
 * 
 * Your algorithm should have a linear runtime complexity. Could you implement
 * it without using extra memory?
 * 
 */
//考虑全部用二进制表示，如果我们把 第 ith 个位置上所有数字的和对3取余，
//那么只会有两个结果 0 或 1 (根据题意，3个0或3个1相加余数都为0).  因此取余的结果就是那个 “Single Number”.

//即用二进制模拟三进制计算

// We know a number appears 3 times at most, so we need 2 bits to store that. 
// Now we have 4 state, 00, 01, 10 and 11, but we only need 3 of them.

// 00->01->10->00(0->1->2->3/0).
public int singleNumber(int[] A) {
    int ones = 0, twos = 0;
    for(int i = 0; i < A.length; i++){
        ones = (ones ^ A[i]) & ~twos; // 表示第0个 bit, 如果two是1，那就要置0   10->00
        twos = (twos ^ A[i]) & ~ones; // 表示第1个 bit, 如果one是1，那就要置0   00->01
    }
    return ones;
}

//时间复杂度O(32 * n), 空间复杂度O(1)
public class Singlenumber2 {
	 public int singleNumber(int[] A) {
		 int bit=0;
		 int result=0;
		 for(int i=0;i<32;i++){ //每次外循环 求得32位int里,1个位的总和
			 bit=0;
			for(int j=0;j<A.length;j++){
				if(((A[j]>>i)&1)==1){  //比方说外循环第一次的时候0 那么就不往右平移 然后和1比 看看是不是1
					bit++;             //外循环第二次的时候往右移1位然后比第二位。
				}
			} 
			 bit=bit%3; // 1 or 0
			 result=result|bit<<i;//每次外循环结束的时候就把bit取完余后的结果存到result里
		 }
		 return result;
	 } 
}

// 01/13/15
public class Solution {
    public int singleNumber(int[] A) {
        int l = A.length;
        if(l == 0) return 0;
        int bit = 0;
        int ret = 0;
        for(int i = 0; i < 32; i++){  // each bit
            bit = 0;
            for(int j = 0; j < l; j++){
                bit += A[j] >> i & 1;
            }
            bit %= 3;
            ret |= bit << i;
        }
        return ret;
    }
}

// 03/24/15
// O(32n) 
// 32个循环遍历每一个bit，内部遍历每个A[j], 累加第i位的digit，求模，把结果放入ret的第i位上
// right shift用来找到第n个digit，left shift用来更改digit在指定位置
public class Solution {
    public int singleNumber(int[] A) {
        int l = A.length;
        if(l == 0) return 0;
        int ret = 0;
        for(int i = 0; i < 32; i++){
            int tmp = 0;
            for(int j = 0; j < l; j++){
                tmp += A[j] >> i & 1;  // 位操作 A[j] >> i & 1 不能判断
            }
            tmp %= 3;
            ret |= tmp << i;
        }
        return ret;
    }
}