// Given a string S, you are allowed to convert it to a palindrome by adding 
// characters in front of it. Find and return the shortest palindrome you can 
// find by performing this transformation.

// For example:

// Given "aacecaaa", return "aaacecaaa".

// Given "abcd", return "dcbabcd".


// 字符串匹配之KMP算法
public class Solution {
    public String shortestPalindrome(String s) {  
        String result = "";  
        if(s.length() == 0) return result;  
        int[] prefix = new int[s.length() * 2];  
        String mirror = s + new StringBuilder(s).reverse().toString();  
        for(int i = 1; i < s.length() * 2; i++) {  
            int j = prefix[i-1];  
            while(mirror.charAt(j) != mirror.charAt(i) && j > 0)  
                j = prefix[j-1];  
            if(mirror.charAt(i) == mirror.charAt(j))  
                prefix[i] = j + 1;  
            else  
                prefix[i] = 0;  
        }  
        int count = s.length() - prefix[s.length() * 2 -1];  
        result = new StringBuilder(s.substring(s.length()-count, s.length())).reverse().toString() + s;  
        return result;  
    }  
}