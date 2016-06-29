/**
 * Validate if a given string is numeric.
 * 
 * Some examples:
 * 
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * 
 * Note: It is intended for the problem statement to be ambiguous. 
 * You should gather all requirements up front before implementing one.
 *
 */
public class ValidNumber {
	public boolean isNumber(String s) {
		s = s.trim();
		int length = s.length();
		if (length == 0)
			return false;
		int i = 0;
		if (s.charAt(i) == '-' || s.charAt(i) == '+') {
			i++;
		}
		for (; i < length; i++) {
			char c = s.charAt(i);
			if (c == 'e' || c == 'E') {
				return isDouble(s.substring(0, i)) && isDigitals(s.substring(i + 1, s.length()));  // 'E'的左右两边
			} else if (c != '.' && (c < '0' || c > '9')) {
				return false;
			}
		}
		return isDouble(s);  // 说明没有'E'
	}

	private boolean isDouble(String s) {  // 这里的s前面肯定没有'-' '+'
		int length = s.length();  
		if (length == 0 || ((s.charAt(0) == '-' || s.charAt(0) == '+') && length == 1))  // 没有内容，或者只有正负号
			return false;
		int i = 0, start = 0;
		if (s.charAt(i) == '-' || s.charAt(i) == '+') {
			i++;
			start++;
		}		
		for (; i < length; i++) {
			char c = s.charAt(i);
			if (c == '.') {  // 找到'.'
				if (i == start && s.length() - 1 == i)  // 说明'.'前后都没有数据  s.length() - 1 == i
					return false;
				boolean left = i == start ? true : isDigitals(s.substring(0, i));  // '.' 的left
				boolean right = s.length() - 1 == i ? true : isDigitals(s.substring(i + 1, s.length())); // '.' 的right
				return left && right;
			} else if (c < '0' || c > '9') {
				return false;
			}
		}
		return true;
	}

	private boolean isDigitals(String s) {
		int length = s.length();
		if (length == 0 || ((s.charAt(0) == '-' || s.charAt(0) == '+') && length == 1))  // '.' 的前面没有数字
			return false;      // 考虑到指数也有正负
		int i = 0;
		if (s.charAt(i) == '-' || s.charAt(i) == '+') {
			i++;
		}
		for (; i < length; i++) {
			char c = s.charAt(i);
			if (c < '0' || c > '9') {
				return false;
			}
		}
		return true;
	}
}










public class Solution {
    public boolean isNumber(String s) {
        s = s.trim();
        int l = s.length();
        if(l == 0) return false;
        int i = 0;
        if(s.charAt(i) == '+' || s.charAt(i) == '-'){
            i++;
        }
        for( ; i < l; i++){
            char c = s.charAt(i);
            if(c == 'e' || c == 'E'){
                return isDouble(s.substring(0,i)) && isInt(s.substring(i+1));
            }
            else if(c != '.' && (c > '9' || c < '0')) {
                return false;
            }
        }
        return isDouble(s);
    }
    
    private boolean isDouble(String s) {
        int l = s.length();
        if(l == 0 || ((s.charAt(0) == '+' || s.charAt(0) == '-') && l == 1)) return false;
        int i = 0, start = 0;
        if(s.charAt(i) == '+' || s.charAt(i) == '-'){
            i++;
            start++;
        }
        for( ; i < l; i++){
            char c = s.charAt(i);
            if(c == '.'){
                if(i == start && i == l-1) return false;
                boolean left  = (i == start)? true: isInt(s.substring(start, i));
                boolean right = (i == l-1)? true: isInt(s.substring(i+1));
                return left && right;  
            } 
            else if(c > '9' || c < '0'){
                return false; 
            }
        }
        return true;
    }
    
    private boolean isInt(String s) {
        int l = s.length();
        if(l == 0 || ((s.charAt(0) == '+' || s.charAt(0) == '-') && l == 1)) return false;
        int i = 0;
        if(s.charAt(i) == '+' || s.charAt(i) == '-'){
            i++;
        }
        for( ; i < l; i++){
            char c = s.charAt(i);
            if(c > '9' || c < '0') return false;
        }
        return true;
    }
}



// 09/08/2015
// best solution O(n)
// 跳过正负号，统计digit和dot的个数，dot多于1或者digit为0个都是false。发现'e'之后跳过正负号只能是digit

// ".1"  "1." correct
// "."        false
public class Solution {
    public boolean isNumber(String s) {
        s = s.trim();
        if(s.length() == 0) return false;
        int l = s.length();
        int i = 0;
        if(s.charAt(i) == '+' || s.charAt(i) == '-') {
            i++;
        }
        int numPoints = 0, numDigits = 0;
        while(i < l && ((s.charAt(i) >= '0' && s.charAt(i) <= '9') || s.charAt(i) == '.')){
            if(s.charAt(i) == '.'){
                numPoints++;
            }
            else{
                numDigits++;
            }
            i++;
        }
        if(numPoints > 1 || numDigits == 0) return false;
        if(i < l && s.charAt(i) == 'e'){
            i++;
            if(i < l && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
                i++;
            }
            numDigits = 0;
            while(i < l && s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                numDigits++;
                i++;
            }
            if(numDigits == 0) return false;
        }
        return i == l;
    }
}