

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Divide two integers without using multiplication, division and mod operator.
 */

// 用移位来翻倍，速度更快
public class DivideTwoIntegers {
	public int divide(int dividend, int divisor) {
        long a = dividend;  // 防止溢出
        long b = divisor;  
        a = Math.abs(a); b = Math.abs(b);  
        int res = 0;  
        while (a>=b){  
            long t = b;  
            for (int i = 1; a >= t; i <<= 1, t <<= 1){  
                a -= t;  // 这里是 a - t - t*2 - t*4...
                res += i;  
            }  
        }  
        return ((dividend<0)^(divisor<0))? -res:res;  
	}
}


// 01/05/15
public class Solution {
    public int divide(int dividend, int divisor) {
        long a = Math.abs((long)dividend);  // 注意要先转为long类型再求绝对值，不然会溢出
        long b = Math.abs((long)divisor);
        long ret = 0;
        while(a >= b){
            long tmp = b;
            long i = 1;
            while(a >= tmp){
                a -= tmp;
                ret += i;
                i <<= 1;
                tmp <<= 1;
            }
        }
        if((dividend > 0) ^ (divisor > 0)){
            ret = -ret;
        }
        if(ret < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        if(ret > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        return new Long(ret).intValue();
    }
}

// 03/04/15
// 小心overflow，比如 MAX_INTEGER / -1, 所以先转为long
// 就是每次loop从divisor开始，逐渐翻倍，直到大于dividend; 然后下个loop再重新从divisor开始
// 最后判断结果是否溢出，溢出就用MAX/MIN返回
public class Solution {
    public int divide(int dividend, int divisor) {
        boolean neg;
        if((dividend < 0) ^ (divisor < 0)) neg = true;
        else neg = false;
        long a = Math.abs((long)dividend); // 注意要先转为long类型再求绝对值，不然会溢出， max和min不是完全相反的两个数
        long b = Math.abs((long)divisor);
        long base = 1;
        long ret = 0;
        while(a >= b){
            long tmp = b; 
            base = 1; // 表示是b的一倍数
            while(a >= tmp){
                a -= tmp;
                ret += base;
                tmp <<= 1;
                base <<= 1;
            }
        }
        if(neg == true && -ret < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        if(neg == false && ret > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        return neg == true ? (int)(-ret) : (int)(ret);
    }
}