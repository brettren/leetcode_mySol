

/**
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 * 
 * For example, Given: s1 = "aabcc", s2 = "dbbca",
 * 
 * When s3 = "aadbbcbcac", return true. When s3 = "aadbbbaccc", return false.
 */

//看 s3是否能由s1 s2交错但是不错乱顺序的组成

public class InterleavingString {
	public boolean isInterleave(String s1, String s2, String s3) {
		if (s1.length() + s2.length() != s3.length())
			return false;
		boolean[][] mat = new boolean[s1.length() + 1][s2.length() + 1];
		mat[0][0] = true;  // 表示s1和s2都还没有和s3比较
		for (int i = 1; i <= s1.length(); ++i)
			mat[i][0] = mat[i - 1][0] && (s3.charAt(i - 1) == s1.charAt(i - 1));  // 先不管s2，拿s1和s3一个个比较
		for (int i = 1; i <= s2.length(); ++i)
			mat[0][i] = mat[0][i - 1] && (s3.charAt(i - 1) == s2.charAt(i - 1));  // 不管s1，拿s2和s3一个个比较
		for (int i = 1; i <= s1.length(); ++i)
			for (int j = 1; j <= s2.length(); ++j)
				// mat[i][j]表示s1(0,i), s2(0,j)能否形成s3(0,i+j)
				mat[i][j] = (mat[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1))  // s1的第1,2,...i个，s2的第1,2,...j个
						 || (mat[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1));
		return mat[s1.length()][s2.length()];
	}
}

// 下面是dp的sol, 但是会超时
public class InterleavingString {
	public boolean isInterleave(String s1, String s2, String s3) {  //"a", "b", "ab"   
		if (s1.length() + s2.length() != s3.length()) 
			return false;
		if (s1.length() == 0) {
			return (s2.equals(s3));
		}
		if (s2.length() == 0) {
			return (s1.equals(s3));
		}
		return isInterleave(s1, s2, s3, 0, 0, 0);
	}

	public boolean isInterleave(String s1, String s2, String s3, int s1_idx, int s2_idx, int s3_idx){
		if( (s1_idx >= s1.length())&&(s2_idx >= s2.length())&&(s3_idx >= s3.length()) ){
			return true;
		}
		if ( (s1_idx < s1.length()) && (s1.charAt(s1_idx) == s3.charAt(s3_idx)) ) {
			if (isInterleave(s1, s2, s3, s1_idx + 1, s2_idx, s3_idx + 1)) {
				return true;
			}
		}
		if ( (s2_idx < s2.length()) && (s2.charAt(s2_idx) == s3.charAt(s3_idx)) ) {
			if (isInterleave(s1, s2, s3, s1_idx, s2_idx + 1, s3_idx + 1)) {
				return true;
			}
		}
		return false;	
	}

	public static void main(String[] args) {
		System.out.println( isInterleave("aa", "ab", "abaa") ) ;
	}
}



//03/08/15
// DP, 表示l1+l2的s3能不能被s1和s2组成，每一位都检查是否和s1或s2的对应位char相等
public class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int l1 = s1.length();
        int l2 = s2.length();
        int l3 = s3.length();
        if(l1+l2 != l3) return false;
        boolean[][] map = new boolean[l1+1][l2+1];
        map[0][0] = true;
        for(int i = 1; i <= l1; i++){
            if(map[i-1][0] == true && s1.charAt(i-1) == s3.charAt(i-1))
                map[i][0] = true;
        }
        for(int i = 1; i <= l2; i++){
            if(map[0][i-1] == true && s2.charAt(i-1) == s3.charAt(i-1))
                map[0][i] = true;
        }
        
        for(int i = 1; i <= l1; i++){
            for(int j = 1; j <= l2; j++){
                if(map[i-1][j] == true && s1.charAt(i-1) == s3.charAt(i+j-1)){
                    map[i][j] = true;
                }
                if(map[i][j-1] == true && s2.charAt(j-1) == s3.charAt(i+j-1)){
                    map[i][j] = true;
                }
            }
        }
        return map[l1][l2];
    }
}