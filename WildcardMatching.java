// Implement wildcard pattern matching with support for '?' and '*'.

// '?' Matches any single character.
// '*' Matches any sequence of characters (including the empty sequence).

// The matching should cover the entire input string (not partial).

// The function prototype should be:
// bool isMatch(const char *s, const char *p)

// Some examples:
// isMatch("aa","a") → false
// isMatch("aa","aa") → true
// isMatch("aaa","aa") → false
// isMatch("aa", "*") → true
// isMatch("aa", "a*") → true
// isMatch("ab", "?*") → true
// isMatch("aab", "c*a*b") → false


// 递归容易超时，非递归效率更高
// 贪心的策略，能匹配就一直往后遍历，匹配不上了就看看前面有没有'*'来救救场，再从'*'后面接着试。
public class Solution {
    public boolean isMatch(String s, String p) {  
        int i = 0;  
        int j = 0;  
        int star = -1;  
        int mark = -1;  
        while (i < s.length()) {  
            if (j < p.length()  
                    && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {  
                ++i;  
                ++j;  
            } else if (j < p.length() && p.charAt(j) == '*') {  
            	// 注意这里是尽可能的跳过'*',只需记下上一个'*'的位置，因为'*'能够对应所有的char
                star = j++;  //贪心的策略，先跳过这个'*'，当做是empty sequence，star用来记录上一个'*'的位置
                mark = i;  // 记下与'*'对应的char位置
            } else if (star != -1) {  //匹配不上了就看看前面有没有'*'来救救场
                j = star + 1;  // 回到上一个'*'的位置对应一下，继续尝试跳过它
                i = ++mark;  
            } else {  
                return false;  
            }  
        }  
        while (j < p.length() && p.charAt(j) == '*') {  // 如果j后面剩下的全是'*'，就可以当做是empty sequence 
            ++j;  
        }  
        return j == p.length();  
    } 
}



// 01/18/15
public class Solution {
    public boolean isMatch(String s, String p) {
        int ls = s.length();
        int lp = p.length();
        if(s.equals(p)) return true;
        if(lp == 0) return false;
        int i = 0, j = 0;
        int star = -1;
        int mark = -1;
        while(i < ls){
            if(j < lp && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')){
                i++;
                j++;
            }
            else if(j < lp && p.charAt(j) == '*'){  //先assume这个'*'不匹配任何字符
                star = j;
                j++;
                mark = i;
            }
            else if(star != -1){  // 如果当前都不能match，那就回到上一个'*', 尝试让'*'匹配一个字符
                j = star;
                i = mark;
                i++;
            }
            else{
                return false;
            }
        }
        while(j < lp){
            if(p.charAt(j) == '*'){
                j++;
            }
            else{
                return false;
            }
        }
        return true;
    }
}

// 02/08/15
public class Solution {
    public boolean isMatch(String s, String p) {
        int ls = s.length();
        int lp = p.length();
        int i = 0, j = 0;
        int mark = -1;
        int pos = -1;
        while(i < ls){
            if(j < lp && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')){
                i++;
                j++;
                continue;
            }
            if(j < lp && p.charAt(j) == '*'){
                mark = j;
                pos = i;
                j++;
            }
            else{
                if(mark != -1){
                    j = mark;
                    pos++; // 让'*' 多match一位
                    i = pos;
                }
                else{
                    return false;
                }
            }
        }
        while(j < lp && p.charAt(j) == '*') j++;
        return j == lp;
    }
}

// 03/04/15
public class Solution {
    public boolean isMatch(String s, String p) {
        int ls = s.length();
        int lp = p.length();
        int star = -1;  // 记录上一个'*'在p的位置
        int mark = -1;  // 记录上一个'*'match后在s开始的位置
        int i = 0;
        int j = 0;
        while(i < ls){
            if(j < lp && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')){
                i++;
                j++;
                continue;
            } 
            else if(j < lp && p.charAt(j) == '*'){
                mark = i;
                star = j;
                j++;
                continue;
            }
            else{  // 两种情况，j已经超限了，但是i还在ls内；或者对应的char不match，而且j也不是'*'
                if(star != -1){
                    mark++;
                    i = mark;
                    j = star+1;
                }
                else{
                    return false;
                }
            }
        }
        while(j < lp && p.charAt(j) == '*'){  // 当i == ls的时候，j剩下的必须都是'*' 才能轮空
            j++;
        }
        return j == lp;
    }
}