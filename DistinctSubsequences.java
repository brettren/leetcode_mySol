

/**
 * Distinct Subsequences, Given a string S and a string T, count the
 * number of distinct subsequences of T in S.
 * 
 * A subsequence of a string is a new string which is formed from the original
 * string by deleting some (can be none) of the characters without disturbing
 * the relative positions of the remaining characters. (ie, "ACE" is a
 * subsequence of "ABCDE" while "AEC" is not).
 * 
 * Here is an example: S = "rabbbit", T = "rabbit"
 * 
 * Return 3.
 * 这题的意思是 rabbit 可以有rabbbit的里面的有元素,在不改变顺序的情况下取里面的元素 能组成rabbit 就是可以
 * 比方说 rabbit可以由rabbbit的 第一第二个b 第二第三个b 第一第三个b组成 
 * 这样 有3种组成方法 所以是 返回3
 *
 * f(i, j) = f(i - 1, j) + S[i] == T[j]? f(i - 1, j - 1) : 0 
 * Where f(i, j) is the number of distinct sub-sequence for T[0:j] in S[0:i].
 */
// 只有在i>=j的情况下，f[i][j]才可能不是0  所以其实只有左下半部分的三角区域是有效数字
public class DistinctSubsequences {
	public int numDistinct(String S, String T) {
		if(S.length() < T.length()) return 0;
		int[][] f = new int[S.length() + 1][T.length() + 1]; //f[i][j]就是 S[0:i]组成T[0:j]有几种方法
		for (int k = 0; k < S.length(); k++)
			f[k][0] = 1;  // 如果T为空，那length为0,1,2...,S.length()的S都只有一种方法组成T
		for (int i = 1; i <= S.length(); i++) {
			for (int j = 1; j <= T.length(); j++) {
   		 		f[i][j] = f[i-1][j]; // 如果f[i-1][j]有x种方法，那么让S的size加一，至少f[i][j]也会有x种方法
    	  		if(S.charAt(i-1) == T.charAt(j-1)){   //因为num矩阵里是从1开始的,[0][0]是空,所以S.charAt(0)就是f[1]表示的
    	  			// 如果S和T多出来的一位相同，刚好两个最后位match，然后都除掉这一位，各退一步，看有几种方法
    				f[i][j] += f[i-1][j-1];  // rabb  rab
    				// 因为如果S[i-1]和T[j-1]不相同，S这边多出来的第i位没有用，这一位只能和T最后位match才有意义
    			}
			}
		}
		return f[S.length()][T.length()];
	}
}


// 03/14/15
// 建立二维数组，DP，长宽是S和T的长度+1
// 分两种情况，如果最后一个对应位match，那就 = map[i-1][j] + map[i-1][j-1]；unmatch 就可以不算 第j位了 = map[i-1][j]
public class Solution {
    public int numDistinct(String S, String T) {
        int ls = S.length();
        int lt = T.length();
        if(ls < lt) return 0;
        int[][] map = new int[ls+1][lt+1];
        for(int i = 0; i <= ls; i++){
            map[i][0] = 1;
        }
        
        for(int i = 1; i <= ls; i++){
            for(int j = 1; j <= lt; j++){
                if(S.charAt(i-1) != T.charAt(j-1)){  //S = "aa(b)", T = "a(a)"
                    map[i][j] = map[i-1][j];
                }
                else{         //S = "aa(a)", T = "a(a)"
                    map[i][j] = map[i-1][j] + map[i-1][j-1];  // 两种情况，一是S和T的最后一位匹配，二是不是他们两个匹配
                }  
            }
        }
        
        return map[ls][lt];
    }
}