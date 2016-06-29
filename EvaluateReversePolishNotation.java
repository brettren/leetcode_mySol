

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * 
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * 
 * Some examples:
 * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 */

import java.util.Stack;

public class EvaluateReversePolishNotation {
	public int evalRPN(String[] tokens) {
		Stack<Integer> st = new Stack<Integer>();
		int size = tokens.length;
		for (int i = 0; i < size; i++) {
			if (isDigital(tokens[i])) {  // 如果是数字就放入stack
				st.push(Integer.parseInt(tokens[i]));
			} else {  // 如果是符号就开始计算
				st.push(calc(st.pop(), st.pop(), tokens[i])); // 把得到的结果再放回stack！！！！
			}
		}
		return st.pop();
	}

	private Integer calc(Integer num2, Integer num1, String op) {  // 注意这里是num2，num1的顺序
		switch (op.charAt(0)) {
		case '+':
			return num1 + num2;
		case '-':
			return num1 - num2;
		case '*':
			return num1 * num2;
		case '/':
			return num1 / num2;
		default:
			return 0;
		}
	}

	private boolean isDigital(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}


// 03/08/15
// 先检查是否是4个运算符之一，如果是，就pop两次，进行计算后再push结果；如果是数字，就直接push回去
public class Solution {
    public int evalRPN(String[] tokens) {
        int l = tokens.length;
        if(l == 0) return 0;
        Stack<Integer> st = new Stack<Integer>();
        Set<String> set = new HashSet<String>();
        set.add("+"); // 注意是双引号，表示string
        set.add("-");
        set.add("*");
        set.add("/");
        for(int i = 0; i < l; i++){
            if(set.contains(tokens[i])){
                int b = st.pop();
                int a = st.pop();
                int result = 0;
                if(tokens[i].equals("+")){
                    result = a+b;
                }
                if(tokens[i].equals("-")){
                    result = a-b;
                }
                if(tokens[i].equals("*")){
                    result = a*b;
                }
                if(tokens[i].equals("/")){
                    result = a/b;
                }
                st.push(result);
            }
            else{
                st.push(Integer.parseInt(tokens[i]));
            }
        }
        return st.pop();
    }
}