	

import java.util.HashMap;

/**
 * Given a string, find the length of the longest substring without repeating
 * characters. For example, the longest substring without repeating letters for
 * "abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest substring
 * is "b", with the length of 1.
 */
// “滑动窗口”的做法，有点类似maximum subarray的滑动窗口。 
// 比方说 abcabccc 当你右边扫描到abca的时候你得 把第一个a删掉得到bca 然后"窗口"继续向右滑动，
// 每当加到一个新char的时候，左边检查有无重复的（用hashmap）然后如果没重复就能正常加 有重复 
// 的话 就左边扔掉一部分（从最左到重复char这段扔掉） 在后在这个过程中记录最大的
public class LongestSubstringWithoutRepeatingCharacters {
	public int lengthOfLongestSubstring(String s) {
		if (s.length() == 0)
			return 0;
		int i = 0, j = 0;
		int result = 0;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>(); // <char, index>
		while (j < s.length()) {  // j从0到length-1
			Character c = new Character(s.charAt(j));
			if (!map.containsKey(c)) {
				map.put(c, j);
			} else {  // 说明这个char之前已发现过
				int length = j - i;  // i表示string的start，j表示发现有重复的char的index，j-i就是longest string
				if (result < length) {
					result = length;  // 找到longest length
				}
				Integer index = map.get(c); // 找到这个char上次出现的index
				i = Math.max(i, index + 1);  // 这个char上次出现的index可能在i前面，可能在i后面
				map.put(c, j); // 保持更新这个char的最新出现的index
			}
			j++;
		}

		if (result < j - i)
			return j - i;
		else
			return result;  // 找到longest
	}
}

// 01/09/15
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int l = s.length();
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        if(l == 0) return 0;
        int max = 0;
        int start = 0;
        int i = 0;
        for(; i < l; i++){
            char c = s.charAt(i);
            if(map.containsKey(c)){
                max = Math.max(max, i-start);
                start = Math.max(start, map.get(c)+1);  // 更新新的substring的start   注意比较的是map.get(c)+1
                map.put(c, i);
            }
            else{
                map.put(c, i);
            }
        }
        max = Math.max(max, i-start);  // 注意需要再检查一次
        return max;
    }
}


// 03/10/15
// 用map来记录某个char的上一次出现的index，当再次发现时计算local的length，和ret比较
// 并且更新start新的substring起点，比较上次出现的index和原来的start
// 因为上次出现char的index可能在start前面，也可能在后面，所以要检查local是否cover了上次出现的index
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int l = s.length();
        if(l == 0) return 0;
        int ret = 0;
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        for(int i = 0; i < l; i++){
            char c = s.charAt(i);
            if(!map.containsKey(c)){
                map.put(c, i);
            }
            else{  // 不管上次出现在哪，都需要update，因为即使出现在start的前面，也要update map里存的位置
                ret = Math.max(ret, i-start);
                int idx = map.get(c);
                start = Math.max(start, idx+1);
                map.put(c, i);
            }
        }
        ret = Math.max(ret, l-start);
        return ret;
    }
}