
/**
 * Given a string, determine if it is a palindrome, considering only
 * alphanumeric characters and ignoring cases.
 * 
 * For example, "A man, a plan, a canal: Panama" is a palindrome. "race a car"
 * is not a palindrome.
 * 
 * Note:
 * 
 * Have you consider that the string might be empty? This is a good question to
 * ask during an interview.
 * 
 * For the purpose of this problem, we define empty string as valid palindrome.
 */

public class ValidPalindrome {
	public boolean isPalindrome(String s) {
		s = s.toUpperCase();
		int i = 0, j = s.length() - 1;
		while (i < j) {
			if (!isAlphabet(s.charAt(i))) {
				i++;  // 如果不是字母，就要skip
			} else if(!isAlphabet(s.charAt(j))) {
				j--;
			} else if (s.charAt(i) != s.charAt(j)) {
				return false;
			} else {
				i++;
				j--;
			}
		}
		return true;
	}
	
	private boolean isAlphabet(char c) {
		return (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9');
	}
}

// 01/02/15
public class ValidPalindrome {
	public boolean isPalindrome(String s) {
		int i = 0, j = s.length() - 1;
		while (i < j) {
			while (i < j && !Character.isLetterOrDigit(s.charAt(i))) i++;
			while (i < j && !Character.isLetterOrDigit(s.charAt(j))) j--;
			if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
				return false;
			}
			i++; j--;
		}
		return true;
	}
}

// 03/11/15
// 检查两端的指针所指的char是不是letter或digit，不是就跳过，如果都是就检查是否match
public class Solution {
    public boolean isPalindrome(String s) {
        int l = s.length();
        if(l == 0) return true;
        int i = 0;
        int j = l-1;
        while(i < j){
            if(!Character.isLetterOrDigit(s.charAt(i))){
                i++;
                continue;
            }
            if(!Character.isLetterOrDigit(s.charAt(j))){
                j--;
                continue;
            }
            if(Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) return false;
            i++;
            j--;
        }
        return true;
    }
}