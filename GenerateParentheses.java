

import java.util.ArrayList;

/**
 * Given n pairs of parentheses, write a function to generate all combinations
 * of well-formed parentheses.
 * 
 * For example, given n = 3, a solution set is:
 * 
 * "((()))", "(()())", "(())()", "()(())", "()()()"
 */

public class GenerateParentheses {
	public ArrayList<String> generateParenthesis(int n) {
		ArrayList<String> ret = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		generateParenthesis(n, 0, 0, sb, ret);
		return ret;
	}

	public void generateParenthesis(int n, int s, int e, 
			StringBuilder sb, ArrayList<String> ret) {
		if (s == n && e == n) {  // 注意两个都要等于n
			ret.add(sb.toString());
		} else {
			if (s < n) {
				sb.append('(');
				generateParenthesis(n, s + 1, e, sb, ret);  // s 是'('的个数，e 是')'的个数，
				sb.deleteCharAt(sb.length() - 1);
			}			
			if (e < s) {  // ')'的个数不能超过'('
				sb.append(')');
				generateParenthesis(n, s, e + 1, sb, ret);
				sb.deleteCharAt(sb.length() - 1);
			}
		}
	}
}

// 03/21/15
// 记下已经有的left和right括号个数，当两个括号数量等于n时，说明一个sol成立，放入ret
// 只有l < n才能继续插入left；只有r < l才能继续插入right
public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ret = new ArrayList<String>();
        if(n == 0) return ret;
        StringBuffer sb = new StringBuffer();
        helper(ret, sb, n, 0, 0);
        return ret;
    }
    
    public void helper(List<String> ret, StringBuffer sb, int n, int l, int r){
        if(l == n && l == r){
            ret.add(sb.toString());
            return;
        }
        if(l < n){
            sb.append('(');
            helper(ret, sb, n, l+1, r);
            sb.deleteCharAt(sb.length()-1);
        }
        if(r < l){
            sb.append(')');
            helper(ret, sb, n, l, r+1);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}