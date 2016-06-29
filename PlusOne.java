/** 
 * Given a number represented as an array of digits, plus one to the number. 
 */

public class PlusOne {
	public int[] plusOne(int[] digits) {
		int length = digits.length;
		int carry = 1;
		for (int i = length - 1; i >= 0; i--) {
			int sum = digits[i] + carry;  // 从LSB加一
			digits[i] = sum % 10;  // 加一后的结果
			carry = sum / 10; // carry
			if (carry == 0)  // 一旦carry为0，就可以返回结果了
				return digits;
		}
		// 一直没return说明MSB有进位
		int[] ret = new int[length + 1];
		ret[0] = carry;
		for (int i = 0; i < length; i++) {
			ret[i + 1] = digits[i];
		}
		return ret;
	}
}


// 01/10/15
public class Solution {
    public int[] plusOne(int[] digits) {
        int carry = 1;
        int l = digits.length;
        if(l == 0) return digits;
        for(int i = l-1; i >= 0; i--){
            digits[i] += carry;  // 先加上得到sum，再根据sum来得到余数和carry
            carry = digits[i] / 10;
            digits[i] %= 10;
        }
        if(carry == 0) return digits;
        
        int[] ret = new int[l+1];
        ret[0] = 1;
        for(int i = 1; i < l+1; i++){
            ret[i] = digits[i-1];
        }
        return ret;
    }
}


// 03/20/15
// 从末尾开始，carry初始化为1
// 每一位加上carry，得到新的carry，如果是0就直接返回digits[]
// 最后检查是否需要新建一个array，可能需要多一位来放carry
public class Solution {
    public int[] plusOne(int[] digits) {
        int l = digits.length;
        if(l == 0) return digits;
        int carry = 1;
        for(int i = l-1; i >= 0; i--){
            int sum = digits[i]+carry;
            digits[i] = sum%10;
            carry = sum / 10;
            if(carry == 0) return digits;
        }
        if(carry == 0) return digits;
        int[] arr = new int[l+1];
        arr[0] = 1;
        for(int i = 1; i <= l; i++){
            arr[i] = digits[i-1];
        }
        return arr;
    }
}