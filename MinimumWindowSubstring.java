

/**
 * Given a string S and a string T, find the minimum window in S which will
 * contain all the characters in T in complexity O(n).
 * 
 * For example, S = "ADOBECODEBANC" T = "ABC" Minimum window is "BANC".
 * 
 * Note: 
 * 
 * If there is no such window in S that covers all characters in T, return the emtpy string "".
 * 
 * If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 */

public class MinimumWindowSubstring {
	public String minWindow(String S, String T) {
		int[] hasFound = new int[256];  // as hashmap  保存的是window里的char个数
		int[] needFound = new int[256];
		int diffCount = T.length();  // T = "ABC"
		int length = S.length();  // S = "ADOBECODEBANC"
		String window = "";
		int size = Integer.MAX_VALUE;
		if (length == 0 || diffCount == 0)
			return window;
		for (int l = 0; l < diffCount; l++) {
			needFound[T.charAt(l)]++;  // 把T里需要找到的char数量都记下来
		}
		int i = 0, j = 0;
		while (j < length) {  // 每次j前进一位，就检查i到j
			char c = S.charAt(j);
			if (needFound[c] > 0 && ++hasFound[c] <= needFound[c]) {  //如果这个char是needFound，同时hasFound的个数没有超过needFound
				diffCount--;     								// 注意每次循环都会 ++hasFound[c]，所以可能是hasFound[c] > needFound[c]
			}
			if (diffCount == 0) {  // 说明T里所有的char现在都在window里包括了，下面检查这个window
				while (i <= j) {  // i是当前window的left，j是right，从头开始尝试缩减window
					char h = S.charAt(i);
					i++;
					if (needFound[h] > 0 && --hasFound[h] < needFound[h]) { //如果--hasFound[h] >= needFound[h],说明这是个多余的char
															//如果--hasFound[h] < needFound[h], 说明这个char不可缺少，无法再缩小window
						if (j - i + 1 < size) {
							size = j - i + 2;  // 记录到目前的最小window size
							window = S.substring(i - 1, j + 1);  // 因为i++自增过了，而原来的[i]不可少，所以再退回一位i-1
						}                       // [i-1,j]
						diffCount++;
						break;  // 这时说明已经不能再缩小window，跳出循环
					}
				}
			}
			j++;  // 因为自增后的i到j已不可能满足cover T，所以j++
		}
		return window;
	}
}


// 01/10/15
public class Solution {
    public String minWindow(String S, String T) {
        String window = null;
        int ls = S.length();
        int lt = T.length();
        if(ls < lt) return "";
        if(lt == 0) return "";
        HashMap<Character, Integer> hasfound = new HashMap<Character, Integer>();
        HashMap<Character, Integer> needfound = new HashMap<Character, Integer>();
        for(int i = 0; i < lt; i++){
            char c = T.charAt(i);
            if(needfound.containsKey(c)){
                needfound.put(c, needfound.get(c)+1);
            }
            else{
                needfound.put(c, 1);   
            }
        }
        int diffCount = 0;
        int start = 0;
        for(int i = 0; i < ls; i++){
            char c = S.charAt(i);
            if(!needfound.containsKey(c)){
                continue;
            }
            
            if(hasfound.containsKey(c)){
                hasfound.put(c, hasfound.get(c)+1);
            }
            else{
                hasfound.put(c, 1);   
            }
            
            if(hasfound.get(c) <= needfound.get(c)){
                count++;
            }
            
            if(count == lt){  //等到substring包括了所有的T内容，再来缩减这个substring
                while(start < ls){
                    char ch = S.charAt(start);
                    if(!needfound.containsKey(ch)){
                        start++;
                        continue;
                    }
                    if(needfound.get(ch) < hasfound.get(ch)){
                        hasfound.put(ch, hasfound.get(ch)-1);
                        start++;
                        continue;
                    }
                    break;
                }
                
                if(window == null || i-start+1 < window.length()){
                    window = S.substring(start, i+1);
                }
                
            }
        }
        if(window == null) return "";
        return window;
    }
}

// 03/07/15
// 先是不断往后探索，直到找到一个window，然后从头开始缩小window，不能再缩小了就是一个sol，和ret比较
// 每次往后探索都增大hasfound
public class Solution {
    public String minWindow(String S, String T) {
        int ls = S.length();
        int lt = T.length();
        if(ls == 0) return "";
        if(ls < lt) return "";
        String ret = null;
        int[] needfound = new int[256];
        for(int i = 0; i < lt; i++){
            int idx = T.charAt(i);
            needfound[idx]++;
        }
        int[] hasfound = new int[256];
        int s = 0;
        int count = 0; // 用来track已经找到T中几个char
        for(int e = 0; e < ls; e++){
            int idx = S.charAt(e);
            if(needfound[idx] == 0) continue;
            hasfound[idx]++;
            if(hasfound[idx] <= needfound[idx]){
                count++;
            }
            if(count >= lt){
                int sidx = S.charAt(s);
                while(needfound[sidx] == 0 || hasfound[sidx] > needfound[sidx]){ // 不断缩小window，直到min window为止
                    hasfound[sidx]--;
                    s++;
                    sidx = S.charAt(s);
                }
                if(ret == null || (ret.length() > (e-s+1))){
                    ret = S.substring(s, e+1);
                }
            }
        }
        if(ret == null) return "";
        return ret;
    }
}