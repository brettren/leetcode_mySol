

/**
 * Determine whether an integer is a palindrome. Do this without extra space.
 * 
 * Some hints: Could negative integers be palindromes? (ie, -1)
 * 
 * If you are thinking of converting the integer to string, note the restriction
 * of using extra space.
 * 
 * You could also try reversing an integer. However, if you have solved the
 * problem "Reverse Integer", you know that the reversed integer might overflow.
 * How would you handle such case?
 * 
 * There is a more generic way of solving this problem.
 */

//23432

//如果是回文的话 那么这个int 应该== reverse(int)
public class PalindromeNumber {
	public boolean isPalindrome(int x) {
	    if(x<0){
	    	return false;  // 负数不是palindrome  
	    }
	    return x == reverse(x);
	}
	  
	private int reverse(int x){
		int result=0;
		while(x!=0){
			result=result*10+ x%10; // 取x各位上的值
			x=x/10;	
		}
		return result;
	}
}


// 结果正确，但是用了extra space
public class Solution {
    public boolean isPalindrome(int x) {
		if (x < 0)
			return false;  // 负数不是palindrome    	
        String s = String.valueOf(x);
        int i = 0;
        int j = s.length()-1;
        while(i<j){
            if(s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }
}


// 03/20/15
// 负值不是palindrom
// 然后为了防止溢出，还是用long 来比较，用tmp来作为x的buffer
public class Solution {
    public boolean isPalindrome(int x) {
        if(x < 0) return false;
        long ret = 0;
        long tmp = (long)x;
        while(tmp != 0){
            ret = ret*10+tmp%10;
            tmp /= 10;
        }
        return ret == x;
    }
}