

/**
 * Implement atoi to convert a string to an integer.
 * 
 * Hint: 
 * 
 * Carefully consider all possible input cases. If you want a challenge,
 * please do not see below and ask yourself what are the possible input cases.
 * 
 * Notes: 
 * 
 * It is intended for this problem to be specified vaguely (ie, no given
 * input specs). You are responsible to gather all the input requirements up front.
 * 
 * Requirements for atoi: The function first discards as many whitespace
 * characters as necessary until the first non-whitespace character is found.
 * Then, starting from this character, takes an optional initial plus or minus
 * sign followed by as many numerical digits as possible, and interprets them as
 * a numerical value.
 * 
 * The string can contain additional characters after those that form the
 * integral number, which are ignored and have no effect on the behavior of this
 * function.
 * 
 * If the first sequence of non-whitespace characters in str is not a valid
 * integral number, or if no such sequence exists because either str is empty or
 * it contains only whitespace characters, no conversion is performed.
 * 
 * If no valid conversion could be performed, a zero value is returned. If the
 * correct value is out of the range of representable values, INT_MAX
 * (2147483647) or INT_MIN (-2147483648) is returned.
 */

public class StringtoIntegerAtoi {
	public int atoi(String str) {
		str = str.trim();  //首先删掉两边的whitespace
		int length = str.length();
		if (length == 0)
			return 0;
		int i = 0;
		boolean minus = false;
		if (str.charAt(0) == '-') {
			minus = true;
			i++;
		} else if (str.charAt(0) == '+') {
			i++;
		}
		long MIN_VALUE = Integer.MIN_VALUE; // 2^31 -1
		long MAX_VALUE = Integer.MAX_VALUE;
		long num = 0;
		boolean finished = false;
		for (; i < length && !finished; i++) {
			char c = str.charAt(i);
			if (c >= '0' && c <= '9') {
				num *= 10;
				num += c - '0';
			} else {  // 如果不是int就直接结束!!!!!
				finished = true;  //takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.
			}
			if (minus && 0 - num < MIN_VALUE) { // 比最小值还小, overflow
				return Integer.MIN_VALUE;  //-2^31
			}
			if (!minus && num > MAX_VALUE) {
				return Integer.MAX_VALUE;  //2^31 -1  注意MIN和MAX不是完全相反的数
			}
		}
		return minus ? new Long(0 - num).intValue() : new Long(num).intValue();  //转为long，防止溢出
	}
}



// 01/02/15
public class Solution {
    public int atoi(String str) {
        String s = str.trim();
        int l = s.length();
        if(l == 0) return 0;
        int i = 0;
        boolean minusflag = false;
        
        if(s.charAt(i) == '+')
            i++;
        else if (s.charAt(i) == '-'){
            i++;
            minusflag = true;
        }
        
        long MAX = Integer.MAX_VALUE;
        long MIN = Integer.MIN_VALUE;
        
        boolean finish = false;
        long num = 0;
        
        for( ; i < l && !finish; i++){
            char c = s.charAt(i);
            if(c >= '0' && c <= '9'){
                num = num*10 + c - '0';
            }
            else{
                finish = true;
            }
            
            if(minusflag == true && 0 - num < MIN){
                return Integer.MIN_VALUE;
            }
            if(minusflag == false && num > MAX){
                return Integer.MAX_VALUE;
            }
        }
        
        return (minusflag) ? new Long(0 - num).intValue() : new Long(num).intValue();
    }
}

// 02/12/15
public class Solution {
    public int atoi(String str) {
        str = str.trim();
        long ret = 0;
        int l = str.length();
        if(l == 0) return 0;
        boolean neg = false;
        boolean finish = false;
        int i = 0;
        char c = str.charAt(0);
        if(c == '+') i++;
        else if(c == '-') {
            neg = true;
            i++;
        }
        for(; i < l && !finish; i++){
            c = str.charAt(i);
            if(c < '0' || c > '9') finish = true;  // 注意遇到不是数字就结束
            else ret = ret*10 + (c -'0');
            if(neg == false && ret > Integer.MAX_VALUE) return Integer.MAX_VALUE; // 每次循环都检查是否overflow
            if(neg == true && -ret < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        }
        return neg == true ? (int)(-ret) : (int)ret;
    }
}

// 03/03/15
// 要考虑到各种情况，先trim除去两端的空白
// 然后判断是否有'+'/'-', 然后再逐个判断是否是digit
// 要考虑到String读到的数也许overflow，到每一位都要判断, 因为返回的是int，所以int overflow就能判断了
public class Solution {
    public int atoi(String str) {
        str = str.trim();
        int l = str.length();
        if(l == 0) return 0;
        long ret = 0;
        boolean neg = false;
        int i = 0;
        // 只有第一位可以是'+'/'-'
        if(str.charAt(i) == '+') {
            i++;
        }
        else if(str.charAt(i) == '-'){
            neg = true;
            i++;
        }
        for(; i < l; i++){
            char c = str.charAt(i);
            if(c < '0' || c > '9') break;  // 检查是否是数字
            int d = c - '0';
            ret = ret*10 + d;
            if(neg == false && ret > Integer.MAX_VALUE) return Integer.MAX_VALUE; // 检查溢出
            if(neg == true && -ret < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        }
        return neg == false ? (int)ret: (int)(-ret);
    }
}