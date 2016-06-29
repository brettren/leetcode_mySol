// Given an array of words and a length L, format the text such that each line has exactly 
// L characters and is fully (left and right) justified.

// You should pack your words in a greedy approach; that is, pack as many words as you can in 
// each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.

// Extra spaces between words should be distributed as evenly as possible. If the number of spaces 
// on a line do not divide evenly between words, the empty slots on the left will be assigned more 
// spaces than the slots on the right.

// For the last line of text, it should be left justified and no extra space is inserted between words.

// For example,
// words: ["This", "is", "an", "example", "of", "text", "justification."]
// L: 16.

// Return the formatted lines as:
// [
//    "This    is    an",
//    "example  of text",
//    "justification.  "
// ]
// Note: Each word is guaranteed not to exceed L in length.

import java.util.ArrayList;

public class TextJustification {
	public ArrayList<String> fullJustify(String[] words, int L) {
		ArrayList<String> ret = new ArrayList<String>();  // 每一行是一个string
		int length = words.length;
		if (length == 0) return ret;  // 没有words
		int start = 0, end = start;
		int len = 0;
		while (start < length) {
			StringBuffer line = new StringBuffer();  // 创建新的line buffer
			while (end < length) {  
				int sl = words[end].length();
				if (len + (end - start) + sl > L) {  // 尝试加入下一个word，看这一行line总的length是否超过L, end - start 表示有几个空格
					break;  // start是这一行第一个word的index，end是该行末word的index
				}
				len += sl;  // 一行里不包含空格的所有words的length
				end++;
			}
			// 上面的while loop用来填满一行
			end--;  // 最后试图加入的words[end]不成功，所以退回一个word
			if (start == end) {  // 说明一行只能放一个word
				line.append(words[start]);
				int spaceCount = L - words[start].length();  // 剩下需要几个空格
				for (int i = 0; i < spaceCount; i++) {
					line.append(' ');  // 剩下的全放入space
				}
				ret.add(line.toString());
			} else {
				boolean lastLine = end == length - 1;  // 判断是否是最后一行
				int spaceBase = lastLine ? 1 : (L - len) / (end - start);  // word之间需要space个数   8/3 = 2
				int bonus = lastLine ? 0 : (L - len) % (end - start); //(L - len) % (end - start)  8 % 3 = 2
				line.append(words[start]);
				for (int i = start + 1; i <= end; i++) {  // 这个for loop是先放space再放下一个word
					for (int j = 0; j < spaceBase; j++) {
						line.append(' ');
					}
					if (bonus > 0) {  // 每个word之间的间隔在bonus大于0的情况下需要加一个' '
						line.append(' ');
						bonus--;  // bonus分给最前面几个间隔
					}
					line.append(words[i]);
				}
				if (lastLine) {  // 剩下的用' '填满
					for (int i = 0; i < L - len - (end - start); i++) {
						line.append(' ');  // 如果是last line，就把剩下的空用space补齐
					}
				}
				ret.add(line.toString());
			}
			start = end + 1;  // 开始下一行的start word
			end = start;
			len = 0;  // 新的一行length初始为0
		}
		return ret;
	}
}

// 01/17/15
// 要注意三个情况，最后一行剩下的全部为空格填满; 或者一行只能有一个word; 或者一行按照space平均布局几个words
public class Solution {
    public List<String> fullJustify(String[] words, int L) {
        List<String> ret = new ArrayList<String>();
        int l = words.length;
        if(l == 0) return ret;
        int s = 0;
        int e = 0;
        while(s < l){
            int space = 0;
            int charL = 0;
            while(e < l && space+charL+words[e].length() <= L){
                charL += words[e].length();
                space++;
                e++;
            }
            e--;
            StringBuffer sb = new StringBuffer();
            if(e == l-1){  //For the last line of text
                sb.append(words[s]);
                for(int i = s+1; i <= e; i++){
                    sb.append(" ").append(words[i]);
                }
                for(int i = sb.length(); i < L; i++){
                    sb.append(" ");  // 剩下的全部是space
                }
            }
            else{
                if(s == e){  // only one word in a line
                    sb.append(words[s]);
                    for(int i = sb.length(); i < L; i++){
                        sb.append(" ");
                    }
                }
                else{
                    int totalspace = L - charL;
                    int base = totalspace/(space-1);  // base是space的基础空位个数
                    int bonus = totalspace%(space-1); // bonus是看余下多少个分配给前面几个space加一
                    for(int i = s; i < e; i++){
                        sb.append(words[i]);
                        for(int j = 0; j < base; j++){
                            sb.append(" ");
                        }
                        if(bonus > 0){
                            sb.append(" ");
                            bonus--;
                        }
                    }
                    sb.append(words[e]);  // 加上最后一个
                }
            }
            ret.add(sb.toString());
            s = e+1;
            e = s;
        }
        return ret;
    }
}