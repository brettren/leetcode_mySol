

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given two words (start and end), and a dictionary, find the length of
 * shortest transformation sequence from start to end, such that:
 * 
 * Only one letter can be changed at a time Each intermediate word must exist in
 * the dictionary For example,
 * 
 * Given: start = "hit" end = "cog" dict = ["hot","dot","dog","lot","log"]
 * 
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * 
 * Note:
 * 
 * Return 0 if there is no such transformation sequence. All words have the same
 * length. All words contain only lowercase alphabetic characters.
 */

public class WordLadder {
	public int ladderLength(String start, String end, HashSet<String> dict) {
		if (dict.size() == 0)
			return 0;
		int currentLevelcount = 1; // 一开始放入的start
		int nextLevelcount = 0;
		int step = 1;  // start也是ladder一部分
		boolean found = false;
		Queue<String> st = new LinkedList<String>();
		HashSet<String> visited = new HashSet<String>();
		st.add(start);  // 先把start放入queue
		while (!st.isEmpty()) {
			String s = st.remove(); // 从queue取出一个
			currentLevelcount--;  // 当前level少一个word
			if (stringDiff(s, end) == 1) {  // 找到了倒数第二个ladder，和target只差一位
				found = true;
				step++;  // 再下一步就是end
				break;
			} else {
				int length = s.length();
				StringBuffer sb = new StringBuffer(s);
				for (int i = 0; i < length; i++) {  // 遍历每一位
					for (int j = 0; j < 26; j++) {  // 每一位遍历26个字母
						char c = sb.charAt(i);
						sb.setCharAt(i, (char) ('a' + j));  // 尝试把指定char改为'a'到'z'
						if (dict.contains(sb.toString())  // dict要有这个word才能作ladder
								&& !visited.contains(sb.toString())) {
							nextLevelcount++;  // 表示下一个level多了一个可用的ladder
							st.add(sb.toString());
							visited.add(sb.toString());  // 加入queue的同时标记为visited
						}
						sb.setCharAt(i, c);  // 再改回原来的char
					}
				}
			}
			if (currentLevelcount == 0) { // 当前的level已经没有word了
				currentLevelcount = nextLevelcount;
				nextLevelcount = 0;
				step++;
			}
		}
		return found ? step : 0;
	}

	private int stringDiff(String s1, String s2) {
		int diff = 0;
		int length = s1.length();
		for (int i = 0; i < length; i++) {
			if (s1.charAt(i) != s2.charAt(i)) {
				diff++;  // 位比较，统计有几个不相同的位
			}
		}
		return diff;
	}
}






// 01/02/15   BFS
public class Solution {
    public int ladderLength(String start, String end, HashSet<String> dict) {
        if (dict == null || dict.size() == 0) {
            return 0;
        }

        Queue<String> queue = new LinkedList<String>();
        queue.add(start);
        dict.remove(start);
        int length = 1;

        while(!queue.isEmpty()) {
            int count = queue.size();  // 记下下个level里有几个string
            for (int i = 0; i<count; i++){  // for each string in a level
                String current = queue.poll();
                for (char c = 'a'; c <= 'z'; c++) {  // for 26 chars
                    for (int j=0; j < current.length(); j++) {  // for each digit
                        if (c == current.charAt(j)) {
                            continue;
                        }
                        String tmp = replace(current, j, c);
                        if (tmp.equals(end)) {
                            return length + 1;
                        }
                        if (dict.contains(tmp)){
                            queue.offer(tmp);
                            dict.remove(tmp);
                        }
                    }
                }
            }
            length++;
        }
        return 0;
    }

    private String replace(String s, int index, char c) {
        char[] chars = s.toCharArray();
        chars[index] = c;
        return new String(chars);
    }
}

// 01/03/15
public class Solution {
    public int ladderLength(String start, String end, Set<String> dict) {
        if(dict == null || dict.size() == 0) return 0;
        int l = start.length();
        if(l == 0) return 0;
        
        Queue<String> q= new LinkedList<String>();
        
        q.add(start);
        dict.remove(start);
        int ret = 1;
        
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++) {
                String current = q.poll();
                for(int j = 0; j < l; j++){
                    for(char c = 'a'; c <= 'z'; c++){
                        if(c == current.charAt(j)) continue;
                        String tmp = replace(current, c, j);
                        if(tmp.equals(end)) return ret+1;
                        if(dict.contains(tmp)){
                            dict.remove(tmp);
                            q.add(tmp);
                        }
                    }
                }
            }
            ret++;
        }
        return 0;
    }
    
    private String replace(String s, char c, int j){
        char[] chars = s.toCharArray();
        chars[j] = c;
        return new String(chars);
    }
}

// 03/07/15
// 由于需要得到depth，所以是level by level，每个level先记下size
// 内部的loop是对每个string，从首到尾，每个字母变化来check
public class Solution {
    public int ladderLength(String start, String end, Set<String> dict) {
        int l = start.length();
        if(l == 0) return 0;
        Queue<String> q = new LinkedList<>();
        int ret = 0;
        q.add(start);
        dict.remove(start);
        while(!q.isEmpty()){
            int size = q.size();
            ret++;
            for(int i = 0; i < size; i++){
                String origin = q.remove();
                for(int j = 0; j < l; j++){
                    StringBuffer sb = new StringBuffer(origin);
                    char c = sb.charAt(j);
                    for(char k = 'a'; k <= 'z'; k++){
                        if(c == k) continue;
                        sb.setCharAt(j, k);
                        String tmp = sb.toString();
                        if(tmp.equals(end)){
                            ret++;
                            return ret;
                        }
                        if(dict.contains(tmp)){
                            q.add(tmp);
                            dict.remove(tmp);
                        }
                    }
                }
            }
        }
        return 0;
    }
}