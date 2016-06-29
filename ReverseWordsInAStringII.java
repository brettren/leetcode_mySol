// 要求in-place
// 
// 该题在LeetCode中假设开头和结尾没有空格，而且单词之间只有一个空格。但其实不需要这些假设也是可以的，就是代码会比较复杂。
// 思路就是两步走，第一步就是将整个字符串翻转。然后从头逐步扫描，将每个遇到单词再翻转过来。

public class Solution {
    public void reverseWords(char[] s) {
    	int l = s.length;
    	reverse(s, 0, l-1); //将整个字符串翻转
    	int begin = 0;
    	for(int i = 0; i <= l; i++){
    		if(i == l || s[i] == ' '){ // 当遇到空格或者字符串末尾，可以reverse一个word
    			reverse(s, begin, i-1);
    			begin = i+1;
    		}
    	}
    }

    public void reverse(char[] s, int begin, int end){
    	while(begin < end){
    		char t = s[begin];
    		s[begin] = s[end];
    		s[end] = t;
    		begin++;
    		end--;
    	}
    }
}