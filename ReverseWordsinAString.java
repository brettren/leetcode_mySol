
//Given an input string, reverse the string word by word.
//
//For example,
//Given s = " the sky is blue ",
//return " blue is sky the ". 

//
//What constitutes a word?
//A sequence of non-space characters constitutes a word.

//Could the input string contain leading or trailing spaces?
//Yes. However, your reversed string should not contain leading or trailing spaces.

//How about multiple spaces between two words?
//Reduce them to a single space in the reversed string.

public class ReverseWordsinaString {
//	正统的做法就是自己去逐个找到每个单词，从前往后还是从后往前其实都差不多，
//	都要翻转：要么是翻转字母（从后往前）、要么翻转单词（从前往后）。
//
//	这里给出的是用java自带的split方法，帮助我们以“ ”进行切割，代码看上去会比较简洁明了。
	public String reverseWords(String s) {
		if(s==null||s.length()==0){
			return "";
		} 
		String[] array=s.split(" +");   //表示1~n个空格 split 2个 5个都split一次
		StringBuilder sb=new StringBuilder();
		for(int i=array.length-1;i>=0;i--){  //从数组最后一个词往前遍历
			sb.append(array[i]).append(" "); //放一个词后加一个空
		}
		return sb.length()==0?"":sb.toString().trim();
	}
}


public class ReverseWordsinaString {
//	正统的做法就是自己去逐个找到每个单词，从前往后还是从后往前其实都差不多，
//	都要翻转：要么是翻转字母（从后往前）、要么翻转单词（从前往后）。

	public String reverseWords(String s) {
		if(s==null||s.length()==0){
			return "";
		} 
		String s1 = s.trim();
		if(s1.length() == 0) return "";	  
		StringBuffer ret = new StringBuffer();
		int i=s1.length()-1, j=s1.length();
		for(;i>=0;i--){  //从数组最后一个词往前遍历
			if (s1.charAt(i) == ' ' && s1.charAt(i+1) != ' ') {
				ret.append(s1.substring(i+1,j)).append(" ");
				j = i; 
			}
			else if(s1.charAt(i) == ' ' && s1.charAt(i+1) == ' '){
				j = i;
			}
		}
		ret.append(s1.substring(0,j)).append(" ");  // 把第一个word放入
		return ret.toString().trim();
	}
}


// 01/13/15
public class Solution {
    public String reverseWords(String s) {
        if(s == null) return null;
        s = s.trim();
        int l = s.length();
        StringBuffer sb = new StringBuffer();
        if(l <= 1) return s;
        int end = l;
        for(int i = l-2; i >= 0; i--){
            char c = s.charAt(i);
            char ch = s.charAt(i+1);
            if(c != ' ' && ch != ' ') continue;
            
            if(c == ' ' && ch != ' '){
                sb.append(s.substring(i+1, end));
                sb.append(' ');
            }
            
            if(c != ' ' && ch == ' '){
                end = i+1;
            }
        }
        sb.append(s.substring(0, end));  // 把第一个word放入
        return sb.toString();
    }
}

// 03/02/15
// 分四个情况分析
public class Solution {
    public String reverseWords(String s) {
        s = s.trim();
        int l = s.length();
        StringBuffer sb = new StringBuffer();
        if(l <= 1) return s;  // 当只有一个char，直接返回
        int j = l;
        int i = l-2;
        while(i >= 0){
            char c0 = s.charAt(i);
            char c1 = s.charAt(i+1);
            if((c0 == ' ') && (c1 == ' ')) {
                i--;
                continue;
            }
            if((c0 != ' ') && (c1 != ' ')) {
                i--;
                continue;
            }
            if((c0 == ' ') && (c1 != ' ')){
                sb.append(s.substring(i+1, j)).append(' ');
                i--;
                continue;
            }
            if((c0 != ' ') && (c1 == ' ')) {
                j = i+1;
                i--;
                continue;
            }
        }
        sb.append(s.substring(i+1, j)).append(' ');
        return sb.toString().trim();
    }
}


public class Solution {
    public String reverseWords(String s) {
        s = s.trim();
        if(s.length() == 0) return s;
        StringBuffer sb = new StringBuffer();
        String[] array = s.split("\\ +");
        for(int i = array.length-1; i >= 0; i--){
            sb.append(array[i]).append(' ');
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
}
