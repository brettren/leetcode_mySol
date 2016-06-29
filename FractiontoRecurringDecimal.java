// Given two integers representing the numerator and denominator of a fraction, 
// return the fraction in string format.

// If the fractional part is repeating, enclose the repeating part in parentheses.

// For example,

// Given numerator = 1, denominator = 2, return "0.5".
// Given numerator = 2, denominator = 1, return "2".
// Given numerator = 2, denominator = 3, return "0.(6)".

// 用map来保存不同remainder的起始位置，当某个loop发现重复的remainder，可知已发现循环部分
// 比如1/3，ans = "0.", remainder = 10，map.put(10, 2); 下一个remainder = 10, beg = 2开始循环
public class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";
        if (denominator == 0) return "";
        
        String ans = "";
        
        //如果结果为负数
        if ((numerator < 0) ^ (denominator < 0)) {
            ans += "-";
        }
        
        //下面要把两个数都转为正数，为避免溢出，int转为long
        long num = numerator, den = denominator;
        num = Math.abs(num);
        den = Math.abs(den);
        
        //结果的整数部分
        long res = num / den;
        ans += String.valueOf(res);
        
        //如果能够整除，返回结果
        long rem = (num % den) * 10;
        if (rem == 0) return ans;
        
        //结果的小数部分
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        ans += ".";
        while (rem != 0) {
            //如果前面已经出现过该余数，那么将会开始循环
            if (map.containsKey(rem)) {
                int beg = map.get(rem); //循环体开始的位置
                String part1 = ans.substring(0, beg);
                String part2 = ans.substring(beg, ans.length());
                ans = part1 + "(" + part2 + ")";
                return ans;
            }
            
            //继续往下除
            map.put(rem, ans.length());
            res = rem / den;
            ans += String.valueOf(res);
            rem = (rem % den) * 10;
        }
        
        return ans;
    }
}