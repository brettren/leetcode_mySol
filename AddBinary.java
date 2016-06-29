

/**
 * Given two binary strings, return their sum (also a binary string).
 * 
 * For example, a = "11" b = "1" Return "100".
 */

public class AddBinary {
	public String addBinary(String a, String b) {
		int i = a.length() - 1;
		int j = b.length() - 1;  // 从a和b的末尾开始往前
		int da = 0;
		int db = 0;
		int carry = 0;
		StringBuffer result = new StringBuffer();
		while (i >= 0 && j >= 0) {
			da = a.charAt(i--) - '0';  // 这里可以直接用xor来得到sum和carry  sum = a ^ b ^ carry  carry = a&b + (a|b)&carry
			db = b.charAt(j--) - '0';
			int d = da + db + carry;
			result.append((char)(d % 2 + '0')); // d是0或2，d%2 = 0；d是1，d%2 = 1
			carry = d >> 1;  // carry
		}
		if (i >= 0) {
			while (i >= 0) {
				da = a.charAt(i--) - '0';
				int d = da + carry;
				result.append((char)(d % 2 + '0'));
				carry = d >> 1;
			}
		} else if (j >= 0) {
			while (j >= 0) {
				db = b.charAt(j--) - '0';
				int d = db + carry;
				result.append((char)(d % 2 + '0'));
				carry = d >> 1;
			}
		}
		if (carry == 1) {
			result.append('1');
		}
		return result.reverse().toString();  // 前面append是从左到右LSB到MSB，所以要reverse
	}
}

// 03/13/15
// 这类题都是相同的思路，从a和b的低位开始相加，加上carry，计算每个位的digit，更新给下一位的carry
public class Solution {
    public String addBinary(String a, String b) {
        int la = a.length()-1;
        int lb = b.length()-1;
        StringBuffer sb = new StringBuffer();
        int carry = 0;
        while(la >= 0 && lb >= 0){
            int da = a.charAt(la) - '0';
            int db = b.charAt(lb) - '0';
            int sum = da+db+carry;
            int digit = sum & 1;
            sb.insert(0, (char)(digit + '0'));
            carry = sum >> 1;
            la--;
            lb--;
        }
        while(la >= 0){
            int da = a.charAt(la) - '0';
            int sum = da+carry;
            int digit = sum & 1;
            sb.insert(0, (char)(digit + '0'));
            carry = sum >> 1;
            la--;
        }
        while(lb >= 0){
            int db = b.charAt(lb) - '0';
            int sum = db+carry;
            int digit = sum & 1;
            sb.insert(0, (char)(digit + '0'));
            carry = sum >> 1;
            lb--;
        }
        if(carry == 1){
            sb.insert(0, '1');
        }
        return sb.toString();
    }
}