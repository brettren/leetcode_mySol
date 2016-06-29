

/**
 * Given a string S, find the longest palindromic substring in S. You may assume
 * that the maximum length of S is 1000, and there exists one unique longest
 * palindromic substring.
 */

public class LongestPalindromicSubstring {
	public String longestPalindrome(String s) {
		int length = s.length();
		String result = "";
		for (int i = 0; i < length; i++) {
			String ps = getPalindrome(s, i, i);  //以i为pivot的奇数length的substring
			if (ps.length() > result.length()) {
				result = ps;
			}
			ps = getPalindrome(s, i, i + 1);  //以i和i+1为pivot的偶数length的substring
			if (ps.length() > result.length()) {
				result = ps;
			}
		}
		return result;
	}

	private String getPalindrome(String s, int l, int r) { // left和right都是string的index
		while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
			l--;
			r++;
		}
		return s.substring(l + 1, r); //[l+1,r-1]
	}
}

// 03/09/15
// 用DP建立二维数组map，同样的方法，记录[j, i]是否是palindrom，同时update max length, 维持最大palindrom的区间
public class Solution {
    public String longestPalindrome(String s) {
        int b = -1;
        int e = -1;
        int l = s.length();
        boolean[][] map = new boolean[l][l];
        for(int i = 0; i < l; i++){
            for(int j = 0; j <= i; j++){
                if(s.charAt(i) == s.charAt(j) && (i-j <= 2 || map[j+1][i-1])){
                    map[j][i] = true;
                    if(b == -1){
                        b = j;
                        e = i;
                    }
                    else if(i-j > e-b){
                        b = j;
                        e = i;
                    }
                }
            }
        }
        return s.substring(b, e+1);
    }
}