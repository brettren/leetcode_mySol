

/**
 * Given an integer, convert it to a roman numeral.
 * 
 * Input is guaranteed to be within the range from 1 to 3999.
 */

public class IntegertoRoman {
	public String intToRoman(int num) {
		String a[][] = {  
				{ "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" },  // 个位  1,2,3...,9
				{ "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" },  // 十分位  10,20,30...90
				{ "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" },  // 百分位  100,200,300...900
				{ "M", "MM", "MMM", "", "", "", "", "", "" } };
		StringBuffer result = new StringBuffer();
		int key = 0;
		while (num != 0) {
			int d = num % 10;  // 取LSB
			if (d != 0) {  // 罗马数字中没有“0”, 所以一行只有1~9,   101表示为 CI，十分位的0不需要表示，所以就跳过
				result.insert(0, a[key][d - 1]);  // 插入前面 注意是d-1
			}
			num /= 10;
			key++; // 个位，十位，百位...
		}
		return result.toString();
	}
}

右加左减：
在较大的罗马数字的右边记上较小的罗马数字，表示大数字加小数字。
在较大的罗马数字的左边记上较小的罗马数字，表示大数字减小数字。
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

// 02/14/15
public class Solution {
    public String intToRoman(int num) {
        String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder ret = new StringBuilder();
        for (int i=0; i<values.length; i++) {
            while (num >= values[i]) {
                ret.append(symbols[i]);
                num -= values[i];
            }
        }
        return sb.toString();
    }
}

// 03/22/15
// 先建立两个map
// 然后不断地从最大值开始减，找到对应的roman放入ret
// 要从最大值开始尝试，尽可能减少时间
public class Solution {
    public String intToRoman(int num) {
        String[] s = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        int[] v = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        int l = v.length;
        StringBuffer ret = new StringBuffer();
        int i = 0;
        while(num != 0){
            while(num >= v[i]){
                ret.append(s[i]);
                num -= v[i];
            }
            i++;
        }
        return ret.toString();
    }
}