

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given a string s and a dictionary of words dict, add spaces in s to construct
 * a sentence where each word is a valid dictionary word.
 * 
 * Return all such possible sentences.
 * 
 * For example, given s = "catsanddog", dict = ["cat", "cats", "and", "sand", "dog"].
 * 
 * A solution is ["cats and dog", "cat sand dog"].
 */

public class WordBreakII {
	public ArrayList<String> wordBreak(String s, Set<String> dict) {
		HashSet<String> wordMap = new HashSet<String>(dict);
		int len = s.length();
		boolean[] strMap = new boolean[len + 1];
		ArrayList<String> ret = new ArrayList<String>();
		strMap[0] = true;
		for (int i = 1; i <= len; i++) {
			for (int j = 0; j < i; j++) {
				String ww = s.substring(j, i);
				if (strMap[j] && wordMap.contains(ww)) {
					strMap[i] = true;  // 预处理   在各个位上标记能形成几个words的位置
				}
			}
		}
		if (!strMap[len])  // 如果整个string不能分为几个word，就没有sol, 提前结束程序
			return ret;
		StringBuffer sb = new StringBuffer();
		search(s, dict, 0, len, strMap, sb, ret);

		return ret;
	}

	private void search(String s, Set<String> dict, int st, int len,
			boolean[] strMap, StringBuffer sb, ArrayList<String> ret) {
		if (st >= len) {			
			ret.add(sb.toString().trim()); // words之间
		} else {
			for (int ll = st + 1; ll <= len; ll++) {
				String tmp = s.substring(st, ll);
				if (strMap[st] && dict.contains(tmp)) {  // 这里可以省略strMap[st]的判断，因为能进入这个递归子程序，说明strMap[st]肯定是1
					sb.append(" ").append(tmp);
					search(s, dict, ll, len, strMap, sb, ret);
					for (int i = 0; i <= tmp.length(); i++) {
						sb.deleteCharAt(sb.length() - 1); // 移除这个word加" "
					}					
				}
			}
		}
	}
}


// 01/13/15
public class Solution {
    public List<String> wordBreak(String s, Set<String> dict) {
        int l = s.length();
        List<String> ret = new ArrayList<String>();
        if(l == 0) return ret;
        boolean[] map = new boolean[l+1];
        map[0] = true;
        
        for(int i = 1; i <= l; i++){
            for(int j = 0; j < l; j++){
                if(map[j] == true && dict.contains(s.substring(j, i))){
                    map[i] = true;
                    break;
                }
            }
        }
        if(map[l] == false) return ret;
        StringBuffer sb = new StringBuffer();
        helper(s, dict, map, ret, sb, 0);
        return ret;
    }
    
    private void helper(String s, Set<String> dict, boolean[] map, List<String> ret, StringBuffer sb, int idx){
        int l = s.length();
        if(idx >= l){
            ret.add(sb.toString().trim());  // 注意要trim删除最后的" "
            return;
        }
        for(int i = idx; i < l; i++){
            String tmp = s.substring(idx, i+1);
            if(map[i+1] && dict.contains(tmp)){
                sb.append(tmp);
                sb.append(" ");
                helper(s, dict, map, ret, sb, i+1);
                sb.deleteCharAt(sb.length()-1);
                for(int j = 0; j < tmp.length(); j++){
                    sb.deleteCharAt(sb.length()-1);
                }
            }
        }
    }
}

// 03/06/15
// 用一维的map记录在某个位置能否形成word break
// 再用递归的方法，当某个位置map[] = true并且substring属于dict，就可以作为一个word
public class Solution {
    public List<String> wordBreak(String s, Set<String> dict) {
        List<String> ret = new ArrayList<>();
        int l = s.length();
        if(l == 0) return ret;
        boolean[] valid = new boolean[l];  // 建立valid数组，可以避免超时
        for(int i = 0; i < l; i++){
            for(int j = 0; j <= i; j++){
                if(dict.contains(s.substring(j, i+1)) && (j == 0 || valid[j-1])){
                    valid[i] = true;  // prune tree
                }
            }
        }
        if(valid[l-1] == false) return ret;
        StringBuffer sb = new StringBuffer();
        helper(ret, sb, s, dict, valid, 0);
        return ret;
    }
    
    public void helper(List<String> ret, StringBuffer sb, String s, Set<String> dict, boolean[] valid, int start){
        int l = s.length();
        if(start >= l){
            ret.add(sb.toString().trim());
            return;
        }
        for(int i = start; i < l; i++){
            String tmp = s.substring(start, i+1);
            if(dict.contains(tmp) && valid[i]){
                sb.append(tmp).append(" ");
                helper(ret, sb, s, dict, valid, i+1);
                for(int j = 0; j < tmp.length(); j++){
                    sb.deleteCharAt(sb.length()-1);
                }
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }
}