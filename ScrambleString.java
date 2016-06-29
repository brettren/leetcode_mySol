/** 
 * Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.
 *
 * Below is one possible representation of s1 = "great":
 *
 *      great
 *     /    \
 *    gr    eat
 *   / \    /  \
 *  g   r  e   at
 *            / \
 *           a   t
 * To scramble the string, we may choose any non-leaf node and swap its two children.
 *
 * For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".
 *
 *       rgeat
 *      /    \
 *     rg    eat
 *    / \    /  \
 *   r   g  e   at
 *             / \
 *            a   t
 * We say that "rgeat" is a scrambled string of "great".
 *
 * Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".
 *
 *       rgtae
 *      /    \
 *     rg    tae
 *    / \    /  \
 *   r   g  ta  e
 *         / \
 *        t   a
 * We say that "rgtae" is a scrambled string of "great".
 *
 * Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
 */

//也就是一次只能进行两个child的swap

public class ScrambleString {
	public boolean isScramble(String s1, String s2) {
		if (s1.length() != s2.length())
			return false;
		if (s1.equals(s2))  // 递归到这发现两个string相同，就直接返回true
			return true;

		// 这段加上可以提高效率，更早排除不可能的分割方法
		int[] A = new int[26];  // 26个字母
		for (int i = 0; i < s1.length(); i++) {
			++A[s1.charAt(i) - 'a'];
		}

		for (int j = 0; j < s2.length(); j++) {
			--A[s2.charAt(j) - 'a'];
		}

		for (int k = 0; k < 26; k++) {
			if (A[k] != 0)
				return false;  // s1和s2出现的各个字母个数要一一对应
		}

		// 尝试把s1各种scramble的string都形成一次
		for (int i = 1; i < s1.length(); i++) {
			boolean result1 = isScramble(s1.substring(0, i), s2.substring(0, i))
					&& isScramble(s1.substring(i), s2.substring(i));  // 如果是正向对应  (0,i)和(0,i)，(i,length)和(i,length)
			boolean result2 = isScramble(s1.substring(0, i), s2.substring(s2.length() - i, s2.length()) )  
					&& isScramble(s1.substring(i), s2.substring(0, s2.length() - i));  // 如果是反向对应  
			if (result1 || result2)
				return true;
		}
		return false;  // 所有遍历都没有找到相符的就返回false
	}
}


// 01/16/15
public class Solution {
    public boolean isScramble(String s1, String s2) {
        int l1 = s1.length();
        int l2 = s2.length();
        if(l1 != l2) return false;
        int[] count = new int[26];
        if(s1.equals(s2)) return true;
        
        for(int i = 0; i < l1; i++){
            int idx = s1.charAt(i) - 'a';
            count[idx]++;
        }
        for(int i = 0; i < l1; i++){
            int idx = s2.charAt(i) - 'a';
            if(count[idx] == 0) return false;
            count[idx]--;
        }
        for(int i = 0; i < l1; i++){
            int idx = s2.charAt(i) - 'a';
            if(count[idx] != 0) return false;
        }
        
        for(int i = 1; i < l1; i++){
            boolean r1 = isScramble(s1.substring(0, i), s2.substring(0, i)) 
                        && isScramble(s1.substring(i, l1), s2.substring(i, l1));
            boolean r2 = isScramble(s1.substring(0, i), s2.substring(l1-i, l1)) 
                        && isScramble(s1.substring(i, l1), s2.substring(0, l1-i));
            if(r1 || r2) return true;
        }
        return false;
    }
}

// 02/06/15
public class Solution {
    public boolean isScramble(String s1, String s2) {
        int l1 = s1.length();
        int l2 = s2.length();
        if(l1 != l2) return false;
        if(s1.equals(s2)) return true;
        int[] map = new int[26];
        
        for(int i = 0; i < l1; i++){
            int d = s1.charAt(i)-'a';
            map[d]++;
        }
        for(int i = 0; i < l1; i++){
            int d = s2.charAt(i)-'a';
            if(map[d] == 0) return false;
            map[d]--;
        }
        for(int i = 0; i < l1; i++){
            int d = s2.charAt(i)-'a';
            if(map[d] != 0) return false;
        }
        
        for(int i = 1; i < l1; i++){
            boolean f1 = isScramble(s1.substring(0, i), s2.substring(0, i)) 
                        && isScramble(s1.substring(i, l1), s2.substring(i, l1));
            boolean f2 = isScramble(s1.substring(0, i), s2.substring(l1-i, l1))
                        && isScramble(s1.substring(i, l1), s2.substring(0, l1-i));
            if(f1 || f2) return true;
        }
        return false;
    }
}

// 03/12/15
// 递归程序，每次先遍历s1和s2每一位的char，统计看map是否match，有助于prune递归
// 然后比较s1的左边和s2的左边，s1的右边和s2的右边
// 比较s1的左边和s2的右边，s1的右边和s2的左边
// 只要一个比较match，返回true
public class Solution {
    public boolean isScramble(String s1, String s2) {
        int l = s1.length();
        if(l == 0) return true;
        if(s1.equals(s2)){
            return true;
        }
        int[] map = new int[26];
        for(int i = 0; i < l; i++){
            int idx = s1.charAt(i)-'a';
            map[idx]++;
        }
        for(int i = 0; i < l; i++){
            int idx = s2.charAt(i)-'a';
            map[idx]--;
        }
        for(int i = 0; i < 26; i++){
            if(map[i] != 0) return false;
        }
        for(int i = 1; i < l; i++){
            boolean result1 = isScramble(s1.substring(0, i), s2.substring(0, i)) && 
                              isScramble(s1.substring(i), s2.substring(i));
            boolean result2 = isScramble(s1.substring(0, i), s2.substring(l-i)) && 
                              isScramble(s1.substring(i), s2.substring(0, l-i));
            if(result1 || result2) return true;
        }
        return false;
    }
}