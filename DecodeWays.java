

/**
 * A message containing letters from A-Z is being encoded to numbers using the
 * following mapping:
 * 
 * 'A' -> 1 'B' -> 2 ... 'Z' -> 26 Given an encoded message containing digits,
 * determine the total number of ways to decode it.
 * 
 * For example, Given encoded message "12", it could be decoded as "AB" (1 2) or
 * "L" (12).
 * 
 * The number of ways decoding "12" is 2.
 */
//用一个数组，里面存的是到第几char位的时候 此时有多少种decoding方法
//c[i] = c[i-1] + (s[i-1]s[i] < 26)&&c[i-2]
// 1 ~ 26


// s.charAt(0)   s.charAt(i-1)
//        c[1]            c[n]
public class DecodeWays {
	public int numDecodings(String s) {
		if (s.length() == 0)
			return 0;
		int[] c = new int[s.length()];
		c[0] = s.charAt(0) != '0'? 1 : 0;
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == '0') {
				c[i] = 0; // 这一位如果是0，那就不能当做单个char来decode
			} else {  // 如果是 '1'~'9'
				c[i] = c[i - 1]; // 当做一位来decode
			}
			if (s.charAt(i - 1) == '1'
					|| (s.charAt(i - 1) == '2' && s.charAt(i) <= '6')) {  // 如果是两个char来decode，必须满足范围在10~26之内
				c[i] += (i == 1)? 1 : c[i - 2]; // 把s.charAt(i - 1,i)当做两位来decode
			}
		}
		return c[s.length() - 1];
	}
}


public class Solution {
    public int numDecodings(String s) {
        int l = s.length();
        if(l == 0) return 0;
        int[] ret = new int[l];
        ret[0] = (s.charAt(0) == '0') ? 0 : 1;
        for(int i = 1; i < l; i++){
            if(s.charAt(i) == '0') ret[i] = 0;
            else ret[i] = ret[i-1];
            if(s.charAt(i-1) == '1'|| (s.charAt(i-1) == '2' && s.charAt(i) <= '6'))  // 10-19 20-26
                ret[i] += (i == 1) ? 1 : ret[i-2];
        }
        return ret[l - 1];
    }
}

// 03/06/15
public class Solution {
    public int numDecodings(String s) {
        int l = s.length();
        int[] ret = new int[l];
        if(l == 0) return 0;
        ret[0] = s.charAt(0) == '0' ? 0 : 1;
        for(int i = 1; i < l; i++){
            char c1 = s.charAt(i-1);
            char c2 = s.charAt(i);
            if(c2 != '0') ret[i] = ret[i-1];  // 先判断个位 不能是 0
            if(c1 == '1' || (c1 == '2' && c2 <= '6')){  // 再判断两位 [10, 26] 区间内
                ret[i] += (i == 1) ? 1 : ret[i-2];
            }
        }
        return ret[l-1];
    }
}