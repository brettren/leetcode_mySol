

/**
 * The count-and-say sequence is the sequence of integers beginning as follows:
 * 1, 11, 21, 1211, 111221, ...
 * 
 * 1 is read off as "one 1" or 11. 11 is read off as "two 1s" or 21. 21 is read
 * off as "one 2, then one 1" or 1211.
 * 
 * Given an integer n, generate the nth sequence.
 * 
 * Note: The sequence of integers will be represented as a string.
 */

public class CountandSay {
	public String countAndSay(int n) {
		StringBuilder s1 = new StringBuilder("1");
		StringBuilder s2 = new StringBuilder();
		for (int i = 1; i < n; i++) {  // n-1个loop
			int j = 0;
			int len = s1.length();
			while (j < len) {
				int count = 1;
				char c = s1.charAt(j);
				while (j < len - 1 && s1.charAt(j + 1) == s1.charAt(j)) {  // 注意是连续的数字
					count++;  // 看到下一个 j+1也是同样的数字，就加一
					j++;
				}
				s2.append(count + "");  // 统计出某个数字连续的个数
				s2.append(c);
				j++;
			}
			s1 = s2;  // 记下上一个string   这里就是prev和curr的互换
			s2 = new StringBuilder();
		}
		return s1.toString();
	}
}


// 01/08/15
public class Solution {
    public String countAndSay(int n) {
        StringBuffer prev = new StringBuffer();
        StringBuffer cur = new StringBuffer();
        prev.append('1');
        for(int i = 1; i < n; i++){
            int l = prev.length();
            int j = 0;
            while(j < l){
                int count = 1;
                char c = prev.charAt(j);
                while(j < l-1 && prev.charAt(j) == prev.charAt(j+1)){
                    count++;
                    j++;
                }
                cur.append((char)(count+'0'));
                cur.append(c);
                j++;
            }
            prev = cur;
            cur = new StringBuffer();
        }
        return prev.toString();
    }
}

// 02/09/15
public class Solution {
    public String countAndSay(int n) {
        if(n == 0) return "";
        StringBuffer prev = new StringBuffer();
        StringBuffer cur = new StringBuffer();
        prev.append("1");
        for(int i = 2; i <= n; i++){
            int count = 1;
            char c = prev.charAt(0);
            for(int j = 1; j < prev.length(); j++){
                if(prev.charAt(j) == prev.charAt(j-1)){
                    count++;
                }
                else{
                    cur.append((char)(count+'0')).append(c);
                    count = 1;
                    c = prev.charAt(j);
                }
            }
            cur.append((char)(count+'0')).append(c);
            prev = cur;
            cur = new StringBuffer();
        }
        return prev.toString();
    }
}

// 03/14/15
// 需要一个prev的string表示上一个level，然后根据prev来逐位数出现的次数，添加到新的buffer
// 用单个指针来遍历，记下新出现的char，直到dup结束为止，记下count数量
// 不要忘了最后末尾的dup也要放入buffer
public class Solution {
    public String countAndSay(int n) {
        String prev = "1";
        if(n == 1) return prev;
        for(int i = 2; i <= n; i++){
            StringBuffer sb = new StringBuffer();
            int size = prev.length();
            int j = 1;
            int count = 1;
            char c = prev.charAt(0);
            while(j < size){
                if(prev.charAt(j) == c){
                    j++;
                    count++;
                }
                else{
                    sb.append((char)(count+'0')).append(c);
                    c = prev.charAt(j);
                    count = 1;
                    j++;
                }
            }
            sb.append((char)(count+'0')).append(c);
            prev = sb.toString();
        }
        return prev;
    }
}