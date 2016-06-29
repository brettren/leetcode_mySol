

/**
 * Given a string s consists of upper/lower-case alphabets and empty space
 * characters ' ', return the length of last word in the string.
 * 
 * If the last word does not exist, return 0.
 * 
 * Note: A word is defined as a character sequence consists of non-space
 * characters only.
 * 
 * For example, Given s = "Hello World", return 5.
 */

public class LengthofLastWord {
	public int lengthOfLastWord(String s) {
		int i = s.length() - 1;
		while (i >= 0 && s.charAt(i) == ' ') {  
			i--;  // 消掉末尾的space
		}
		if (i < 0)  // 说明没有word  // 如果s是空字符串，i == -1 
			return 0;
		int j = i;
		// 从最后开始往前，找到第一个' '
		while (j - 1 >= 0 && s.charAt(j - 1) != ' ') {  // 或者到了string的开头，或者是发现了" "
			j--;
		}
		return i - j + 1;
	}
}


// 03/20/15
// 先trim出去两端空白，然后从最后位开始，如果过了界限或者找到了' '，就返回这个length
public class Solution {
    public int lengthOfLastWord(String s) {
        s = s.trim();
        int l = s.length();
        for(int i = l-1; i >= 0; i--){
            if(s.charAt(i) == ' ') return l-i-1;
        }
        return l;
    }
}