/**
 * Implement regular expression matching with support for '.' and '*'.
 * 
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * 
 * The matching should cover the entire input string (not partial).
 * 
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 * 
 * Some examples:
 * isMatch("aa","a") -> false
 * isMatch("aa","aa") -> true
 * isMatch("aaa","aa") -> false
 * isMatch("aa", "a*") -> true
 * isMatch("aa", ".*") -> true
 * isMatch("ab", ".*") -> true
 * isMatch("aab", "c*a*b") -> true       c*可以代表 0个c 
 *
 */


// 主要就是分两种情况，一个是j后面没有*或者j是最后一个，一个是j后面有*
public class RegularExpressionMatching {
	public boolean isMatch(String s, String p) {
		return isMatch(s, 0, p, 0);
	}

	private boolean isMatch(String s, int i, String p, int j) {  // s是原string，p是regular expression，i和j分别是他们的index
		int ls = s.length();
		int lp = p.length();
		if (j == lp) {
			return i == ls;  // 如果regular exp的index j超过length了，看原string是否也超过了length
		}
		// j不是最后一个char，下一个char也不是'*'; 或者j是最后一个char。说明不是可能重复的char
		// ...ab...  /  ...a
		if ((j < lp - 1 && p.charAt(j + 1) != '*') || j == lp - 1) {    
			return (i < ls && s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') && isMatch(s, i + 1, p, j + 1);  
			//当前i和j指向的char能match
		}
		// 排除上面的if条件，说明p当前的index j后面一定有'*'！！！
		// i还没有超出length并且两个char match；或者p(j)是'.',能match所有char
		while ((i < ls && s.charAt(i) == p.charAt(j)) || (i < ls && p.charAt(j) == '.')) {  			// 比如'aaab'和'a*b*' 
			// 在这个loop里i不断自增，i=i,i+1,i+2,...对所有可能进行测试, 只要找到一个匹配成功即可   			i       j
			if (isMatch(s, i, p, j + 2)) //用i,i+1,i+2,...不断和j+2去尝试比较
				return true;  // 注意 'a' 和 'a*a*a'是match的，而和'aa'是不match的。a*表示0到n个a ！！！！所以我们可以跳过a*，但不能跳过a
			//如果上面返回false，就继续和原来的j比较
			i++;  //能进入loop说明s当前的i和p的j match，把index i移到下一个
		}  //注意在这个while loop里j保持不变
		//如果i==ls 或者 i和j对应的char不match了
		return isMatch(s, i, p, j + 2);  // 要保证s里每个都有match，但是p里不需要每个都有match
	}
}



class Solution {
	public:
	bool matchFirst(const char *s, const char *p){
	    return (*p == *s || (*p == '.' && *s != '\0'));
	}

	bool isMatch(const char *s, const char *p) {
	    if (*p == '\0') return *s == '\0';  //empty

	    if (*(p + 1) != '*') {//without *
	        if(!matchFirst(s,p)) return false;
	        return isMatch(s + 1, p + 1);
	    } else { //next: with a *
	        if(isMatch(s, p + 2)) return true;    //try the length of 0
	        while ( matchFirst(s,p) )       //try all possible lengths 
	            if (isMatch(++s, p + 2))return true;
	    }
	}
};


动态规划：

dp[i][j]表示字串 s[i...len(s)], p[j...len(p)] 是否可以匹配。

那么状态转移方程如下：

dp[i][j] = 

c1. p[j+1] != '*'   if s[i] == p[j]  dp[i][j] = dp[i+1][j+1] 

                       else dp[i][j] = false

c2 p[j+1] == '*'   这个情况下，要扩展 *, dp[i][j] 从拓展的情况下，选择一个是真的结果）

                       if( s[i] ==  p[j] || p[j] == '.' && (*s) != 0)  当s[i] 和 p[j] 一样的时候，
                       	例如 aba, a*b这个时候，i = 0, j = 0, 自然可以匹配a a

                        如果p[j] == '.'  因为他可以匹配任何字符，所以和相等关系有基本一样的方式。

                       并且每一步匹配都要递增 i 的值，如果有成立的，则返回true，否则到匹配终了，返回通配符匹配完成后的结果。


// 01/07/15
public class Solution {
    public boolean isMatch(String s, String p) {
        return helper(s, 0, p, 0);
    }
    
    private boolean helper(String s, int i, String p, int j){
        int ls = s.length();
        int lp = p.length();
        if(j >= lp) return i == ls;
        
        if((j < lp-1 && p.charAt(j+1) != '*') || j == lp-1){ // p的这个位一定要match上
            if(i >= ls) return false;
            return (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') && helper(s, i+1, p, j+1);
        }
        // 跳过上面的if语句，说明p这个位和后面一位是 任何char+* ,可以跳过
        while(i < ls && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.')){
        	// 这里要控制char+*的贪婪性，先尝试p后面一位位去match，不行的话再用char+*贪婪地去match更多
            if(helper(s, i, p, j+2)) return true;
            i++;
        }
        return helper(s, i, p, j+2);
    }
}

// 02/08/15
public class Solution {
    public boolean isMatch(String s, String p) {
        return helper(s, 0, p, 0);
    }
    
    private boolean helper(String s, int i, String p, int j){
        int ls = s.length();
        int lp = p.length();
        if(j >= lp) return i == ls;
        
        if((j < lp-1 && p.charAt(j+1) != '*') || j == lp-1){ // p的这个位一定要match上
            return match(s, i, p, j) && helper(s, i+1, p, j+1);
        }
        // 跳过上面的if语句，说明p这个位和后面一位是 任何char+* ,可以跳过
        while(match(s, i, p, j)){
        	// 这里要控制char+*的贪婪性，先尝试p后面一位位去match，不行的话再用char+*贪婪地去match更多
            if(helper(s, i, p, j+2)) return true;
            i++;
        }
        return helper(s, i, p, j+2);
    }

    private boolean match(String s, int i, String p, int j){
		int ls = s.length();
    	return i < ls && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
    }
}



// 03/09/15
// 分两种情况分析，一种是后面没有'*'; 一种是有'*' (需要match，or 可以skip), 然后再分析对应位是否match
public class Solution {
    public boolean isMatch(String s, String p) {
        return isMatch(s, 0, p, 0);
    }
    
    public boolean isMatch(String s, int i, String p, int j){
        int ls = s.length();
        int lp = p.length();
        if(j == lp){
            return i == ls;
        }
        if((j < lp-1 && p.charAt(j+1) != '*') || j == lp-1){  // pattern 的这一位一定要有match
            if(i == ls) return false;
            if(s.charAt(i) != p.charAt(j) && p.charAt(j) != '.') return false;
            return helper(s, i+1, p, j+1); 
        }
        // 如果是'*',先尝试跳过这个'*'; 只有能够match才有选择的可能
        while(i < ls && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.')){
            if(isMatch(s, i, p, j+2)){
                return true;
            }
            i++;
        }
        //对不上就一定要跳过'*'了
        return isMatch(s, i, p, j+2);
    }
}