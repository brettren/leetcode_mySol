

import java.util.Stack;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * 
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 */

// 这里'([])'也是错误的

public class ValidParentheses {
	public boolean isValid(String s) {
		int len = s.length();
		if (len == 0)
			return true;
		Stack<Character> st = new Stack<Character>();
		st.add(s.charAt(0));
		int i = 1;
		while (i < len) {
			switch (s.charAt(i)) {
			case '(':
				st.add('(');
				break;
			case ')':
				if (!st.empty() && st.peek() == '(') {
					st.pop();
				} else {
					return false;
				}
				break;
			case '{':
				st.add('{');
				break;
			case '}':
				if (!st.empty() && st.peek() == '{') {
					st.pop();
				} else {
					return false;
				}
				break;
			case '[':
				st.add('[');
				break;
			case ']':
				if (!st.empty() && st.peek() == '[') {
					st.pop();
				} else {
					return false;
				}
				break;
			}
			i++;
		}
		return st.empty();
	}
}



// 02/09/15
public class Solution {
    public boolean isValid(String s) {
        int l = s.length();
        if(l == 0) return true;
        Stack<Character> st = new Stack<Character>();
        for(int i = 0; i < l; i++){
            switch(s.charAt(i)){
                case '(': 
                    st.add('(');
                    break;
                case ')':
                    if(!st.empty() && st.peek() == '(') st.pop(); // 需要先判断stack是否为空
                    else return false;
                    break;  // 这里break不能少，不然就go through下去了
                case '[':
                    st.add('[');
                    break;
                case ']':
                    if(!st.empty() && st.peek() == '[') st.pop();
                    else return false;
                    break;
                case '{':
                    st.add('{');
                    break;
                case '}':
                    if(!st.empty() && st.peek() == '{') st.pop();
                    else return false;
                    break;
            }
        }
        return st.empty(); // 要注意这里最后要判断stack是否还有东西
    }
}


// 03/15/15
// 6种情况，如果是左括号就放入stack，右括号就检查stack是否为空并且peek是不是对应的左括号，否则就是false
// 检查完以后最后检查stack是否为空，如果不为空也是false
public class Solution {
    public boolean isValid(String s) {
        int l = s.length();
        if(l == 0) return true;
        Stack<Character> st = new Stack<>();
        for(int i = 0; i < l; i++){
            char c = s.charAt(i);
            if(c == '('){
                st.push(c);
            }
            else if(c == ')'){
                if(st.isEmpty() || st.peek() != '(') return false;
                st.pop();
            }
            if(c == '['){
                st.push(c);
            }
            else if(c == ']'){
                if(st.isEmpty() || st.peek() != '[') return false;
                st.pop();
            }
            if(c == '{'){
                st.push(c);
            }
            else if(c == '}'){
                if(st.isEmpty() || st.peek() != '{') return false;
                st.pop();
            }
        }
        return st.isEmpty();
    }
}