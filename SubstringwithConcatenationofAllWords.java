

import java.util.ArrayList;
import java.util.HashMap;

/**
 * You are given a string, S, and a list of words, L, that are all of the same
 * length. Find all starting indices of substring(s) in S that is a
 * concatenation of each word in L exactly once and without any intervening
 * characters.
 * 
 * For example, given: S: "barfoothefoobarman" L: ["foo", "bar"]
 * 
 * You should return the indices: [0,9]. (order does not matter).  对应"barfoo", "foobar"
 */

//要求词典里的词当中不能被其他词隔开，并且每个词必须而且只能出现一次
public class SubstringwithConcatenationofAllWords {
	public ArrayList<Integer> findSubstring(String S, String[] L) {
		int slen = S.length();
		int llen = L.length;
		ArrayList<Integer> ret = new ArrayList<Integer>();
		if (llen == 0)
			return ret;
		int wordlen = L[0].length(); // 数组里词长度都相同
		// 把L里词和出现的次数都放到hashmap里 (L的值可能有重复)
		HashMap<String, Integer> smap = new HashMap<String, Integer>();  // <string, 次数>
		for (String s : L) {
			if (smap.containsKey(s)) {
				smap.put(s, smap.get(s).intValue() + 1);
			} else {
				smap.put(s, 1);  // 把L[]里的string放入hashmap
			}
		}
		// 因为要每个词都匹配上 所以 如果小于n*m就肯定匹配不上不用检查了
		if (llen * wordlen > slen) // L里string的个数 * 每个string的length
			return ret;
		int start = 0, end = llen * wordlen - 1;
		int index = 0;
		while (end < slen) {
			index = check(S, start, end, wordlen, L, smap);
			if (index >= 0)  // 说明找到一个
				ret.add(index);
			start++;
			end++;
		}
		return ret;
	}

	private int check(String s, int start, int end, int wordlen, String[] L,
			HashMap<String, Integer> smap) {  // 也就是check在start到end这段substring，是否包含了L[]所有的strings
		HashMap<String, Integer> exitMap = new HashMap<String, Integer>(); // 这个hashmap是记录当前start到end之间的words
		int sidx = start;
		while (sidx <= end) {
			String k = s.substring(sidx, sidx + wordlen); // 取一个word的宽度
			if (smap.containsKey(k)) {
				if (exitMap.containsKey(k)) {
					Integer i = exitMap.get(k);
					if (i == smap.get(k))  // 说明已经超过了L里这个word的个数，直接返回false
						return -1;
					else {
						exitMap.put(k, i + 1);
					}
				} else {
					exitMap.put(k, 1);
				}
				sidx += wordlen;  // 到下一个string
			} else {  // 这个string在dict里不存在说明这段start到end不是sol之一
				return -1;
			}
		}
		return start;
	}
}


// 03/08/15
// 就是尝试逐个开头检查，用needfound和hasfound两个map比较，并且维持一个foundcount，当达到需要找到的count时就说明是满足要求的substring
// 要注意的是先check needfound里有没有，然后check hasfound 有没有，并且是否已经找到needfound里需要找到的个数
public class Solution {
    public List<Integer> findSubstring(String S, String[] L) {
        List<Integer> ret = new ArrayList<>();
        int count = L.length;
        if(count == 0) return ret;
        int ls = S.length();
        if(ls == 0) return ret;
        int wl = L[0].length();
        Map<String, Integer> needfound = new HashMap<>();
        for(String s: L){
            if(needfound.containsKey(s)){
                needfound.put(s, needfound.get(s)+1);
            }
            else{
                needfound.put(s, 1);
            }
        }
        for(int i = 0; i <= ls-wl*count; i++){
            int foundcount = 0;
            Map<String, Integer> hasfound = new HashMap<>();
            for(int j = 0; j < wl*count; j+=wl){
                String tmp = S.substring(i+j, i+j+wl);
                if(!needfound.containsKey(tmp)) break;
                if(hasfound.containsKey(tmp)){
                    if(hasfound.get(tmp) >= needfound.get(tmp)) break;
                    hasfound.put(tmp, hasfound.get(tmp)+1);
                }
                else{
                    hasfound.put(tmp, 1);
                }
                foundcount++;
                if(foundcount == count){
                    ret.add(i);
                }
            }
        }
        return ret;
    }
}