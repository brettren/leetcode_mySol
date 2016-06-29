import java.util.Stack;

/**
 * Given a string containing just the characters '(' and ')', find the length of
 * the longest valid (well-formed) parentheses substring.
 * 
 * For "(()", the longest valid parentheses substring is "()", which has length = 2.
 * 
 * Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
 */

public class LongestValidParentheses {
	public int longestValidParentheses(String s) {
		int length = s.length();
		if (length == 0)
			return 0;
		int left = 0;
		Stack<Integer> indexs = new Stack<Integer>();
		boolean[] record = new boolean[length];  // 默认都是false
		for (int i = 0; i < length; i++) {
			if (s.charAt(i) == '(') {
				left++;  // 记录还没配对的左括号数
				indexs.push(i);
			} else if (left > 0) {  // s.charAt(i) == ')'
				int idx = indexs.pop();  // 取出最近放入的左括号
				record[idx] = true;  // 左括号
				record[i] = true;  // 右括号
				left--;
			}
		}

		int ret = 0;
		int current = 0;
		for (int k = 0; k < length; k++) {  // 遍历原string
			if (record[k]) {
				current++;  // 统计连续为true的index数，true表示这个index可以计算在substring内
			} else {
				ret = current > ret ? current : ret;
				current = 0;  // 遇到false就把current清0
			}
		}
		return ret > current ? ret : current;  // 也就是最长连续true的substring
	}
}

// 03/08/15
// 先是用stack来确定成对的括号，然后找出连续是括号对的最大长度
// 要注意最后需要再更新一次
public class Solution {
    public int longestValidParentheses(String s) {
        int l = s.length();
        if(l == 0) return 0;
        Stack<Integer> st = new Stack<Integer>();
        boolean[] record = new boolean[l];
        for(int i = 0; i < l; i++){
            if(s.charAt(i) == '('){
                st.push(i);
            }
            else if(!st.isEmpty()){
                int t = st.pop();
                record[t] = true;
                record[i] = true;
            }
        }
        
        int ret = 0;
        int max = 0;
        // 累计连续true的record数量
        for(int i = 0; i < l; i++){
            if(record[i] == true){
                max++;
            }
            else{
                ret = Math.max(ret, max);
                max = 0;
            }
        }
        ret = Math.max(ret, max);  // 注意最后需要再检查更新一遍
        return ret;
    }
}