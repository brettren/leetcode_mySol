

import java.util.ArrayList;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * 
 * Return all possible palindrome partitioning of s.
 * 
 * For example, given s = "aab", Return
 * 
 * [ ["aa","b"], ["a","a","b"] ]
 */
//就是把string的所有按照回文分割（有多种可能 ）
//比方说 aab 可以分成 aa b 和 a a  b 要求每个都是回文

// DP
public class PalindromePartitioning {
	public ArrayList<ArrayList<String>> partition(String s) {
		ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
		ArrayList<String> r = new ArrayList<String>();
		int length = s.length();
		boolean[][] map = new boolean[length][length];  // start end
		findPartition(s, 0, ret, r, map);
		return ret;
	}

	private void findPartition(String s, int start,
			ArrayList<ArrayList<String>> ret, ArrayList<String> r,
			boolean[][] map) {
		int length = s.length();
		if (start == length && r.size() != 0) {  // start已经超过length，并且sol里有值
			ArrayList<String> clone = new ArrayList<String>(r);  // 复制这个结果放入ret
			ret.add(clone);
		} else {
			for (int j = start; j < length; j++) {
				if (start == j
					|| ( j - start > 1 && s.charAt(start) == s.charAt(j) && map[start + 1][j - 1] )
					|| ( j - start == 1 && s.charAt(start) == s.charAt(j) ) ) {
					map[start][j] = true; // 表示s[start,j]是palindrome ！！！！！！！！
					r.add(s.substring(start, j + 1)); //如果s[start，j]是palindrome，那就从这里断开提取这个substring放入sol
					findPartition(s, j + 1, ret, r, map); // j+1是递归子程序的start，也就是先对s[j+1,end]进行palindrome的判断操作
					r.remove(r.size() - 1);
				}
			}
		}
	}
}


// 01/06/15
public class Solution {
    public int minCut(String s) {
        if(s == null || s.length() == 0) return 0;
        int l = s.length();
        int[] ret = new int[l];
        for(int i = 0; i < l; i++){
            ret[i] = i;  // 记得要先初始化为最大的值
        }
        boolean[][] map = new boolean[l][l];
        for(int j = 0; j < l; j++){
            for(int i = 0; i <= j; i++){
                if(s.charAt(i) == s.charAt(j) && (j-i < 2 || map[i+1][j-1])){
                    map[i][j] = true; 
                    if(i != 0) ret[j] = Math.min(ret[j], ret[i-1]+1); // ret[i-1]的结果加1
                    else ret[j] = 0;  // 说明[0,j] 一刀都不用cut
                }
            }
        }
        return ret[l-1];
    }
}

