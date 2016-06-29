

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Given a string s and a dictionary of words dict, determine if s can be
 * segmented into a space-separated sequence of one or more dictionary words.
 * 
 * For example, given s = "leetcode", dict = ["leet", "code"].
 * 
 * Return true because "leetcode" can be segmented as "leet code".
 */

public class WordBreak {
	public boolean wordBreak(String s, Set<String> dict) {  // Set只是个interace，需要改成对象
		HashSet<String> wordMap = new HashSet<String>(dict);
		int len = s.length();
		boolean[] strMap = new boolean[len + 1];
		strMap[0] = true; // 表示空的word
		for (int i = 1; i <= len; i++) {
			for (int j = 0; j < i; j++) {
				if (strMap[j] && wordMap.contains(s.substring(j, i))) {  //strMap[j]表示前面j位可以分为几个words
					strMap[i] = true;  // 表示[1,i]能够由words组成
				}
			}
		}
		return strMap[len];
	}
}


public class Solution {
    public boolean wordBreak(String s, Set<String> dict) {
        int l = s.length();
        if(l == 0) return true;
        boolean[] ret = new boolean[l];
        ret[0] = dict.contains(s.substring(0,1)) ? true : false;
        for(int i = 1; i < l; i++){
            for(int j = 0; j <= i; j++){
                if(j > 0 && ret[j-1] == true && dict.contains(s.substring(j, i+1))){
                    ret[i] = true;
                    break;
                }
                if(j == 0 && dict.contains(s.substring(j, i+1))){
                    ret[i] = true;
                    break;
                }
            }
        }
        return ret[l-1];
    }
}

// 03/11/15
// 因为有mapset，所以不需要建立二维数组来记录[i,j]是否是word，用dp来记录[0, i)是否能够由word组成
// 这里只需要逐位去确定能否组成，一旦满足条件就继续下一位
public class Solution {
    public boolean wordBreak(String s, Set<String> dict) {
        int l = s.length();
        boolean[] ret = new boolean[l];
        for(int i = 0; i < l; i++){
            for(int j = 0; j <= i; j++){
                if(j > 0 && ret[j-1] && dict.contains(s.substring(j, i+1))){
                    ret[i] = true;
                    break;
                }
                if(j == 0 && dict.contains(s.substring(j, i+1))){
                    ret[i] = true;
                    break;
                }
            }
        }
        return ret[l-1];
    }
}