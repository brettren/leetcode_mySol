// Implement a basic calculator to evaluate a simple expression string.

// The expression string may contain open ( and closing parentheses ), 
// the plus + or minus sign -, non-negative integers and empty spaces .

// You may assume that the given expression is always valid.

// Some examples:
// "1 + 1" = 2
// " 2-1 + 2 " = 3
// "(1+(4+5+2)-3)+(6+8)" = 23


// the final arithmetic operation on each number is not only depend on the sign directly operating on it, but all signs associated with each unmatched '(' before that number.
// The effect of associated signs are cumulative, stack is builded based on this.

// e.g. 5 - ( 6 + ( 4 - 7 ) ), if we remove all parentheses, the expression becomes 5 - 6 - 4 + 7.

// sign:

// 6: (-1)(1) = -1

// 4: (-1)(1)(1) = -1

// 7: (-1)(1)(-1) = 1

public class Solution {
    public int calculate(String s) {
        s = s.trim().replaceAll("\\ +", "");
        int sign = 1;
        int ret = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                stack.push(stack.peek()*sign);
                sign = 1;
            }
            else if(s.charAt(i) == ')'){
                stack.pop();
            }
            else if(s.charAt(i) == '+'){
                sign = 1;
            }
            else if(s.charAt(i) == '-'){
                sign = -1;
            }
            else{
                int tmp = 0;
                while(i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                    tmp = tmp*10 + (s.charAt(i)-'0');
                    i++;
                }
                ret += tmp*stack.peek()*sign;
                i--;
            }
        }
        return ret;
    }
}