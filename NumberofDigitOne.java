// Given an integer n, count the total number of digit 1 appearing in all 
// non-negative integers less than or equal to n.

// For example:
// Given n = 13,
// Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.d


// 比如56就有(5+1)+10=16个。如何知道是否要加上多出的10个呢，
// 我们就要看十位上的数字是否大于等于2，是的话就要加上多余的10个'1'。
//看某一位数 d >= 2， 比如20； 那就是
//用(n+8)/10来判断一个数是否大于等于2
//用n % 10 == 1来判断末位是否是1
// b用来记录后面几位的数，如果当前循环是5，那么b就是8   5(8)  1(23)
// 58

public class Solution {
    public int countDigitOne(int n) {
        int res = 0, a = 1, b = 1;
        while (n > 0) {
            res += (n + 8) / 10 * a;
            res += (n % 10 == 1) ? b : 0;   // (6 + 0)   (10 + 0) 
            b += n % 10 * a;  
            a *= 10;  // 表示第几位， 十位就是10
            n /= 10;  // 5
        }
        return res;
    }
}