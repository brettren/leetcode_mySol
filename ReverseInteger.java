

/**
 * Reverse digits of an integer.
 * 
 * Example1: x = 123, return 321 Example2: x = -123, return -321
 * 
 * Have you thought about this? Here are some good questions to ask before
 * coding. Bonus points for you if you have already thought through this!
 * 
 * If the integer's last digit is 0, what should the output be? ie, cases such
 * as 10, 100.
 * 
 * Did you notice that the reversed integer might overflow? Assume the input is
 * a 32-bit integer, then the reverse of 1,000,000,003 overflows. How should you
 * handle such cases?   2^31-1
 * 
 * Throw an exception? Good, but what if throwing an exception is not an option?
 * You would then have to re-design the function (ie, add an extra parameter).
 */

public class ReverseInteger {
	public int reverse(int x) {
		int num = Math.abs(x);
		int ret = 0;
		while (num != 0) {
			int d = num - num / 10 * 10;  // d表示各个digit的值      123-123/10*10 = 123-120 = 3   12-10 = 2
			ret = ret * 10 + d;  // ret = 3    ret左移然后加上新的digit     3*10+2 = 32
			num /= 10;  // 123/10 = 12   12/10 = 1
		}
		if (x < 0)
			return -ret;
		else
			return ret;
	}
}


// 下面是考虑到overflow的情况
public class ReverseInteger {
	public int reverse(int x) {
		long ret = 0;
		while (x != 0) {
			ret = ret * 10 + x % 10;
			x /= 10;
	    }
        // handle overflow/underflow
		if (Math.abs(ret) > Integer.MAX_VALUE) {  // 正常范围是在[]
		   return 0;
		}
		else{
		   return (int)ret;
		}
	}
}


//If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100. 
//这种情况下下面的sol不可用
public class ReverseInteger {
	public int reverse(int x) {
		String s = String.valueOf(x);
		StringBuffer sb = new StringBuffer(s);
		int i = 0, j = s.length()-1;
		if (sb.charAt(0) == '-' || sb.charAt(0) == '+') {
			i++;
		}
		while(i<j){
			char temp = sb.charAt(i);
			sb.setCharAt(i, sb.charAt(j));
			sb.setCharAt(j, temp);
			i++;
			j--;
		}
		return sb.toString();
	}
}

// 01/07/15
public class Solution {
    public int reverse(int x) {
        long ret = 0;
        while(x != 0){
            int d = x%10;
            x = x/10;
            ret = ret*10 + d;
        }
        if(ret > Integer.MAX_VALUE) return 0;
        if(ret < Integer.MIN_VALUE) return 0;
        return new Long(ret).intValue();
    }
}

// 01/26/15
public class Solution {
    public int reverse(int x) {
        boolean minus = false;
        if(x < 0) minus = true;
        long ret = 0;
        x = Math.abs(x);
        while(x != 0){
            ret = ret*10 + x%10;
            x /= 10;
        }
        ret = minus ? -ret : ret;
        if(ret > Integer.MAX_VALUE) return 0;  // 注意overflow就返回0
        if(ret < Integer.MIN_VALUE) return 0;
        return (new Long(ret)).intValue();
    }
}

// 03/19/15
// 防止overflow的问题，所以用long来表示ret，reverse就是x不断十进制右移，移出的LSB添加到ret
public class Solution {
    public int reverse(int x) {
        long ret = 0;
        while(x != 0){
            ret = ret*10+x%10;
            x /= 10;
        }
        if(ret > Integer.MAX_VALUE) return 0;
        if(ret < Integer.MIN_VALUE) return 0;
        return (int)ret;
    }
}