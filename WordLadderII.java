import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Given two words (start and end), and a dictionary, find all shortest
 * transformation sequence(s) from start to end, such that:
 * 
 * Only one letter can be changed at a time Each intermediate word must exist in
 * the dictionary For example,
 * 
 * Given: start = "hit" end = "cog" dict = ["hot","dot","dog","lot","log"]
 * 
 * Return:
 * 
 * [ ["hit","hot","dot","dog","cog"], ["hit","hot","lot","log","cog"] ] Note:
 * 
 * All words have the same length. All words contain only lowercase alphabetic
 * characters.
 */


public class WordLadderII {

	public ArrayList<ArrayList<String>> findLadders(String start, String end,
			HashSet<String> dict) {
		ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
		Map<String, ArrayList<String>> prevMap = new HashMap<String, ArrayList<String>>();
		dict.add(start);
		dict.add(end);
		for (String d : dict) {
			prevMap.put(d, new ArrayList<String>()); // 以每个word为key，分配一个空的list，来存能到达这个word的上一个words
		}
		ArrayList<HashSet<String>> candidates = new ArrayList<HashSet<String>>();
		candidates.add(new HashSet<String>());  // level 0 
		candidates.add(new HashSet<String>());	// level 1
		int current = 0;
		int previous = 1;
		candidates.get(0).add(start);  // level 0 里只有start word
		// BFS
		while (true) {
			current = current == 0 ? 1 : 0;  // 1,0,1,0,1,0...
			previous = previous == 0 ? 1 : 0; // 0,1,0,1,0,1...  用candidates.get(current)和candidates.get(previous)两个作buffer
			// 先把previous的word都标为visited，再把current hashset清空好放下一个level的word
			for (String d : candidates.get(previous)) {  // 上一个level的hashset
				dict.remove(d);  // 从dict移走表示已经visited
			}
			candidates.get(current).clear();  // 清空当前level的hashset

			for (String wd : candidates.get(previous)) {  // 从上一个level取word
				for (int pos = 0; pos < wd.length(); ++pos) {
					StringBuffer word = new StringBuffer(wd);
					for (int i = 'a'; i <= 'z'; ++i) {
						word.setCharAt(pos, (char) i);
						if (dict.contains(word.toString())) {  // 确认可以当做ladder,并且还是unvisited
							prevMap.get(word.toString()).add(wd);  // word是改变后的，wd是原来的   current word,  prev words list
							candidates.get(current).add(word.toString());  // 下一个level的words，hat,hbt,hct...
						}
					}
				}
			}
			// 直到遍历完previous level的words
			if (candidates.get(current).size() == 0) {  // 说明下一个level没有words，不能再延伸探索了
				return ret;
			}
			if (candidates.get(current).contains(end)) {  // 说明已经找到target，并且都是shortest path
				break;
			}
		}

		ArrayList<String> path = new ArrayList<String>();
		GeneratePath(prevMap, path, end, ret);  // 从end开始倒推回去

		return ret;
	}

	private void GeneratePath(Map<String, ArrayList<String>> prevMap,
			ArrayList<String> path, String word,
			ArrayList<ArrayList<String>> ret) {
		if (prevMap.get(word).size() == 0) {  // 说明这个word没有prev words了，是起始点，其实就是start
			path.add(0, word);  
			ArrayList<String> curPath = new ArrayList<String>(path);  //把这个path复制保存
			ret.add(curPath);
			path.remove(0);  // 保存后注意再remove这个word
			return;
		}

		path.add(0, word); // 从end开始倒推回start，所以是不断在list的前面插入
		for (String pt : prevMap.get(word)) {  // 对当前word，尝试每一个prev word
			GeneratePath(prevMap, path, pt, ret);
		}
		path.remove(0);
	}
}


// 06/25/15
// 这个方法应该对，但是memory exceed
public class Solution {
    public ArrayList<ArrayList<String>> findLadders(String start, String end,
			HashSet<String> dict) {  // start 和 end 都在dict里了
		ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
		Map<String, ArrayList<String>> prevMap = new HashMap<>();

		for (String s: dict) {
			prevMap.put(s, new ArrayList<String>());
		}

		Queue<String> q = new LinkedList<>();
		q.add(start);

		int l = start.length();

		boolean found = false;
		while(!q.isEmpty()){
			int size = q.size();
			for(int i = 0; i < size; i++){  // iterate one level's string
				String origin = q.remove(); 
				dict.remove(origin);
				for (int j = 0; j < l; j++) {
				    StringBuffer clone = new StringBuffer(origin); // 这里注意每一个index都新建一个clone
					for(char ch = 'a'; ch <= 'z'; ch++){
						clone.setCharAt(j, ch);
						String next = new String(clone);
						if(dict.contains(next)){
							prevMap.get(next).add(origin);
							q.add(next);
						}
						if(next.equals(end)) found = true;
					}
				}
			}
			if(found == true) break;
		}

		if(found == false) return ret;
		
		ArrayList<String> path = new ArrayList<>();
		
		generate(prevMap, path, end, ret);

		return ret;
	} 

	public void generate(Map<String, ArrayList<String>> prevMap,
			ArrayList<String> path, String s, 
			ArrayList<ArrayList<String>> ret){
		if(prevMap.get(s).size() == 0){
			path.add(0, s);
			ret.add(new ArrayList<>(path));
			path.remove(0);
			return;
		}
		ArrayList<String> prevList = prevMap.get(s);
		path.add(0, s);
		for(String word: prevList){
			generate(prevMap, path, word, ret);
		}
		path.remove(0);
	}
}