
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
//	��ͳ�����������Լ�ȥ����ҵ�ÿ�����ʣ���ǰ�����ǴӺ���ǰ��ʵ����࣬
//	��Ҫ��ת��Ҫô�Ƿ�ת��ĸ���Ӻ���ǰ����Ҫô��ת���ʣ���ǰ���󣩡�
//
//	�������������java�Դ���split���������������ԡ� �������и���뿴��ȥ��Ƚϼ�����ˡ�
	public String reverseWords(String s) {
		if(s==null||s.length()==0){
			return "";
		} 
		String[] array=s.split(" +");   //��ʾ1~n���ո� split 2�� 5����splitһ��
		StringBuilder sb=new StringBuilder();
		for(int i=array.length-1;i>=0;i--){  //���������һ������ǰ����
			sb.append(array[i]).append(" "); //��һ���ʺ��һ����
		}
		return sb.length()==0?"":sb.toString().trim();
	}
}


public class ReverseWordsinaString {
//	��ͳ�����������Լ�ȥ����ҵ�ÿ�����ʣ���ǰ�����ǴӺ���ǰ��ʵ����࣬
//	��Ҫ��ת��Ҫô�Ƿ�ת��ĸ���Ӻ���ǰ����Ҫô��ת���ʣ���ǰ���󣩡�

	public String reverseWords(String s) {
		if(s==null||s.length()==0){
			return "";
		} 
		String s1 = s.trim();
		if(s1.length() == 0) return "";	  
		StringBuffer ret = new StringBuffer();
		int i=s1.length()-1, j=s1.length();
		for(;i>=0;i--){  //���������һ������ǰ����
			if (s1.charAt(i) == ' ' && s1.charAt(i+1) != ' ') {
				ret.append(s1.substring(i+1,j)).append(" ");
				j = i; 
			}
			else if(s1.charAt(i) == ' ' && s1.charAt(i+1) == ' '){
				j = i;
			}
		}
		ret.append(s1.substring(0,j)).append(" ");  // �ѵ�һ��word����
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
        sb.append(s.substring(0, end));  // �ѵ�һ��word����
        return sb.toString();
    }
}

// 03/02/15
// ���ĸ��������
public class Solution {
    public String reverseWords(String s) {
        s = s.trim();
        int l = s.length();
        StringBuffer sb = new StringBuffer();
        if(l <= 1) return s;  // ��ֻ��һ��char��ֱ�ӷ���
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
