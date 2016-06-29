

/**
 * Given a roman numeral, convert it to an integer.
 * 
 * Input is guaranteed to be within the range from 1 to 3999.
 */

public class RomantoInteger {
	public int romanToInt(String s) {
		int result = 0;
		if (s.length() != 0) {
			int prev = getDigit(s.charAt(s.length() - 1));  // 先取最右的char  LSB
			result += prev; 
			for (int i = s.length() - 2; i >= 0; i--) {  // 从右边第二位开始，往左遍历
				int d = getDigit(s.charAt(i));
				// 右加左减：
				// 在较大的罗马数字的右边记上较小的罗马数字，表示大数字加小数字。
				// 在较大的罗马数字的左边记上较小的罗马数字，表示大数字减小数字。
				if (d < prev) {   // d在高位  prev在低位  IV
					result -= d;  // 右边大，就减d
				} else if (d >= prev) {    // VI
					result += d;  // 右边小，就加d
				}
				prev = d;
			}
		}
		return result;
	}

	private int getDigit(char c) {
		switch (c) {
		case 'I':
			return 1;
		case 'V':
			return 5;
		case 'X':
			return 10;
		case 'L':
			return 50;
		case 'C':
			return 100;
		case 'D':
			return 500;
		case 'M':
			return 1000;
		default:
			return 0;
		}
	}
}


// 01/04/15
public class Solution {
    public int romanToInt(String s) {
        int l = s.length();
        if(l == 0) return 0;

        int ret = 0;
        int prev = getDigit(s.charAt(l - 1));
        ret += prev;
        for(int i = l-2; i >= 0; i--){
            int d = getDigit(s.charAt(i));
            if(d < prev)
                ret -= d;
            else
                ret += d;
            prev = d;
        }
        return ret;
    }
    
    private int getDigit(char c){
        switch(c){
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }
}

// 03/24/15
// 左边的和右边比较，再决定左边的是加还是减，如果left >= right，那就是 +   II, VI
// 否则就是 -   IV
public class Solution {
    public int romanToInt(String s) {
        int l = s.length();
        if(l == 0) return 0;
        int ret = helper(s.charAt(l-1));
        for(int i = l-2; i >= 0; i--){
            int num = helper(s.charAt(i));
            int right = helper(s.charAt(i+1));
            if(num >= right) ret += num;  // 注意 是 >= 的情况都是+
            else ret -= num;
        }
        return ret;
    }
    
    private int helper(char c){
        switch(c){
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
        }
        return 0;
    }
}